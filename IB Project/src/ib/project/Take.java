package ib.project;

import static ib.project.Main.*;
import java.awt.Button;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

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
    boolean screen;
    int xc, yc, Qnumber;
    int timerTime, repeatTime, perQuest, percent, left, right, count, subt;
    boolean timerV = false, repeatV = false;
    
    public Take() {
        if(debug) start.addMouseListener(new PanelListener());
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
        screen = true;
        
        
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
        a.setPreferredSize(new Dimension(300,40));
        b.setPreferredSize(new Dimension(300,40));
        c.setPreferredSize(new Dimension(300,40));
        d.setPreferredSize(new Dimension(300,40));
        e.setPreferredSize(new Dimension(300,40));
        
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
        Layout(Quest, 50, 10);
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
                    if(a.getName().endsWith("Data.txt")) {
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
           String[] ArrayString2, ArrayString = everything.split("\n");
           ArrayString[0] = ArrayString[0].replaceFirst("\\[ ", "{");
           int l = ArrayString.length - 1; //the WAnswers
           ArrayString[l] = ArrayString[l].replaceFirst("\\[", "{");
           ArrayString[l] = ArrayString[l].replaceAll("],", "] |");
           
           //replace last
           StringBuilder b = new StringBuilder(ArrayString[l]);
           b.replace(ArrayString[l].lastIndexOf("]"), ArrayString[l].lastIndexOf("]") + 1, "}" );
           ArrayString[l] = b.toString();
           //replace last end
           ArrayString[l] = betterReplace(ArrayString[l], 1);
           
           String[] wrongAnswers = ArrayString[l].split("\\| ");
           
           System.out.println( ArrayString[l] );
           int i = 0;
           for(String a: ArrayString){
               if(i == l) break;
                    ArrayString[i] = betterReplace(a, 0); //YESYEYSYEYSYYYEYYSYEYSYEYES
                    //ArrayString[i] = a.replaceAll("]", "");
                    System.out.println(ArrayString[i]);
               i++;
           }
           if (debug)
           for(String a: wrongAnswers) {
               System.out.println(a);//here's an idea, do method chaining
           }
           //Properties
            System.out.println(ArrayString[0]);
           String scan = betterReplace(ArrayString[0], 0);
           timerV = scan.contains("Ttrue");
           if(timerV) {
               String x = scan.substring(scan.indexOf("e: ") + 1);
                x = x.substring(0, x.indexOf(","));
                x = betterReplace(x , 2);
                timerTime = Integer.parseInt(x);
               System.out.println(x);
           }
           repeatV = scan.contains("Rtrue");
           if(repeatV){
               String x = scan.substring(scan.indexOf("t: ") + 1);
               x = x.substring(0, x.indexOf(","));
               x = betterReplace(x , 2);
               repeatTime = Integer.parseInt(x);
               System.out.println(x);
           }
           if(scan.contains("PQ5")) perQuest = 5;
           if(scan.contains("PQ4")) perQuest = 4;
           if(scan.contains("PQ3")) perQuest = 3;
           if(scan.contains("PQ2")) perQuest = 2;
               
               String x = scan.substring(scan.indexOf("= ") + 1);
               x = x.substring(0, x.indexOf(","));
               x = betterReplace(x , 2);
               repeatTime = Integer.parseInt(x);
               Qnumber = Integer.parseInt(x);
               System.out.println(x);
           
               
              // x = scan.replaceAll(".*%", "");
               x = scan.substring(scan.indexOf("%") + 1);
               x = x.substring(0, x.indexOf("%"));
               x = betterReplace(x , 2);
               percent = Integer.parseInt(x);
               System.out.println(x);
           //
           
           ArrayString2 = ArrayString[l].split("\\|"); //splits up wrong answers
           //spitting this up into that mutidimentional list
           ArrayList<String> tempList = new ArrayList<String>();
           i = 0;
           WAnswer.clear();
           String[] tempA;
           for(String a: ArrayString2) {
                tempA = ArrayString2[i].split(",");
                Collections.addAll(tempList, tempA);
                WAnswer.add(i, tempList);
                i++; //fix ize problem
           }
           /*try {
               while(true) {
                WAnswer.remove(perQuest);
               }
           } catch(IndexOutOfBoundsException e){
               System.out.println(WAnswer);
           }*/
           
           //Done. now for the other two.
           QuestA.clear();
           tempA = ArrayString[1].split(",");
           Collections.addAll(QuestA, tempA);
           RAnswer.clear();
           tempA = ArrayString[2].split(",");
           Collections.addAll(RAnswer, tempA); 
           //done.
           
           //now for the properties.
           System.out.println("WAnswer:" + WAnswer);
           System.out.println("RAnswer:" + RAnswer);
           System.out.println("Questions:" + QuestA);
           
           if(debug){
           if(Qnumber != RAnswer.size()) {
               System.out.println("MisMatch Error: Qnumb & RightSize");
               System.out.println("Qnumb:" + Qnumber + " RightSize:" + RAnswer.size());
           }
           if(WAnswer.size() != RAnswer.size()) {
               System.out.println("MisMatch Error: WrongSize & RightSize");
               System.out.println("WrongSize:" +  WAnswer.size() + " RightSize:" + RAnswer.size());
           }
           if(WAnswer.size() != Qnumber) {
              System.out.println("MisMatch Error: Qnumb & Wrongsize");
              System.out.println("Qnumb:" + Qnumber + " WrongSize:" +  WAnswer.size());
           }
           }
           System.out.println("Test:" + WAnswer.size());
           
           Qnumber = RAnswer.size();
           //perQuest
           
           
           
           s.setText("Loading Quiz was scucess: Quiz Ready"); //if not sucess, prevent next from appearing
           subt = 0;
           next.setEnabled(true);
        }  
    }
    public String betterReplace(String str, int i) {
        String token = str;
        StringBuffer s = new StringBuffer(token.length());

        CharacterIterator it = new StringCharacterIterator(token);
        for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next()) {
            if(i == 0) {
            switch (ch) {
                
                case '[':
                    s.append("");
                    break;
                case ']':
                    s.append("");
                    break;
                case '\n':
                    s.append("");
                    break;
                default:
                    s.append(ch);
                    break;
                }
            }
            if(i == 1) {
                switch (ch) {
                
                case '{':
                    s.append("");
                    break;
                case '}':
                    s.append("");
                    break;
                default:
                    s.append(ch);
                    break;
                }
            }
            if(i == 2) {
                switch (ch) {
                
                case ':':
                    s.append("");
                    break;
                case ' ':
                    s.append("");
                    break;
                default:
                    s.append(ch);
                    break;
                }
            }
        }

        token = s.toString();
        return token;
    }
    public void quizSetup() {
        screen = false;
        System.out.println("went into quiz setup");
        
        start.setSize(500,500);
        takePane.removeAll();
        takePane.add(next);
            takePane.add(a);
            takePane.add(b);
            if(perQuest >= 3) takePane.add(c);
            if(perQuest >= 4) takePane.add(d);
            if(perQuest == 5) takePane.add(e);
            takePane.add(Quest);
            
            
            //e.setLabel(WAnswer.get(4).get(0));
            a.setVisible(true);
            b.setVisible(true);
            if(perQuest >= 3) c.setVisible(true);
            if(perQuest >= 4) d.setVisible(true);
            if(perQuest == 5) e.setVisible(true);
            
            Quest.setVisible(true);
            
        //next.setLabel(Qnumber + " Questions Left");
        Font f = new Font("Verdana", Font.BOLD, 14);
        Quest.setFont(f);
        Quest.setPreferredSize(new Dimension(300, 100));
        layout();
        Layout(next, 130, 400);
        printd("Quizsetupbeforecount: " + count);
        count = Qnumber;
        
        qSetup(0);
        printd("QuizsetupAFTERcount: " + count);
                //properties.get(0);
                /*
                
                
                long seed = System.nanoTime();
                Collections.shuffle(QuestA, new Random(seed));
                Collections.shuffle(RAnswer, new Random(seed));
                Collections.shuffle(WAnswer, new Random(seed));
                
                /* store a set of a's a set of b's a set of c's....
                store a for #1, a for #2, a for #3
                so basically every time you move on to a next question,
                randomize the array lists in the array.
                then go to that coresponding question number in that array list.
                BAM.
   A's a array  [][][][][]
             B  [][][][][]
             C  [][][][][]
             D  [][][][][]
                1 2 3 4 5
                */
    }
    
    private class Move implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //load();
            //Qnumber is the number of Questions left
            if(screen) quizSetup();
            else {
                printd("Count HERE IS: " + count);
            if(count <= 0) {
                maining();//the final screen
            } else {
                /*a.setLabel(WAnswer.get(0).get((WAnswer.get(0).size() - 1) - ntemp));
                c.setLabel(RAnswer.get((RAnswer.size() - 1) - ntemp));
                b.setLabel(WAnswer.get(1).get((WAnswer.get(1).size() - 1) - ntemp));
                d.setLabel(WAnswer.get(2).get((WAnswer.get(2).size() - 1) - ntemp));
                e.setLabel(WAnswer.get(3).get((WAnswer.get(2).size() - 1) - ntemp)); */
                printd("The Question BEFORE Number is " + (Qnumber - count));
                qSetup(Qnumber - count); printd("The Question Number is " + (Qnumber - count));

            }
            }
        }
    }
    /*public String[] Rand(int i) {
        String a[] = new String[i];
        Random r = new Random(i);
        int rand = r.nextInt(i);
        for(int j = 0; j < a.length - 1; j++) {
            
        }
        return a;
        Main asdf = new Main();
   // asdf.maining();
    
    } */
    public void load() {
        maining();
    }
    public void qSetup(int i) {
        //WAnswer.get(0).size() = how many answer choices there are
        //WAnswer.size() = how many questios there are
        printd("Went into qSetup");
        int ntemp = Qnumber - count;
        
        //special spefications for the button
        
        next.setLabel( (count - 1) + " Questions Left");
        if(next.getLabel().equals("1 Questions Left"))
            next.setLabel("1 Question Left");
        if(next.getLabel().equals("0 Questions Left"))
            next.setLabel("Done with Quiz");
        
        if(RAnswer.get(i) != null)
        a.setLabel(RAnswer.get(i));
        else a.setLabel("Error: Doesn't Exist");
        
        if(WAnswer.get(0).get((WAnswer.get(0).size() - 1) - ntemp) != null)
        b.setLabel(WAnswer.get(0).get((WAnswer.get(0).size() - 1) - ntemp)); 
        else b.setLabel("Error: Doesn't Exist");
        
        if(perQuest >= 3) {
        if(perQuest >= 4) {
        if(perQuest == 5) {
            if(WAnswer.get(3).get((WAnswer.get(3).size() - 1) - ntemp) != null)
            e.setLabel(WAnswer.get(3).get((WAnswer.get(3).size() - 1) - ntemp)); 
            else e.setLabel("Error: Doesn't Exist");
        }
            if(WAnswer.get(2).get((WAnswer.get(2).size() - 1) - ntemp) != null)
            d.setLabel(WAnswer.get(2).get((WAnswer.get(2).size() - 1) - ntemp)); 
            else d.setLabel("Error: Doesn't Exist");
        }
            
            if(WAnswer.get(1).get((WAnswer.get(1).size() - 1) - ntemp) != null)
            c.setLabel(WAnswer.get(1).get((WAnswer.get(1).size() - 1) - ntemp)); 
            else c.setLabel("Error: Doesn't Exist"); 
        }
        Quest.setText(QuestA.get(i));
       if(count > 0) count = (count - 1);

    }
    public void quiztaking() {
    
    }
    public void end(){
        
    }
    public void printd(String a) {
        if(debug) System.out.println(a);
    }
}

