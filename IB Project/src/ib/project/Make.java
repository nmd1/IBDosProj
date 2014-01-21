package ib.project;
import static ib.project.Main.*;
import java.awt.Button;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.*;
import javax.swing.*;

public class Make {
    Button next;
    Container buttonPane;
    Checkbox timer, repeat;
    TextField numbers;
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
        //=====If the repeat checkbox is selected open a textbox=====
        numbers = new TextField();
        repeat.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //if the checkbox is selected
                    buttonPane.add(numbers);
                    if (debug == true) System.out.println("True line reached");
                    } else {
                    buttonPane.remove(numbers);
                    if (debug == true) System.out.println("False line reached");
                    }   
           }
        });
        //==                         end                            ==
        buttonPane.add(repeat);
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
            
            if (debug == true) System.out.println("Repeat is " + repeat.getState() + " With value " + numbers.getText());
            if(repeat.getState()) {
                properties = properties + "Repeat = true";
                properties = properties + "RepeatValue = " + numbers.getText();
            } else {
                properties = properties + "Repeat = false";
            }
            
        }
    }
}
