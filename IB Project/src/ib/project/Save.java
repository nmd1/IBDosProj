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
    Button SavetoText, SaveandTake, TakeQuiz;
    Container saverPane;
    JFileChooser fc = new JFileChooser();
    SpringLayout layout = new SpringLayout();
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
        SavetoText.setPreferredSize(a);
        SaveandTake.setPreferredSize(a);
        TakeQuiz.setPreferredSize(a);
        SavetoText.addActionListener(new StT());
    }
    public void Layout(Component c, int x, int y) {
        layout.putConstraint(SpringLayout.WEST, c,x, SpringLayout.WEST, saverPane);
        layout.putConstraint(SpringLayout.NORTH, c,y,SpringLayout.NORTH, saverPane);
    }
    public void file() {
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int returnVal = fc.showOpenDialog(saverPane);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = new File(fc.getSelectedFile().getAbsolutePath() + "/DATA.txt"); //fileChooser.getSelectedFile();
            
            //fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
            //fc.showSaveDialog(saverPane);
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                System.out.println(file.toPath());
                output.write("QUIZ DATA\n");
                for(String a: properties){
                    output.write(a+"\n");
                }
                output.write("\n");
                for(String a: QuestA){
                    output.write(a);
                }
                output.write("\n");
                for(String a: RAnswer){
                    output.write(a);
                }
                output.write("\n");
                for(ArrayList b : WAnswer){
                    //I WAS WORKING ON THIS LINE b.
                    for(Object a : b){
                        
                    }
                }
                output.close();
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
            file();
        }
    }
}

