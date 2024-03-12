/**
 * This class is the client of the whiteboard system, which allows
 * concurrent clients to draw on a shared interactive canvas
 * distributed by the server. The clients can do so via a GUI
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */

import org.json.simple.JSONObject;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class Client extends JFrame {

    private String color;
    private String shape;
    private static Whiteboard canvas;
    private static String userName;
    private static JSONObject shapes = new JSONObject();
    private static String ip;
    private static int port;

    /**
     * Launch the application.
     */

     public static void main(String[] args) {

         // Command checking
         if(args.length != 3){
             System.out.println("Wrong command. Command to start" +
                     "the client is: java –jar WhiteboardClient.jar <server-address> <server-port> <username>");
             System.exit(1);
         }
         else {
             ip = args[0];
             port = Integer.parseInt(args[1]);
             userName = args[2];
         }

         // Run the GUI
         EventQueue.invokeLater(new Runnable() {
             public void run() {
                 try { Client frame = new Client();
                     frame.setTitle("Client: " + userName);
                     frame.setVisible(true);
                 } catch (Exception e)
                 {
                     e.printStackTrace();
                 }
             }
         }
         );
     }

    /**
     * Create the client GUI.
     */
    public Client() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 700);
        getContentPane().setLayout(null);

        canvas = new Whiteboard(ip, port, userName);
        canvas.setBounds(0, 220, 539, 432);
        canvas.setBackground(Color.WHITE);
        getContentPane().add(canvas);

        JToggleButton tglbtnNewToggleButton = new JToggleButton("Triangle");
        tglbtnNewToggleButton.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                this.color = "#000000";
                this.shape = "Triangle";
            }
            else {

            }
            canvas.setColor(this.color);
            canvas.setShape(this.shape);
        });
        tglbtnNewToggleButton.setBounds(0, 0, 86, 23);
        getContentPane().add(tglbtnNewToggleButton);

        JButton closeButton = new JButton("Close");
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                canvas.disconnectClient();
            }
        });
        closeButton.setBounds(0, 34, 86, 23);
        getContentPane().add(closeButton);

        JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("Circle");
        tglbtnNewToggleButton_2.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#000000";
                shape = "Circle";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
            canvas.setShape(this.shape);
        });
        tglbtnNewToggleButton_2.setBounds(96, 0, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_2);

        JToggleButton tglbtnNewToggleButton_3 = new JToggleButton("Rectangle");
        tglbtnNewToggleButton_3.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#000000";
                shape = "Rectangle";
            }
            else {
                color = "#000000";
                this.shape = "Line";
            }
            canvas.setColor(this.color);
            canvas.setShape(this.shape);
        });
        tglbtnNewToggleButton_3.setBounds(96, 34, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_3);

        JToggleButton tglbtnNewToggleButton_4 = new JToggleButton("Line");
        tglbtnNewToggleButton_4.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#000000";
                shape = "PresetLine";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
            canvas.setShape(this.shape);
        });
        tglbtnNewToggleButton_4.setBounds(192, 0, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_4);

        JButton textButton = new JButton("Text");
        textButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String newText = JOptionPane.showInputDialog("Enter text");
                canvas.setShape("Text");
                canvas.setColor("#000000");
                canvas.setText(newText);
            }
        });
        textButton.setBounds(192, 34, 86, 23);
        getContentPane().add(textButton);

        JToggleButton tglbtnNewToggleButton_6 = new JToggleButton("Crimson");
        tglbtnNewToggleButton_6.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#DC143C";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_6.setBounds(303, 0, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_6);

        JToggleButton tglbtnNewToggleButton_7 = new JToggleButton("Silver");
        tglbtnNewToggleButton_7.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#C0C0C0";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_7.setBounds(303, 34, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_7);

        JToggleButton tglbtnNewToggleButton_8 = new JToggleButton("Yellow");
        tglbtnNewToggleButton_8.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#FFFF00";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_8.setBounds(399, 0, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_8);

        JToggleButton tglbtnNewToggleButton_9 = new JToggleButton("Gray");
        tglbtnNewToggleButton_9.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "808080";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_9.setBounds(399, 34, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_9);

        JToggleButton tglbtnNewToggleButton_10 = new JToggleButton("Fuchsia");
        tglbtnNewToggleButton_10.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#FF00FF";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_10.setBounds(495, 0, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_10);

        JToggleButton tglbtnNewToggleButton_11 = new JToggleButton("Olive");
        tglbtnNewToggleButton_11.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#808000";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_11.setBounds(495, 34, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_11);

        JToggleButton tglbtnNewToggleButton_12 = new JToggleButton("Red");
        tglbtnNewToggleButton_12.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "FF0000";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_12.setBounds(591, 0, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_12);

        JToggleButton tglbtnNewToggleButton_13 = new JToggleButton("Purple");
        tglbtnNewToggleButton_13.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#800080";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_13.setBounds(591, 34, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_13);

        JToggleButton tglbtnNewToggleButton_14 = new JToggleButton("Brown");
        tglbtnNewToggleButton_14.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#800000";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_14.setBounds(303, 68, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_14);

        JToggleButton tglbtnNewToggleButton_15 = new JToggleButton("Aqua");
        tglbtnNewToggleButton_15.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#00FFFF";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_15.setBounds(399, 68, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_15);

        JToggleButton tglbtnNewToggleButton_16 = new JToggleButton("Lime");
        tglbtnNewToggleButton_16.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#00FF00";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_16.setBounds(495, 68, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_16);

        JToggleButton tglbtnNewToggleButton_17 = new JToggleButton("Teal");
        tglbtnNewToggleButton_17.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#008080";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNewToggleButton_17.setBounds(591, 68, 86, 23);
        getContentPane().add(tglbtnNewToggleButton_17);

        JToggleButton tglbtnGreen = new JToggleButton("Green");
        tglbtnGreen.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#008000";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnGreen.setBounds(303, 102, 86, 23);
        getContentPane().add(tglbtnGreen);

        JToggleButton tglbtnBlue = new JToggleButton("Blue");
        tglbtnBlue.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#0000FF";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnBlue.setBounds(399, 102, 86, 23);
        getContentPane().add(tglbtnBlue);

        JToggleButton tglbtnNavy = new JToggleButton("Navy");
        tglbtnNavy.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#000080";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnNavy.setBounds(495, 102, 86, 23);
        getContentPane().add(tglbtnNavy);

        JToggleButton tglbtnBlack = new JToggleButton("Black");
        tglbtnGreen.addItemListener(e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED){
                color = "#000000";
            }
            else {
                color = "#000000";
            }
            canvas.setColor(this.color);
        });
        tglbtnBlack.setBounds(591, 102, 86, 23);
        getContentPane().add(tglbtnBlack);

        JLabel lblNewLabel = new JLabel("Canvas");
        lblNewLabel.setBounds(0, 174, 86, 35);
        getContentPane().add(lblNewLabel);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(549, 215, 128, 437);
        getContentPane().add(textArea);
        textArea.setText(canvas.getResult());

        JLabel lblLog = new JLabel("Log");
        lblLog.setBounds(549, 174, 86, 35);
        getContentPane().add(lblLog);

        JButton btnNewButton = new JButton("Sync");
        btnNewButton.setBounds(0, 102, 89, 23);
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                canvas.refreshWhiteboard();
            }
        });
        getContentPane().add(btnNewButton);
    }
}
