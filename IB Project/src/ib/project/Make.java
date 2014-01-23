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
                    if (debug == true) System.out.println("True line reached");
                    String Options[] = {"Ok"};
                    ImageIcon a = new ImageIcon();
                            final JOptionPane optionpane = new JOptionPane(JOptionPane.showInputDialog(null,
                            "Input Amount of Times to repeat",
                            "Repeat",JOptionPane.QUESTION_MESSAGE,
                            a,null, null));
                            
                    //===========Stop a bad input==========
                    Frame ab = new Frame();
                    final JDialog dialog = new JDialog(ab, "A Number", true);
                    dialog.setContentPane(optionpane);
                    dialog.setDefaultCloseOperation(
                        JDialog.DO_NOTHING_ON_CLOSE);
                        dialog.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                               if(debug == true) System.out.println("Thwarted user attempted to close window.");
                            }
                        });
                    optionpane.addPropertyChangeListener(
                    new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        String prop = e.getPropertyName();

                        if (dialog.isVisible() 
                        && (e.getSource() == optionpane)
                        && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                            //If you were going to check something
                            //before closing the window, you'd do
                            //it here.
                            dialog.setVisible(false);
                            }
                        }
                    });
                    dialog.pack();
                    dialog.setVisible(true);   
                    
                    int value = ((Integer)optionpane.getValue()).intValue();
                    if (value == JOptionPane.YES_OPTION) {
                        if(debug == true) System.out.println("Good.");
                    } else if (value == JOptionPane.NO_OPTION) {
                        if(debug == true) System.out.println("Try using the window decorations "
                            + "to close the non-auto-closing dialog. "
                            + "You can't!");
}
                    //=======end========
                    Object repeatTime = optionpane;        
                    Re.setVisible(true);
                    
                    //JOptionPane.show
                    
                    Re.setText(repeatTime.toString());
                    } else {
                    if (debug == true) System.out.println("False line reached");
                    Re.setVisible(false);
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
            
            if (debug == true) System.out.println("Repeat is " + repeat.getState() + " With value " + Re.getText());
            if(repeat.getState()) {
                properties = properties + "Repeat = true";
                properties = properties + "RepeatValue = " + Re.getText();
            } else {
                properties = properties + "Repeat = false";
            }
            
        }
    }
}
