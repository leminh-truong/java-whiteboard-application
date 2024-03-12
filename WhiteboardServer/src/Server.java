/**
 * This class is the server of the whiteboard system, which distributes the
 * shared canvas on which clients can draw. The server manager can also work
 * on the shared canvas by directly drawing on the server's canvas, which will
 * be the basis for clients' canvases to synchronize.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */

import javax.net.ServerSocketFactory;
import java.awt.*;
import javax.swing.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {

    private static Manager manager;
    public static void main(String[] args){

        // Check command
        if(args.length != 1){
            System.out.println("Wrong command. Command to start" +
                    "the server is: java –jar WhiteboardServer.jar <port>");
            System.exit(1);
        }
//
        int port = Integer.parseInt(args[0]);
        ServerSocketFactory factory = ServerSocketFactory.getDefault();

        // Create server socket to wait for clients to connect
        try(ServerSocket server = factory.createServerSocket(port))
        {
            System.out.println("Waiting for connections from client-");

            // Run the manager GUI
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try { manager = new Manager();
                        manager.setTitle("The server");
                        manager.setVisible(true);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            );

            // Wait for connections.
            while(true)
            {
                Socket client = server.accept();

                // Receive name of the client
                DataInputStream getName = new DataInputStream(client.getInputStream());
                String clientName = getName.readUTF();

                // Prompt the server manager to approve or deny a new client's request
                int res = JOptionPane.showConfirmDialog(null, "Accept new Client: " + clientName + "?",
                        "New Client", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    client.setKeepAlive(true);
                    DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
                    outputStream.writeUTF("Connected to server");

                    // Start a new thread for a connection if connection is approved
                    Thread t = new Thread(() -> serveClient(client, clientName));
                    t.start();
                } else {
                    // Close the socket if connection is refused
                    DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
                    outputStream.writeUTF("Connection refused");
                    outputStream.close();
                    client.close();
                    continue;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Cannot create socket or connection has been refused");
        }
    }

    /**
     * A thread's function to synchronize the canvas of the server with those
     * of the clients
     * @param client: Client socket
     * @param clientName: Username of client
     */
    private static void serveClient(Socket client, String clientName) {
        String user = clientName;
        manager.getCanvas().addUser(user);
        boolean flag = true; // Used to stop thread when client disconnects
        JSONParser parser = new JSONParser();

        while(flag) {
            try {

                // Input stream to receive new canvas element from client
                DataInputStream input = new DataInputStream((client.getInputStream()));

                // Convert read data to JSON
                JSONObject shape = (JSONObject) parser.parse(input.readUTF());

                // Sync the client's canvas with the server's by updating the client's
                // canvas with new elements found in the server's canvas
                if (shape.containsKey("message") && shape.get("message").equals("sync")) {
                    JSONArray shapeArr = new JSONArray();
                    Iterator<WhiteboardShapes> shapesList = manager.getCanvas().getShapeList().iterator();

                    while (shapesList.hasNext()) {
                        WhiteboardShapes currentShape = shapesList.next();
                        JSONObject toBeSent = new JSONObject();

                        toBeSent.put("firstX", currentShape.x);
                        toBeSent.put("firstY", currentShape.y);
                        toBeSent.put("lastX", currentShape.endX);
                        toBeSent.put("lastY", currentShape.endY);

                        if (currentShape instanceof WhiteboardLine) {
                            toBeSent.put("color", ((WhiteboardLine) currentShape).getColor());
                            toBeSent.put("id", 0);
                        } else if (currentShape instanceof WhiteboardRectangle) {
                            toBeSent.put("color", ((WhiteboardRectangle) currentShape).getColor());
                            toBeSent.put("id", 1);
                        } else if (currentShape instanceof WhiteboardCircle) {
                            toBeSent.put("color", ((WhiteboardCircle) currentShape).getColor());
                            toBeSent.put("id", 2);
                        }else if (currentShape instanceof WhiteboardTriangle) {
                            toBeSent.put("color", ((WhiteboardTriangle) currentShape).getColor());
                            toBeSent.put("id", 3);
                        }else if(currentShape instanceof WhiteboardText){
                            toBeSent.put("color", ((WhiteboardText) currentShape).getColor());
                            toBeSent.put("text", ((WhiteboardText) currentShape).getText());
                            toBeSent.put("id", 4);
                        }
                        shapeArr.add(toBeSent);
                    }
                    DataOutputStream output = new DataOutputStream(client.getOutputStream());
                    output.writeUTF(shapeArr.toJSONString());
                    output.flush();
                }
                // Disconnect a client per the client's request
                else if (shape.containsKey("message") && shape.get("message").equals("disconnect")){
                    flag = false;
                    manager.getCanvas().deleteUser(user);
                }

                // Sync the server's canvas with the client's by updating the server's
                // canvas with new elements found in the client's canvas
                else {
                    updateWhiteboard(shape);
                }

            } catch(IOException | ParseException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * Function to synchronize the server's canvas with new elements in the
     * client's canvas
     * @param shape: Information about a WhiteboardShape in the form of a JSON
     *             object
     */
    private static synchronized void updateWhiteboard(JSONObject shape){
        ArrayList<WhiteboardShapes> newShapes = new ArrayList<>();

        // Extract information from the new element in the client's canvas,
        // sent to the server in the form of a JSON object.
        int firstX = (int) (long) shape.get("firstX");
        int firstY = (int) (long) shape.get("firstY");
        int lastX = (int) (long) shape.get("lastX");
        int lastY = (int) (long) shape.get("lastY");
        String color = (String) shape.get("color");
        String textClient = " ";
        if(shape.containsKey("text")){
            textClient = (String) shape.get("text");
        }

        // Update the server's canvas
        if ((int) (long) shape.get("id") == 0) {
            WhiteboardLine line = new WhiteboardLine(firstX, firstY, lastX, lastY);
            line.setColor(color);
            newShapes.add(line);
        } else if ((int) (long) shape.get("id") == 1) {
            System.out.println("It's a rectangle");
            WhiteboardRectangle rectangle = new WhiteboardRectangle(firstX, firstY, lastX, lastY);
            rectangle.setColor(color);
            newShapes.add(rectangle);
        } else if ((int) (long) shape.get("id") == 2) {
            WhiteboardCircle circle = new WhiteboardCircle(firstX, firstY, lastX, lastY);
            circle.setColor(color);
            newShapes.add(circle);
        } else if ((int) (long) shape.get("id") == 3) {
            WhiteboardTriangle triangle = new WhiteboardTriangle(firstX, firstY, lastX, lastY);
            triangle.setColor(color);
            newShapes.add(triangle);
        } else if ((int) (long) shape.get("id") == 4) {
            WhiteboardText textComponent = new WhiteboardText(firstX, firstY, lastX, lastY);
            textComponent.setColor(color);
            textComponent.setText(textClient);
            newShapes.add(textComponent);
        }


        manager.getCanvas().updateShapeList(newShapes);

    }
}
