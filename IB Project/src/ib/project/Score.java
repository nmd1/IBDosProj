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
    Label rightL, percentL, timeL, notif;
    java.io.File transfer;
    boolean boolt = false;
    int countTake, totalTimesTaken;
    
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
    
    
    public void scoring(java.io.File h, boolean t, int take, int total) {
        
        start.setVisible(false);

        scor.removeAll();
        scor = new JFrame();
        scor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scor.setLocationRelativeTo(null);
        scor.setResizable(true);
        scorerPane = scor.getContentPane();
        scorerPane.setLayout(layout);
        scor.setTitle("Your Results");
        scor.setSize(320,250);
        transfer = h;
        boolt = t;
        countTake = take;
        totalTimesTaken = total;
        setup();
    }
    
    public void Layout(Component c, int x, int y) {
        layout.putConstraint(SpringLayout.WEST, c,x, SpringLayout.WEST, scorerPane);
        layout.putConstraint(SpringLayout.NORTH, c,y,SpringLayout.NORTH, scorerPane);
    }
    public void setup() {
        Take t = new Take();
        Font f = new Font("Verdana", Font.PLAIN, 15);
        Font labf = new Font("Verdana", Font.PLAIN, 25);
        Dimension di = new Dimension(75,40);
        rightL = new Label("You got " + Qright + " questions right");
        if(rightL.getText().equals("You got 1 questions right")) {
            rightL.setText("You got 1 question right");
        }
        rightL.setFont(f);
        scorerPane.add(rightL);
        double total = RAnswer.size();
        double d = (Qright / total) * 100;
        percentL = new Label(d+"%");
        percentL.setFont(labf);
        scorerPane.add(percentL);
        
        notif = new Label();
        if(boolt) notif.setText("You ran out of time.");
        notif.setFont(f);
        scorerPane.add(notif);
        
        exitB = new Button("Exit");
        exitB.setPreferredSize(di);
        scorerPane.add(exitB);
        exitB.addActionListener(new exit());
        reTakeB = new Button("Retake Quiz");
        reTakeB.setPreferredSize(di);
        scorerPane.add(reTakeB);
        reTakeB.addActionListener(new back());//this right here
        mainMB = new Button("Main Menu");
        mainMB.setPreferredSize(di);
        scorerPane.add(mainMB);
        mainMB.addActionListener(new menu());
        
        if(totalTimesTaken < countTake + 1) {
            reTakeB.setEnabled(false);
            notif.setText("You can no longer retake the quiz");
            if(boolt) notif.setText("You ran out of time and \nyou can no longer retake the quiz.");
        }
        Layouts();
        scor.setVisible(true);
    }
    public void Layouts() {
        int x=0,y=0;
        Layout(exitB, 10, 165);
        Layout(rightL, 55, 10);
        Layout(notif, 55,35);
        Layout(percentL, 110, 80);
        Layout(reTakeB, 115, 165);
        Layout(mainMB,215, 165);
    }
    
    private class menu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            scor.setVisible(false);
            mainRun();
            main.setVisible(true);
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
            Take t = new Take();
            Qright = 0;
            t.Taking(transfer, countTake);
            ////////////
            
        }
    }
    
    public void printd(String a) {
        if(debug) System.out.println(a);
    }
}
