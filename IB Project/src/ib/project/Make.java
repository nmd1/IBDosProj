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
    Checkbox timer, repeat, tryA;
    Checkbox Cone, Ctwo, Cthree, Cfour, Cfive;
    Label Re = new Label(), Ti = new Label(), Nq = new Label();
    CheckboxGroup PerQuest = new CheckboxGroup();
    TextField QuestNum;
    public Make() {
        
    }
    
    
    public void QuizCreation() {
        prop.setVisible(true);
        main.setVisible(false);
        buttonPane = prop.getContentPane();
        FlowLayout myLayout = new FlowLayout(FlowLayout.CENTER, 10,10); //lets focus on this
        buttonPane.setLayout(myLayout);
        Buttons();
        Checkbox();
        Options();
        Textfield();
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
        tryA = new Checkbox("Try Again?"); buttonPane.add(tryA);
        Ti.setPreferredSize(new Dimension(100,40));
        Re.setPreferredSize(new Dimension(100,40));
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
        //seriously, saterday, work on this
        QuestNum = new TextField();
        Nq.setText("Numer of Questions Displayed"); 
        buttonPane.add(Nq);
        buttonPane.add(QuestNum);
    }
    private class prop implements ActionListener {
        //When Next is presed, go to the next screen and place all the properties
        //in the properties string.
        @Override
        public void actionPerformed(ActionEvent ae) {//turn this into an arrayList
            if (debug = true) System.out.println("Timer is " + timer.getState());
            
            if(timer.getState()) {
               properties.add("Timer = true");
               properties.add("TimerValue = " + Ti.getText());
            } else {
                properties.add("Timer = false");
            }
            
            if (debug = true) System.out.println("Repeat is " + repeat.getState() + " With value " + Re.getText());
            if(repeat.getState()) {
                properties.add( "Repeat = true");
                properties.add("RepeatValue = " + Re.getText());
            } else {
                properties.add("Repeat = false");
            }
        
            if(tryA.getState()) {
                properties.add("TryAgain = true");
            } else {
                properties.add("TryAgain = false");
            }
            
            if(PerQuest.getSelectedCheckbox() == Cone) properties.add("PerQuest = 1");
            if(PerQuest.getSelectedCheckbox() == Ctwo) properties.add("PerQuest = 2");
            if(PerQuest.getSelectedCheckbox() == Cthree) properties.add("PerQuest = 3");
            if(PerQuest.getSelectedCheckbox() == Cfour) properties.add("PerQuest = 4");
            if(PerQuest.getSelectedCheckbox() == Cfive) properties.add("PerQuest = 5");
            
            if (debug = true) System.out.println("\nPROPERTIES" + properties);
            /*
            0 refers to the If there is a timer
            1 refers to the timer value (may not exist)
            2 refers to the If there is a repeat
            3 refers to the Repeat value
            4 refers to how many options will be in each question of the quiz
            */
        }
    }
}
