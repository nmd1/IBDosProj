package ib.project;
import static ib.project.Main.main;
import static ib.project.Main.prop;
import static ib.project.Main.properties;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;

public class Make {
    Button next;
    Container buttonPane;
    Checkbox timer;
    public Make() {
        
    }
    
    
    public void QuizCreation() {
        prop.setVisible(true);
        main.setVisible(false);
        buttonPane = prop.getContentPane();
        FlowLayout myLayout = new FlowLayout();
        buttonPane.setLayout(myLayout);
        buttons();
    }

    
    public void buttons() {
        next = new Button("Next");
        next.setPreferredSize(new Dimension(100,40));
        next.addActionListener(new more1());
        timer = new Checkbox("Timed?");
        timer.getState();
        buttonPane.add(next);
    }
    
    private class more1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(timer.getState()) {
               properties = properties + "Timer = true";
            }
        }
    public int timer(int t, boolean a) {
        
        return 0;
    }
    }
}
