/**
 * This class is the canvas of the whiteboard system. The server also
 * contains a similar class with slightly different functionalities. Every
 * element drawn on this canvas will be updated to its server counterpart
 * and will be reflected on the canvas of every connected client.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

public class Whiteboard extends JPanel implements MouseListener, MouseMotionListener {
    ArrayList<WhiteboardShapes> shapeList;
    int firstX_coord, firstY_coord, port;
    private String color;
    private String shape;
    private DataInputStream input;
    private DataOutputStream output;
    private String ip, text, result;
    private Socket socket;

    /**
     * Constructor of the Whiteboard class. Responsible for both creating the
     * canvas and connecting to the server.
     * @param ip: server ip address
     * @param port: server port
     * @param userName: client's username
     */
    public Whiteboard(String ip, int port, String userName){
        this.ip = ip;
        this.port = port;
        setBackground(Color.WHITE);
        shapeList = new ArrayList<WhiteboardShapes>();
        addMouseMotionListener(this);
        addMouseListener(this);

        // Establish connection to server
        try{
            socket = new Socket(this.ip, this.port);
            result = "Connected to server";
        }catch (UnknownHostException e) {
            e.printStackTrace();
            result = "Unknown host server. Please make the server's IP address and port are correct";
        }catch (ConnectException e){
            e.printStackTrace();
            result = "Cannot establish connection to server. Please try again";
        }catch (IOException e) {
            e.printStackTrace();
            result = "Server not responding.";
        }

        // Send client's username to server and synchronize the
        // client's canvas with the server's
        try {
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

            output.writeUTF(userName);


            // Wait for approval from server. Exit the program if connection is not
            // approved by the server's manager.
            String result = input.readUTF();
            if(result.equals("Connected to server")){
                JOptionPane.showMessageDialog(null, "Connection accepted");
            }
            else if(result.equals("Connection refused")){
                JOptionPane.showMessageDialog(null, "Connection refused");
                System.exit(0);
            }
            refreshWhiteboard();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            result = "Unknown host server. Please make the server's IP address and port are correct";
        }catch (ConnectException e){
            e.printStackTrace();
            result = "Cannot connect to server. Please try again";
        }catch (IOException e) {
            e.printStackTrace();
            result = "Server not responding.";
        }
    }

    public void mouseMoved(MouseEvent event)
    {
        refreshWhiteboard();
    }

    public void mouseDragged(MouseEvent event)
    {
    }

    public void mouseEntered(MouseEvent event)
    {
    }

    public void mouseExited(MouseEvent event)
    {
    }

    // Establish coordinates on the canvas of
    // the beginning point of a shape
    public void mousePressed(MouseEvent event)
    {
        firstX_coord = event.getX();
        firstY_coord = event.getY();
    }

    // Draw a shape or input a text. Every drawn shape
    // is immediately updated on the server's canvas
    public void mouseReleased(MouseEvent event)
    {
        int endX_coord = event.getX();
        int endY_coord = event.getY();
        if(this.shape == "Rectangle") {
            WhiteboardRectangle shape = new WhiteboardRectangle(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);

            JSONObject toBeSent = new JSONObject();

            toBeSent.put("firstX", shape.x);
            toBeSent.put("firstY", shape.y);
            toBeSent.put("lastX",  shape.endX);
            toBeSent.put("lastY",  shape.endY);
            toBeSent.put("color", shape.getColor());
            toBeSent.put("id", shape.getId());

            try {
                output = new DataOutputStream(this.socket.getOutputStream());
                output.writeUTF(toBeSent.toString());
                output.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(this.shape == "Circle"){
            WhiteboardCircle shape = new WhiteboardCircle(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);

            JSONObject toBeSent = new JSONObject();

            toBeSent.put("firstX", shape.x);
            toBeSent.put("firstY", shape.y);
            toBeSent.put("lastX",  shape.endX);
            toBeSent.put("lastY",  shape.endY);
            toBeSent.put("color", shape.getColor());
            toBeSent.put("id", shape.getId());

            try {
                output = new DataOutputStream(this.socket.getOutputStream());
                output.writeUTF(toBeSent.toString());
                output.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(this.shape == "PresetLine"){
            WhiteboardLine shape = new WhiteboardLine(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);

            JSONObject toBeSent = new JSONObject();

            toBeSent.put("firstX", shape.x);
            toBeSent.put("firstY", shape.y);
            toBeSent.put("lastX",  shape.endX);
            toBeSent.put("lastY",  shape.endY);
            toBeSent.put("color", shape.getColor());
            toBeSent.put("id", shape.getId());

            try {
                output = new DataOutputStream(this.socket.getOutputStream());
                output.writeUTF(toBeSent.toString());
                output.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(this.shape == "Triangle"){
            WhiteboardTriangle shape = new WhiteboardTriangle(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);

            JSONObject toBeSent = new JSONObject();

            toBeSent.put("firstX", shape.x);
            toBeSent.put("firstY", shape.y);
            toBeSent.put("lastX",  shape.endX);
            toBeSent.put("lastY",  shape.endY);
            toBeSent.put("color", shape.getColor());
            toBeSent.put("id", shape.getId());

            try {
                output = new DataOutputStream(this.socket.getOutputStream());
                output.writeUTF(toBeSent.toString());
                output.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(this.shape == "Text") {
            WhiteboardText shape = new WhiteboardText(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setText(this.text);
            shape.setColor(this.color);
            shapeList.add(shape);

            JSONObject toBeSent = new JSONObject();

            toBeSent.put("firstX", shape.x);
            toBeSent.put("firstY", shape.y);
            toBeSent.put("lastX", shape.endX);
            toBeSent.put("lastY", shape.endY);
            toBeSent.put("color", shape.getColor());
            toBeSent.put("text", shape.getText());
            toBeSent.put("id", shape.getId());

            try {
                output = new DataOutputStream(this.socket.getOutputStream());
                output.writeUTF(toBeSent.toString());
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        firstX_coord = endX_coord;
        firstY_coord = endY_coord;
        repaint();
    }

    public void mouseClicked(MouseEvent me)
    {
    }

    /**
     * This function draws and maintain every element on
     * the canvas
     * @param g: draw tool from Graphics class
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        Iterator<WhiteboardShapes> it = shapeList.iterator();
        while(it.hasNext())
        {
            // Draw a line
            WhiteboardShapes current = it.next();
            if(current instanceof WhiteboardLine) {
                WhiteboardLine newCurrent = (WhiteboardLine) current;

                g.setColor(Color.decode(newCurrent.getColor()));
                g.drawLine(newCurrent.getFirstX(), newCurrent.getFirstY(),
                        newCurrent.getLastX(), newCurrent.getLastY());
            }

            //Draw a circle
            else if(current instanceof  WhiteboardCircle){
                WhiteboardCircle newCurrent = (WhiteboardCircle) current;
                g2.setColor(Color.decode(newCurrent.getColor()));
                g2.drawOval(newCurrent.getFirstX(), newCurrent.getFirstY(), newCurrent.getWidth(), newCurrent.getHeight());
            }

            //Draw a rectangle
            else if(current instanceof WhiteboardRectangle){
                WhiteboardRectangle newCurrent = (WhiteboardRectangle) current;
                g.setColor(Color.decode(newCurrent.getColor()));
                g.drawRect(newCurrent.getFirstX(), newCurrent.getFirstY(), newCurrent.getWidth(), newCurrent.getHeight());
            }

            //Draw a triangle
            else if(current instanceof WhiteboardTriangle){
                WhiteboardTriangle newCurrent = (WhiteboardTriangle) current;
                int[] x_coordinates = {newCurrent.x, newCurrent.endX, newCurrent.getThirdX()};
                int[] y_coordinates = {newCurrent.y, newCurrent.endY, newCurrent.getThirdY()};
                g.setColor(Color.decode(newCurrent.getColor()));
                g.drawPolygon(x_coordinates, y_coordinates, 3);
            }

            //Add text
            else if(current instanceof WhiteboardText){
                WhiteboardText newCurrent = (WhiteboardText) current;
                g.setColor(Color.decode(newCurrent.getColor()));
                g.drawString(newCurrent.getText(), newCurrent.x, newCurrent.y);
            }
        }
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setShape(String shape){
        this.shape = shape;
    }

    public void setText(String text){
        this.text = text;
    }


    /**
     * Helper function to synchronize the client's canvas with the server's
     * by updating the list of elements on the canvas
     * @param newShapes
     */
    public void updateShapeList(ArrayList<WhiteboardShapes> newShapes){
        for(WhiteboardShapes oneShape : newShapes){
            if(!this.shapeList.contains(oneShape)){
                this.shapeList.add(oneShape);
            }
        }
        repaint();
    }

    /**
     * This function synchronizes the client's canvas with the server's
     */
    public void refreshWhiteboard(){
        try {

            // Send signal to the server that the client wishes to
            // synchronize
            JSONObject flag = new JSONObject();
            flag.put("message","sync");
            output.writeUTF(flag.toJSONString());
            output.flush();

            JSONParser parser = new JSONParser();
            ArrayList<WhiteboardShapes> newShapes = new ArrayList<>();

            input = new DataInputStream(socket.getInputStream());
            Object shape = parser.parse(input.readUTF());
            JSONArray shapeArr = (JSONArray) shape;

            // Update list of elements on the client
            // canvas based on the server's canvas
            for(int i = 0; i < shapeArr.size(); i++) {
                int firstX = (int) (long) ((JSONObject) shapeArr.get(i)).get("firstX");
                int firstY = (int) (long) ((JSONObject) shapeArr.get(i)).get("firstY");
                int lastX = (int) (long) ((JSONObject) shapeArr.get(i)).get("lastX");
                int lastY = (int) (long) ((JSONObject) shapeArr.get(i)).get("lastY");
                String serverColor = (String) ((JSONObject) shapeArr.get(i)).get("color");
                String serverText = " ";

                if(((JSONObject) shapeArr.get(i)).containsKey("text")){
                    serverText = (String) ((JSONObject) shapeArr.get(i)).get("text");
                }

                if ((int) (long) ((JSONObject) shapeArr.get(i)).get("id") == 0) {
                    WhiteboardLine line = new WhiteboardLine(firstX, firstY, lastX, lastY);
                    line.setColor(serverColor);
                    newShapes.add(line);
                } else if ((int) (long) ((JSONObject) shapeArr.get(i)).get("id") == 1) {
                    WhiteboardRectangle rectangle = new WhiteboardRectangle(firstX, firstY, lastX, lastY);
                    rectangle.setColor(serverColor);
                    newShapes.add(rectangle);
                } else if ((int) (long) ((JSONObject) shapeArr.get(i)).get("id") == 2) {
                    WhiteboardCircle circle = new WhiteboardCircle(firstX, firstY, lastX, lastY);
                    circle.setColor(serverColor);
                    newShapes.add(circle);
                } else if ((int) (long) ((JSONObject) shapeArr.get(i)).get("id") == 3) {
                    WhiteboardTriangle triangle = new WhiteboardTriangle(firstX, firstY, lastX, lastY);
                    triangle.setColor(serverColor);
                    newShapes.add(triangle);
                } else if ((int) (long) ((JSONObject) shapeArr.get(i)).get("id") == 4) {
                    WhiteboardText textComponent = new WhiteboardText(firstX, firstY, lastX, lastY);
                    textComponent.setColor(serverColor);
                    textComponent.setText(serverText);
                    newShapes.add(textComponent);
                }

            }
            this.updateShapeList(newShapes);
        }catch (ParseException e){
            e.printStackTrace();
            result = "Cannot parse JSON";
        }catch(IOException e){
            e.printStackTrace();
            result = "Server error";
        }
    }

    public ArrayList<WhiteboardShapes> getShapeList(){
        return this.shapeList;
    }

    public String getResult(){
        return this.result;
    }

    /**
     * Function to gracefully disconnect client
     */
    public void disconnectClient(){
        try {
            JSONObject message = new JSONObject();
            message.put("message", "disconnect");
            output.writeUTF(message.toJSONString());
            this.socket.close();
            System.exit(0);
        }
        catch(SocketException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
