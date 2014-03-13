package ib.project;

import static ib.project.Main.*;

import java.awt.Button;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Save {
    //global variables
    Button SavetoText, SaveandTake, TakeQuiz, leave, mainB;
    Container saverPane;
    JFileChooser fc = new JFileChooser();
    SpringLayout layout = new SpringLayout();
    Label Sucess = new Label(), nameL = new Label();
    TextField name = new TextField();
    public void Saving() {
        prop.setVisible(false);
        main.setVisible(false);
        save.setVisible(true);
        start.setVisible(false);
        saverPane = save.getContentPane();
        saverPane.setLayout(layout);
        
        buttons();
    }
    public void order() {
        Layout(SavetoText, 40, 20);
        Layout(SaveandTake, 40, 80);
        Layout(TakeQuiz, 40, 140);
        Layout(name, 200, 190);
        Layout(nameL, 85, 190);
       
    }
    public void buttons() {
        Dimension a = new Dimension(300,40);
        SavetoText = new Button("Save to a text file for later");saverPane.add(SavetoText);
        SaveandTake = new Button("Save to a text file and take quiz now"); saverPane.add(SaveandTake);
        TakeQuiz = new Button("Take the quiz without saving quiz info"); saverPane.add(TakeQuiz);
        leave = new Button("Exit");
        mainB = new Button("Main Menu");
        leave.setPreferredSize(new Dimension(100,40));
        mainB.setPreferredSize(new Dimension(100,40));
        SavetoText.setPreferredSize(a);
        SaveandTake.setPreferredSize(a);
        TakeQuiz.setPreferredSize(a);
        SavetoText.addActionListener(new StT());
        SaveandTake.addActionListener(new SaT());
        TakeQuiz.addActionListener(new TQ());
        leave.addActionListener(new Exit());
        mainB.addActionListener(new Menu());
        nameL.setText("Name Your File");
        saverPane.add(nameL);
        name = new TextField();
        name.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int tempI = ke.getKeyChar();
                
                boolean Capital = tempI >=65 || tempI <=90;
                boolean Lower = tempI >= 97 || tempI <= 122;
                boolean numbers = tempI >= 48 || tempI <= 57;
                if(!(Capital || numbers || Lower)) { 
                    ke.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent ke) {}
            @Override
            public void keyReleased(KeyEvent ke) {}
        });
       name.setColumns(7);
       saverPane.add(name);
        order();
    }
    public void Layout(Component c, int x, int y) {
        layout.putConstraint(SpringLayout.WEST, c,x, SpringLayout.WEST, saverPane);
        layout.putConstraint(SpringLayout.NORTH, c,y,SpringLayout.NORTH, saverPane);
    }
    public void file(String a) {
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setApproveButtonText(a);
        fc.setApproveButtonMnemonic('S');
        fc.setApproveButtonToolTipText("Save the \" Data.txt \" file in this current folder");
          int returnVal = fc.showOpenDialog(saverPane);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = new File(fc.getSelectedFile().getAbsolutePath() + "/"+name.getText()+"Data.txt"); //fileChooser.getSelectedFile();
            
            //fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
            //fc.showSaveDialog(saverPane);
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                System.out.println(file.toPath());
                output.write("QUIZ Data\n");
                output.newLine();
                output.write("" + properties);
                output.newLine();
                output.write("" + QuestA);
                output.newLine();
                output.write("" + RAnswer);
                output.newLine();
                output.write("" + WAnswer);
                output.close();
                
                if(a.equals("Save here")) {
                saverPane.remove(SaveandTake);
                saverPane.remove(SavetoText);
                saverPane.remove(TakeQuiz);
                saverPane.add(Sucess);
                Layout(Sucess, 10, 10);
                Sucess.setText("Your quiz has been saved sucessfully");
                Font f = new Font("Verdana", Font.BOLD, 24);
                save.setSize(470,160);
                Sucess.setFont(f);
                saverPane.add(leave);
                saverPane.add(mainB);
                Layout(leave, 270, 60);
                Layout(mainB, 80, 60);
                }
                
            } catch (IOException e) {
                System.out.println("problem accessing file"+file.getAbsolutePath());
                /*int choice = */JOptionPane.showConfirmDialog(null, 
                        "There was a problem accessing the file. Try again", 
                        "Are you sure?", JOptionPane.YES_OPTION);
                printd("" + e);
            }
            } else {
                System.out.println("File access cancelled by user.");
            }
    }
    private class StT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            file("Save here");
        }
    }
    private class SaT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            file("Save and Take Quiz");
            Take t = new Take();
            t.Taking();
        }
    }
    private class TQ implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Take t = new Take();
            t.Taking();
        }
    }
    private class Exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0); //WOOO EXIT POINT;
        }
    }
    private class Menu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            Main m = new Main();
            main.setVisible(true);
            save.dispose();
            prop.dispose();
            save.setVisible(false);
            buttons();
            
            prop.setVisible(false);
            start.setVisible(false);
            //maybe create an erase method?
        }  
    }
    public void printd(String a) {
        if(debug) System.out.println(a);
    }
}

