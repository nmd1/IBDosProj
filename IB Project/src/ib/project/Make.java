package ib.project;
import static ib.project.Main.main;
import static ib.project.Main.prop;
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
    
    public Make() {
        
    }
    
    
    static void QuizCreation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void buttons() {
        prop.setVisible(true);
        main.setVisible(false);
        
        next = new Button("Make A Quiz");
        next.setPreferredSize(new Dimension(100,40));
        next.addActionListener(new more1());
    }
    
    
    
    
    
    
    
    
    private static class more1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //add next things
        }
    }
}
