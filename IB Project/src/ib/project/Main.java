package ib.project;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JPanel {
    
    public static JFrame main, prop, start, save;
    static Container pane;
    static TextArea area;
    static TextField txtName, a1, b1, c1, m1;
    static Button makeB, takeB, helpB;
    static Checkbox check;
    static Component d;
    public static Choice equations;
    public static Label bigLabel = new Label();
    //static String properties;
    static ArrayList<String> properties = new ArrayList<String>();
    static ArrayList<String> QuestA = new ArrayList<String>();
    static ArrayList<String> RAnswer = new ArrayList<String>();
    static ArrayList<ArrayList<String>> WAnswer = new ArrayList<ArrayList<String>>();
    static boolean debug = true;
    public static void main(String[] args) {
        //Main Properties//
        main = new JFrame();
        main.setTitle("Main Menu");
        newPanel(main);
        main.setSize(500,200);
        main.setVisible(true);
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
        save.setSize(400,250);
        newPanel(save);
        
        //take start properties//
        start = new JFrame();
        start.setTitle("Choose a Quiz");
        start.setSize(500,280);
        newPanel(start);
        
        // JObjects
        
        //buttons
        Dimension a = new Dimension(100,40);
        makeB = new Button("Make A Quiz");
        makeB.setPreferredSize(a);
        makeB.addActionListener(new making());
        takeB = new Button("Take A Quiz");
        takeB.setPreferredSize(a);
        takeB.addActionListener(new taking());
        helpB = new Button("Help");
        helpB.setPreferredSize(new Dimension(70,40));
        helpB.addActionListener(new info());
        
        //Labels
        Label lblmain = new Label("Choose an Option below");
        
        
        
        //panes
        pane = main.getContentPane();
        FlowLayout myLayout = new FlowLayout();
        pane.setLayout(myLayout);
        pane.add(lblmain);
        pane.add(makeB);
        pane.add(takeB);
        pane.add(helpB);
        
    }
    
    private static class info implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String message = "Choose 'Make' to make a quiz or choose" +
                    "'Take' to take a quiz.\n Making a quiz will display a set of" +
                    " options that you can choose that will customize your quiz";
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
    public static void maining() {
        Take t = new Take();
//        int percentage = t.right/t.count;
        bigLabel.setText("Congrats, You're done!");
        makeB.setLabel("Exit");
        takeB.setVisible(false);
        makeB.addActionListener(new exiting());
        helpB.setVisible(false);
    }
    
     public static class exiting implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
}