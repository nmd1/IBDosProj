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

public class Take {
    //This is it.. the last file. Probably the biggest.
    //anyways, global variables
    Container takePane;
    SpringLayout layout = new SpringLayout();
    Choice drop;
    Button getQuiz, propB, next;
    JFileChooser fc = new JFileChooser();
    ArrayList<java.io.File> theFiles;String everything;
    Label s = new Label(), Quest = new Label();
    Checkbox a, b, c, d, e;
    CheckboxGroup Ans = new CheckboxGroup();
    boolean screen = true;
    int xc, yc;
    
    public Take() {
        if(debug == true) start.addMouseListener(new PanelListener());
    }
    private class PanelListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            xc = e.getX();
            yc = e.getY();
            System.out.println("(" + (xc - 8) + ", " + (yc - 30) + ")");
        }
    }
   
    public void Taking(){
        start.setVisible(true);
        main.setVisible(false);
        prop.setVisible(false);
        save.setVisible(false);
        takePane = start.getContentPane();
        takePane.setLayout(layout);
        setup();
        
    }
    public void Layout(Component c, int x, int y) {
        layout.putConstraint(SpringLayout.WEST, c,x, SpringLayout.WEST, takePane);
        layout.putConstraint(SpringLayout.NORTH, c,y,SpringLayout.NORTH, takePane);
    }
    public void setup(){
        Dimension di = new Dimension(100,40);
        drop = new Choice();
        drop.setPreferredSize(new Dimension(100,100));
        takePane.add(drop);
        
        getQuiz = new Button("Open Folder");
        takePane.add(getQuiz);
        getQuiz.addActionListener(new File());
        getQuiz.setPreferredSize(di);
        
        propB = new Button("Load Info");
        takePane.add(propB);
        propB.addActionListener(new Iload());
        propB.setPreferredSize(di);
        propB.setEnabled(false);
        
        next = new Button("Take Quiz");
        takePane.add(next);
        next.addActionListener(new Move());
        next.setEnabled(false);
        next.setPreferredSize(new Dimension(200,40));
        
        a = new Checkbox("",false,Ans);takePane.add(a);
        b = new Checkbox("",false,Ans);takePane.add(b);
        c = new Checkbox("",false,Ans);takePane.add(c);
        d = new Checkbox("",true,Ans);takePane.add(d);
        e = new Checkbox("",true,Ans);takePane.add(e);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        e.setVisible(false);
        
        takePane.add(s);
        
        layout();
    }
    public void layout() {
        int x = 0, y = 0;
        Layout(drop,100,100);
        Layout(getQuiz,100,50);
        Layout(propB, 230,50);
        Layout(s, 200, 100);
        Layout(next, 140, 240);
        
        
        Layout(a, x+150, y+40);
        Layout(b, x+200, y+40);
        Layout(c, x+250, y+40);
        Layout(d, x+300, y+40);
        Layout(e, x+300, y+40);
    }
    private class File implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            drop.removeAll();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setApproveButtonText("Open");
            fc.setApproveButtonMnemonic('S');
            fc.setApproveButtonToolTipText("Open the folder with the contained quiz files");
            int returnVal = fc.showOpenDialog(takePane);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                java.io.File file = new java.io.File(fc.getSelectedFile().getAbsolutePath()); //fileChooser.getSelectedFile();
                theFiles = new ArrayList<java.io.File>();
                java.io.File[] f = file.listFiles();
                if(f != null)
                for(java.io.File a : f){
                    if(a.getName().endsWith("DATA.txt")) {
                        drop.addItem(a.getName());
                        propB.setEnabled(true);
                        theFiles.add(a);
                    }
                }
            }
        }
    }
    private class Iload implements ActionListener { //fix more
    @Override
         public void actionPerformed(ActionEvent ae) {
           System.out.println("");
           getQuiz.setEnabled(false);
           drop.setEnabled(false);
           //get the properties
           java.io.File quizProp = theFiles.get(drop.getSelectedIndex());

           try {
                BufferedReader br = new BufferedReader(new FileReader(quizProp));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                everything = sb.toString();
                br.close();
           } catch(IOException e) {
                System.out.println(e);
           }
           
           everything = everything.replaceAll("QUIZ DATA", "");
           everything = everything.trim();
           String[] ArrayString = everything.split("\n");
           ArrayString[0] = ArrayString[0].replaceFirst("\\[ ", "{");
           int l = ArrayString.length - 1;
           ArrayString[l] = ArrayString[l].replaceFirst("\\[", "{");
           ArrayString[l] = ArrayString[l].replaceAll("],", "] |");
           
           //replace last
           StringBuilder b = new StringBuilder(ArrayString[l]);
           b.replace(ArrayString[l].lastIndexOf("]"), ArrayString[l].lastIndexOf("]") + 1, "}" );
           ArrayString[l] = b.toString();
           //replace last end
           ArrayString[l] = ArrayString[l].replace("\\{,", "");
           ArrayString[l] = ArrayString[l].replace("},", "");
           
           String[] wrongAnswers = ArrayString[l].split("\\|");
           
           System.out.println( ArrayString[l] );
           int i = 0;
           for(String a: ArrayString){
               if(i == l) break;
                    ArrayString[i] = a.replace("\\[h", ""); //FIX THIS NOW
                    ArrayString[i] = a.replaceAll("]", "");
                    System.out.println(ArrayString[i]);
               i++;
           }
           for(String a: wrongAnswers) {
               System.out.println(a);//here's an idea, do method changing
           }
           s.setText("Loading Quiz was scucess: Quiz Ready"); //if not sucess, prevent next from appearing
           
           next.setEnabled(true);
        }  
    }
    
    
    private class Move implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(screen){
                screen = false;
                takePane.removeAll();
                takePane.add(next);
                next.setLabel("Questions Left");
                
                a.setVisible(true);
                b.setVisible(true);
                c.setVisible(true);
                d.setVisible(true);
                e.setVisible(true);
                
                //working on this
            }
        }
    }
     
    public void load() {
        
    }
    public void qSetup() {
        
    }
    public void quiztaking() {
    
    }
    public void end(){
        
    }
}
