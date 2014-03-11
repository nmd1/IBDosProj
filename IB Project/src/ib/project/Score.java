//woo last file :D
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

public class Score {
    Button exitB, reTakeB, mainMB;
    int xc, yc;
    Container scorerPane;
    SpringLayout layout = new SpringLayout();
    Label rightL, percentL, timeL;
    
    public Score() {
        scor.addMouseListener(new PanelListener());
    }
    private class PanelListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            xc = e.getX();
            yc = e.getY();
            printd("(" + (xc - 8) + ", " + (yc - 30) + ")");
        }
    }
    
    
    public void scoring() {
        scor.setVisible(true);
        start.setVisible(false);
        scorerPane = scor.getContentPane();
        scorerPane.setLayout(layout);
        setup();
    }
    
    public void Layout(Component c, int x, int y) {
        layout.putConstraint(SpringLayout.WEST, c,x, SpringLayout.WEST, scorerPane);
        layout.putConstraint(SpringLayout.NORTH, c,y,SpringLayout.NORTH, scorerPane);
    }
    public void setup() {
        Font f = new Font("Verdana", Font.PLAIN, 15);
        Dimension di = new Dimension(75,40);
        rightL = new Label("You got " + Qright + " Questions right");
        rightL.setFont(f);
        scorerPane.add(rightL);
        double total = RAnswer.size() - 1;
        double d = (Qright / total) * 100;
        percentL = new Label(d+"%");
        percentL.setFont(new Font("Verdana", Font.PLAIN, 25));
        scorerPane.add(percentL);
        exitB = new Button("Exit");
        exitB.setPreferredSize(di);
        scorerPane.add(exitB);
        exitB.addActionListener(new exit());
        reTakeB = new Button("Retake Quiz");
        reTakeB.setPreferredSize(di);
        scorerPane.add(reTakeB);
        reTakeB.addActionListener(new back());
        mainMB = new Button("Main Menu");
        mainMB.setPreferredSize(di);
        scorerPane.add(mainMB);
        mainMB.addActionListener(new menu());
        
        Layouts();
        
    }
    public void Layouts() {
        int x=0,y=0;
        Layout(exitB, 10, 165);
        Layout(rightL, 55, 0);
        Layout(percentL, 110, 80);
        Layout(reTakeB, 115, 165);
        Layout(mainMB,215, 165);
    }
    
    private class menu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Reset r = new Reset();
            r.destroy();
        }
    }
    private class exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    private class back implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Reset r = new Reset();
            Take t = new Take();
            //r.resetTake();
            t.Taking(false);
            ////////////
            
        }
    }
    
    public void printd(String a) {
        if(debug) System.out.println(a);
    }
}