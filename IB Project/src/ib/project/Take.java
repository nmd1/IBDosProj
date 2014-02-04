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
    int xc, yc, Qnumber;
    
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
        
        s.setPreferredSize(new Dimension(300,40));
        takePane.add(s);
        
        layout();
    }
    public void layout() {
        int x = 50, y = 80;
        Layout(drop,170,70);
        Layout(getQuiz,100,30);
        Layout(propB, 230,30);
        Layout(s, 120, 150);
        Layout(next, 130, 180);
        
        
        Layout(a, x, y);
        Layout(b, x, y+30);
        Layout(c, x, y+60);
        Layout(d, x, y+90);
        Layout(e, x, y+120);
        Layout(Quest, 50, 20);
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
                start.setSize(500,500);
                takePane.removeAll();
                takePane.add(next);
                        takePane.add(a);
                        takePane.add(b);
                        takePane.add(c);
                        takePane.add(d);
                        takePane.add(e);
                next.setLabel("Questions Left");
                Font f = new Font("Verdana", Font.BOLD, 24);
                Quest.setFont(f);
                Quest.setPreferredSize(new Dimension(300, 100));
                layout();
                Layout(next, 130, 400);
                
                a.setVisible(true);
                b.setVisible(true);
                c.setVisible(true);
                d.setVisible(true);
                e.setVisible(true);
                
                a.setLabel("");
                b.setLabel("");
                c.setLabel("");
                d.setLabel("");
                e.setLabel("");
                        
                properties.get(0);
                Quest.setText(QuestA.get(0));
                RAnswer.get(0);
                
            }
        }
    }
    public String[] Rand(int i) {
        String a[] = new String[i];
        Random r = new Random(i);
        int rand = r.nextInt(i);
        for(int j = 0; j < a.length - 1; j++) {
            
        }
        return a;
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
