package ib.project;
import static ib.project.Main.*;
import java.awt.Button;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import javax.swing.*;

public class Make {
    Button next;
    Container buttonPane;
    Checkbox timer, repeat;
    Checkbox Cone, Ctwo, Cthree, Cfour, Cfive;
    Label Re = new Label(), Ti = new Label();
    CheckboxGroup PerQuest = new CheckboxGroup();
    TextField QuestNum = new TextField();
    public Make() {
        
    }
    
    
    public void QuizCreation() {
        prop.setVisible(true);
        main.setVisible(false);
        buttonPane = prop.getContentPane();
        FlowLayout myLayout = new FlowLayout();
        buttonPane.setLayout(myLayout);
        Buttons();
        Checkbox();
        Options();
    }
    
    public void Buttons() {
        next = new Button("Next");
        next.setPreferredSize(new Dimension(100,40));
        next.addActionListener(new prop());
        buttonPane.add(next);
        
    }

    
    public void Checkbox() {
        timer = new Checkbox("Timed?");buttonPane.add(timer);
        theIL(timer, Ti, "How long (In Seconds) should the quiz last", "Time");
        repeat = new Checkbox("Repeat Exam?"); buttonPane.add(repeat);
        theIL(repeat, Re, "Input Amount of Times to repeat","Repeat");
        buttonPane.add(Ti); buttonPane.add(Re);
    }
    public void theIL(final Checkbox but, final Label l, final String ask, final String title) {
        
        but.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if (e.getStateChange() == ItemEvent.SELECTED) { //if the checkbox is selected
                    String wait = ""; //creates a empty string to write to
                    if (debug == true) System.out.println("True line reached"); //debug
                    boolean breaks = true; //to stop the while loop
                    while(breaks) { //keep looping until the input is correct
                        String response; //the input from the question box
                        response = JOptionPane.showInputDialog(null, 
                                ask, title, JOptionPane.QUESTION_MESSAGE); //gets the response
                        if ((response != null) && (response.length() > 0)) { //tests if the response exists
                            if(isNum(response)) { //if it does, tests if its a number
                                wait = response; //if its a number, the empty string at the top becomes that number
                                breaks = false; //stops the loop
                                }
                        } else { //if its empty
                            but.setState(false); //uncheck the checkbox
                        }   
                        if(null == response) { //checks if the reponse if null
                            but.setState(false); //if it is, uncheck the checkbox
                            breaks = false; //and leave the loop
                        }
                    } //end of loop
                    
                    l.setVisible(true); //set the label to visible
                    //JOptionPane.show
                    l.setText(wait); //change the text of the label to the resonse
                    } else { //if the checkbox is unselected
                    if (debug == true) System.out.println("False line reached");
                    l.setVisible(false); //remove the label
                    }   
           }
        });
        
    }
    public boolean isNum(String s)  {  
        try {  
            double temp = Double.parseDouble(s);  
        }  
        catch(NumberFormatException e) {  
            return false;  
        }  
        return true;  
    }
    
    public void Options() {
         Cone = new Checkbox("One",true,PerQuest);//one.getState();
         Ctwo = new Checkbox("two",false,PerQuest);
         Cthree = new Checkbox("three",false,PerQuest);
         Cfour = new Checkbox("four",false,PerQuest);
         Cfive = new Checkbox("five",false,PerQuest);
         buttonPane.add(Cone);
         buttonPane.add(Ctwo);
         buttonPane.add(Cthree);
         buttonPane.add(Cfour);
         buttonPane.add(Cfive);
    }
    public void Textfield() {
        //Alright, today, work on this.
    }
    private class prop implements ActionListener {
        //When Next is presed, go to the next screen and place all the properties
        //in the properties string.
        @Override
        public void actionPerformed(ActionEvent ae) {
            properties = "";
            if (debug == true) System.out.println("Timer is " + timer.getState());
            if(timer.getState()) {
               properties = properties + "Timer = true\n";
               properties = properties + "TimerValue = " + Ti.getText() + "\n";
            } else {
                properties = properties + "Timer = false\n";
            }
            
            if (debug == true) System.out.println("Repeat is " + repeat.getState() + " With value " + Re.getText() + "\n");
            if(repeat.getState()) {
                properties = properties + "Repeat = true\n";
                properties = properties + "RepeatValue = " + Re.getText() + "\n";
            } else {
                properties = properties + "Repeat = false\n";
            }
            
            if(PerQuest.getSelectedCheckbox() == Cone) properties = properties + "PerQuest = 1\n";
            if(PerQuest.getSelectedCheckbox() == Ctwo) properties = properties + "PerQuest = 2\n";
            if(PerQuest.getSelectedCheckbox() == Cthree) properties = properties + "PerQuest = 3\n";
            if(PerQuest.getSelectedCheckbox() == Cfour) properties = properties + "PerQuest = 4\n";
            if(PerQuest.getSelectedCheckbox() == Cfive) properties = properties + "PerQuest = 5\n";
            
            if (debug == true) System.out.println("\nPROPERTIES\n" + properties);
            
        }
    }
}
