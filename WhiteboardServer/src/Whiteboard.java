/**
 * This class is the canvas of the whiteboard system. The client also
 * contains a similar class with slightly different functionalities. Every
 * element drawn on this canvas will be updated to its client counterparts
 * and will be reflected on the canvas of every connected client.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class Whiteboard extends JPanel implements MouseListener, MouseMotionListener {
    ArrayList<WhiteboardShapes> shapeList;
    int firstX_coord, firstY_coord;
    String color, shape, text;
    ArrayList<String> users;

    /**
     * Constructor of the Whiteboard class, which is the shared canvas where
     * clients and the manager can all work on. Unlike its counterpart in
     * WhiteboardClient, this constructor is not responsible for the server's
     * networking duties.
     */
    public Whiteboard(){
        setBackground(Color.WHITE);
        shapeList = new ArrayList<WhiteboardShapes>();
        users = new ArrayList<String>();
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void mouseMoved(MouseEvent event)
    {
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
    // is immediately updated on every client's canvas
    public void mouseReleased(MouseEvent event)
    {
        int endX_coord = event.getX();
        int endY_coord = event.getY();
        if(this.shape == "Rectangle") {
            WhiteboardRectangle shape = new WhiteboardRectangle(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);
        }
        else if(this.shape == "Circle"){
            WhiteboardCircle shape = new WhiteboardCircle(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);
        }
        else if(this.shape == "PresetLine"){
            WhiteboardLine shape = new WhiteboardLine(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);
        }
        else if(this.shape == "Triangle"){
            WhiteboardTriangle shape = new WhiteboardTriangle(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setColor(this.color);
            shapeList.add(shape);
        }
        else if(this.shape == "Text") {
            WhiteboardText shape = new WhiteboardText(firstX_coord, firstY_coord, endX_coord, endY_coord);
            shape.setText(this.text);
            shape.setColor(this.color);
            shapeList.add(shape);
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
        Iterator<WhiteboardShapes> it = this.shapeList.iterator();
        while(it.hasNext())
        {
            WhiteboardShapes current = it.next();

            // Draw a line
            if(current instanceof WhiteboardLine) {
                WhiteboardLine newCurrent = (WhiteboardLine) current;
                g.setColor(Color.decode(newCurrent.getColor()));
                g.drawLine(newCurrent.getFirstX(), newCurrent.getFirstY(),
                        newCurrent.getLastX(), newCurrent.getLastY());
            }

            // Draw a circle
            else if(current instanceof  WhiteboardCircle){
                WhiteboardCircle newCurrent = (WhiteboardCircle) current;
                g2.setColor(Color.decode(newCurrent.getColor()));
                g2.drawOval(newCurrent.getFirstX(), newCurrent.getFirstY(), newCurrent.getWidth(), newCurrent.getHeight());
            }

            // Draw a rectangle
            else if(current instanceof WhiteboardRectangle){
                WhiteboardRectangle newCurrent = (WhiteboardRectangle) current;
                g.setColor(Color.decode(newCurrent.getColor()));
                g.drawRect(newCurrent.getFirstX(), newCurrent.getFirstY(), newCurrent.getWidth(), newCurrent.getHeight());
            }

            // Draw a triangle
            else if(current instanceof WhiteboardTriangle){
                WhiteboardTriangle newCurrent = (WhiteboardTriangle) current;
                int[] x_coordinates = {newCurrent.x, newCurrent.endX, newCurrent.getThirdX()};
                int[] y_coordinates = {newCurrent.y, newCurrent.endY, newCurrent.getThirdY()};
                g.setColor(Color.decode(newCurrent.getColor()));
                g.drawPolygon(x_coordinates, y_coordinates, 3);
            }

            // Add a text
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

    public void addUser(String userName){
        this.users.add(userName);
    }

    public void deleteUser(String userName){
        this.users.remove(userName);
    }

    public ArrayList<String> getUsers(){
        return this.users;
    }

    /**
     * Function to synchronize the server's canvas with the clients'
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

    public ArrayList<WhiteboardShapes> getShapeList(){
        return this.shapeList;
    }
}