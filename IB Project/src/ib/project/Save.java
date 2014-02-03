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
    Button SavetoText, SaveandTake, TakeQuiz, leave;
    Container saverPane;
    JFileChooser fc = new JFileChooser();
    SpringLayout layout = new SpringLayout();
    Label Sucess = new Label();
    public void Saving() {
        prop.setVisible(false);
        main.setVisible(false);
        save.setVisible(true);
        saverPane = save.getContentPane();
        saverPane.setLayout(layout);
        buttons();
        order();
    }
    public void order() {
        Layout(SavetoText, 40, 20);
        Layout(SaveandTake, 40, 80);
        Layout(TakeQuiz, 40, 140);
    }
    public void buttons() {
        Dimension a = new Dimension(300,40);
        SavetoText = new Button("Save to a text file for later");saverPane.add(SavetoText);
        SaveandTake = new Button("Save to a text file and take quiz now"); saverPane.add(SaveandTake);
        TakeQuiz = new Button("Take the quiz without saving quiz info"); saverPane.add(TakeQuiz);
        leave = new Button("Exit");
        leave.setPreferredSize(new Dimension(100,40));
        SavetoText.setPreferredSize(a);
        SaveandTake.setPreferredSize(a);
        TakeQuiz.setPreferredSize(a);
        SavetoText.addActionListener(new StT());
        SaveandTake.addActionListener(new SaT());
        TakeQuiz.addActionListener(new TQ());
        leave.addActionListener(new Exit());
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
            File file = new File(fc.getSelectedFile().getAbsolutePath() + "/DATA.txt"); //fileChooser.getSelectedFile();
            
            //fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
            //fc.showSaveDialog(saverPane);
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                System.out.println(file.toPath());
                output.write("QUIZ DATA\n");
                output.newLine();
                output.write("" + properties);
                output.newLine();
                output.write("" + QuestA);
                output.newLine();
                output.write("" + RAnswer);
                output.newLine();
                output.write("" + WAnswer);
                output.close();
                
                saverPane.remove(SaveandTake);
                saverPane.remove(SavetoText);
                saverPane.remove(TakeQuiz);
                saverPane.add(Sucess);
                Layout(Sucess, 10, 10);
                Sucess.setText("Your quiz has been saved sucessfully");
                Font f = new Font("Verdana", Font.BOLD, 24);
                Sucess.setFont(f);
                saverPane.add(leave);
                Layout(leave, 130, 120);
                
            } catch (IOException e) {
                System.out.println("problem accessing file"+file.getAbsolutePath());
                /*int choice = */JOptionPane.showConfirmDialog(null, 
                        "There was a problem accessing the file. Try again", 
                        "Are you sure?", JOptionPane.YES_OPTION);
                if(debug == true) System.out.println(e);
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
        }
    }
    private class TQ implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //go directly to take screen
        }
    }
    private class Exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int choice = JOptionPane.showConfirmDialog(null, 
                        "Would you like to exit the QuizCreater? No for mainscreen", 
                        "Exit", JOptionPane.YES_NO_OPTION);// yes = 0 no = 1
            if(choice == 0) System.exit(choice); //WOOO EXIT POINT
            if(choice == 1) {
                if(debug = true) System.out.println("Main Menu");    
                save.setVisible(false);
                main.setVisible(true);
                prop.setVisible(false);
            
            }
        }
    }
}

