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
    Label Re = new Label();
    public Make() {
        
    }
    
    
    public void QuizCreation() {
        prop.setVisible(true);
        main.setVisible(false);
        buttonPane = prop.getContentPane();
        FlowLayout myLayout = new FlowLayout();
        buttonPane.setLayout(myLayout);
        buttons();
        checkBox();
    }

    
    public void buttons() {
        next = new Button("Next");
        next.setPreferredSize(new Dimension(100,40));
        next.addActionListener(new more1());
        buttonPane.add(next);
        
    }
    public void checkBox() {
        timer = new Checkbox("Timed?");
        timer.getState();
        buttonPane.add(timer);
        
        repeat = new Checkbox("Repeat Exam?");
        //======If the repeat checkbox is selected open a textbox======
        buttonPane.add(Re);
        
        repeat.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if (e.getStateChange() == ItemEvent.SELECTED) { //if the checkbox is selected
                    String wait = "";
                    if (debug == true) System.out.println("True line reached");
                    String Options[] = {"Ok"};
                    ImageIcon a = new ImageIcon();
                    boolean breaks = true;
                    int first = 0;
                    while(breaks){ //keep looping until the input is correct
                            String response;
                            response = JOptionPane.showInputDialog(null, 
                                "Input Amount of Times to repeat", 
                                "Repeat", JOptionPane.QUESTION_MESSAGE);
                            if ((response != null) && (response.length() > 0)) {
                                if(isNumeric(response)) {
                                wait = response;
                                breaks = false;
                                }
                            } else {
                                 repeat.setState(false);   
                            }
                            
                            if(response.equals("")) {
                                repeat.setState(false);  
                            }
                    }      
                    Re.setVisible(true);
                    //JOptionPane.show
                    Re.setText(wait);
                    } else {
                    if (debug == true) System.out.println("False line reached");
                    Re.setVisible(false);
                    }   
           }
        });
        //==                         end                            ==
        buttonPane.add(repeat);
        if(!Re.equals("")) {
            repeat.setState(false);
        }
    }
    
    
    private class more1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if (debug == true) System.out.println("Timer is " + timer.getState());
            if(timer.getState()) {
               properties = properties + "Timer = true\n";
            } else {
                properties = properties + "Timer = false\n";
            }
            
            if (debug == true) System.out.println("Repeat is " + repeat.getState() + " With value " + Re.getText());
            if(repeat.getState()) {
                properties = properties + "Repeat = true";
                properties = properties + "RepeatValue = " + Re.getText();
            } else {
                properties = properties + "Repeat = false";
            }
            
        }
    }
    
    public static boolean isNumeric(String str)  {  
        try {  
            double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
}
