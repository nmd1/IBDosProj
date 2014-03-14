package ib.project;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JPanel {
    
    public static JFrame main, prop, start, save, scor;
    static Container pane;
   // static TextField;
    static Button makeB, takeB, helpB;
    static Checkbox check;
    public static Choice equations;
    static SpringLayout myLayout;
    //static String properties;
    static ArrayList<String> properties = new ArrayList<String>();
    static ArrayList<String> QuestA = new ArrayList<String>();
    static ArrayList<String> RAnswer = new ArrayList<String>();
    static ArrayList<ArrayList<String>> WAnswer = new ArrayList<ArrayList<String>>();
    static boolean debug = true;
    static int Qright, Qwrong;
    public static void main(String[] args) {
        //Main useless = new Main();
        //useless.mainRun();
        mainRun();
    }
    
    public static void mainRun() {
        //Main Properties//
        main = new JFrame();
        main.update(null);
        main.setTitle("Main Menu");
        newPanel(main);
        main.setSize(340,290);
        main.setResizable(true);
        //main.add() //We'll see if we need this later
        
        //make Properties Properties//
        prop = new JFrame();
        prop.setTitle("Set Properties");
        prop.setSize(500,200);
        newPanel(prop);
        
        //Make Save properties
        save = new JFrame();
        save.setTitle("What would you like to do with your Quiz?");
        save.setSize(400,270);
        newPanel(save);
        
        //take start properties//
        start = new JFrame();
        start.setTitle("Choose a Quiz");
        start.setSize(500,280);
        newPanel(start);
        
        //Scoring start properties//
        scor = new JFrame();
        scor.setTitle("Your Results");
        scor.setSize(320,250);
        newPanel(scor);
        // JObjects
        
        //buttons
        Dimension a = new Dimension(120, 100);
        makeB = new Button("Make A Quiz");
        makeB.setPreferredSize(a);
        makeB.addActionListener(new making());
        takeB = new Button("Take A Quiz");
        takeB.setPreferredSize(a);
        takeB.addActionListener(new taking());
        helpB = new Button("Help");
        helpB.setPreferredSize(new Dimension(120,50));
        helpB.addActionListener(new info());
        
        //Labels
        Label lblmain = new Label("Choose an Option below");
        
        
        
        //panes
        pane = main.getContentPane();
        myLayout = new SpringLayout();
        pane.setLayout(myLayout);
        pane.add(lblmain);
        pane.add(makeB);
        pane.add(takeB);
        pane.add(helpB);
        Layout(makeB, 20, 40);
        Layout(takeB, 180, 40);
        Layout(helpB, 100, 170);
        Layout(lblmain, 90, 10);
        
        main.setVisible(true);
        
    }
    public static void Layout(Component c, int x, int y) {
        myLayout.putConstraint(SpringLayout.WEST, c,x, SpringLayout.WEST, pane);
        myLayout.putConstraint(SpringLayout.NORTH, c,y,SpringLayout.NORTH, pane);
    }
    private static class info implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String message = "Choose 'Make a Quiz' to go to the quiz creation screen. \n"
                    + "From there, fill in the information for a quiz you would like to create.\n"
                    + "You can choose where you would like to save the created quiz, or just take it right there.\n"
                    + "Choose 'Take a quiz' to take a previosuly created quiz.\n"
                    + "You will be prompted to find the folder where you saved a quiz (or more than one), and from there\n"
                    + "you can choose a quiz from the folder and take it.\n ";
            JOptionPane.showMessageDialog(null, message, "Information About Program", 3);
        }
    }
    private static class making implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Make p = new Make();
            p.QuizCreation();
        }
    }
    public static class taking implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Take t = new Take();
            t.Taking();
        }
    }
    
    public static void newPanel(JFrame j) {
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLocationRelativeTo(null);
        j.setResizable(true);
        
        if(j.getTitle().equals("Main Menu")) {
            j.setVisible(true);
        } else {
            j.setVisible(false);
        }
    }
    public static void maining() {}
    
     public static class exiting implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
     
}