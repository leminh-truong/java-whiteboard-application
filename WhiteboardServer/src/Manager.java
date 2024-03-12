/**
 * This class is the GUI of the server of the whiteboard system, which distributes the
 * shared canvas on which clients can draw. The server manager uses this to work on
 * the canvas similar to a client.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Manager extends JFrame{

    private String color;
    private String shape;
    private static Whiteboard canvas;


    /**
     * Create the GUI for the manager
     */
    public Manager(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 700);
        getContentPane().setLayout(null);

        canvas = new Whiteboard();
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
                System.exit(0);
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
                canvas.setText(newText);
                canvas.setColor("#000000");
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

        JLabel lblLog = new JLabel("Log");
        lblLog.setBounds(549, 174, 86, 35);
        getContentPane().add(lblLog);

        JButton btnNewButton = new JButton("Sync");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.setText("");
                for(String user : canvas.getUsers()){
                    textArea.setText(user + "\n");
                }
            }
        });
        btnNewButton.setBounds(0, 102, 89, 23);
        getContentPane().add(btnNewButton);
    }

    public Whiteboard getCanvas(){
        return this.canvas;
    }
}
