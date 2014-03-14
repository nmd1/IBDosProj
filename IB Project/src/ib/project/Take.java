package ib.project;

import static ib.project.Main.*;
import ib.project.Score.*;
import java.awt.Button;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.io.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Take {
    //This is it.. the last file. Probably the biggest.
    //anyways, global variables
    Container takePane;
    SpringLayout layout = new SpringLayout();
    Choice drop = new Choice();
    Button getQuiz, propB, next;
    JFileChooser fc = new JFileChooser();
    java.io.File old, trans;
    ArrayList<java.io.File> theFiles;String everything;
    JLabel s = new JLabel(), Quest = new JLabel();
    Checkbox a, b, c, d, e;
    Date startDate;
    CheckboxGroup Ans = new CheckboxGroup();
    boolean screen, boolt;
    int xc, yc, Qnumber;
    int timerTime, repeatTime, perQuest, percent, count, hour, minute, takenTimes;
    boolean timerV = false, repeatV = false, secondT = true;
    
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
        scor.setVisible(false);
        takePane = start.getContentPane();
        takePane.setLayout(layout);
        takenTimes = 0;
        setup();
        screen = true;   
    }
    public void Taking(java.io.File f, int count) {
        start.setVisible(true);
        main.setVisible(false);
        prop.setVisible(false);
        save.setVisible(false);
        scor.setVisible(false);
        takePane = start.getContentPane();
        takePane.setLayout(layout);//
        secondT = false;
        Qnumber = 0;
        screen = true;
        old = f;
        takenTimes = count;
        restart();
        //start.setResizable(false);
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
        //continue with this second T
        
        
        next = new Button("Take Quiz");
        takePane.add(next);
        next.addActionListener(new Move());
        next.setEnabled(false);
        next.setPreferredSize(new Dimension(200,40));
        
        if(secondT) { //dont run the second time
        propB = new Button("Load Info");
        takePane.add(propB);
        propB.addActionListener(new Iload());
        propB.setPreferredSize(di);
        propB.setEnabled(false);
        }
        
        getQuiz = new Button("Open Folder");
        takePane.add(getQuiz);
        getQuiz.addActionListener(new File());
        getQuiz.setPreferredSize(di);
        
        
        
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
        int x = 50, y = 100;
        if(secondT) {
        Layout(propB, 230,30);
        Layout(getQuiz,100,30);
        Layout(s, 120, 150);
        Layout(drop,170,70);
        }
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
                        if(! a.getName().equals("Data.txt"))
                            drop.addItem(a.getName().replace("Data.txt", ""));
                        else
                            drop.addItem("Data");
                        propB.setEnabled(true);
                        theFiles.add(a);
                    }
                }
            }
        }
    }
    
    public void start() { //ADD TONNES OF TRY-CATCH STATEMENTS
        boolean startWell = true;
        System.out.println("");
           if(secondT) getQuiz.setEnabled(false);
           drop.setEnabled(false);
           //get the properties
           java.io.File quizProp;
           if(secondT) {
                quizProp = theFiles.get(drop.getSelectedIndex());
                trans = quizProp;
           } else {
                quizProp = old;
                trans = quizProp;
           }
           BufferedReader br;
           try {
                br = new BufferedReader(new FileReader(quizProp));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine(); //get each line

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine(); // read each line
                }
                everything = sb.toString(); //add it into 'everything'
                br.close();
           } catch(IOException e) {
                System.out.println(e);
                s.setText("Error: Could not read file");
                startWell = false;
                
           } catch(NullPointerException e){
               System.out.println(e);
                //("Error: Does not Exist");
                startWell = false;
           }
           
           //try
           everything = everything.replaceAll("QUIZ Data", ""); //get rid of the title
           everything = everything.trim(); //remove whitesapce
           String[] ArrayString2, ArrayString = everything.split("\n"); //seperate everything line by line
           
           //replace the first brackets
           ArrayString[0] = ArrayString[0].replaceFirst("\\[ ", "{"); //change the outer brackets to curly brackets
           int l = ArrayString.length - 1; //index of the WAnswers
           ArrayString[l] = ArrayString[l].replaceFirst("\\[", "{");//change the outer brackets to curly brackets
           ArrayString[l] = ArrayString[l].replaceAll("],", "] |");
           
           //replace the last brackets
           StringBuilder b = new StringBuilder(ArrayString[l]);
           b.replace(ArrayString[l].lastIndexOf("]"), ArrayString[l].lastIndexOf("]") + 1, "}" );//change the inner brackets to curly brackets
           ArrayString[l] = b.toString();
           //replace last end
           ArrayString[l] = betterReplace(ArrayString[l], 1);
           
           String[] wrongAnswers = ArrayString[l].split("\\| ");
           
           System.out.println( ArrayString[l] );
           int i = 0;
           for(String a: ArrayString){
               if(i == l) break;
                    ArrayString[i] = betterReplace(a, 0); //Finnaly replaces the brackets with nothing to be parced.
                    //ArrayString[i] = a.replaceAll("]", "");
                    System.out.println(ArrayString[i]);
               i++;
           }
           if (debug)
           for(String a: wrongAnswers) {
               System.out.println(a);//here's an idea, do method chaining
           }
           
           //catch 
           
           //Properties
           //try
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
           //catch
           
           //try
           if(repeatV){
               String x = scan.substring(scan.indexOf("t: ") + 1);
               x = x.substring(0, x.indexOf(","));
               x = betterReplace(x , 2);
               repeatTime = Integer.parseInt(x);
               System.out.println(x);
           }
           //catch
           
           //try
           if(scan.contains("PQ5")) perQuest = 5;
           if(scan.contains("PQ4")) perQuest = 4;
           if(scan.contains("PQ3")) perQuest = 3;
           if(scan.contains("PQ2")) perQuest = 2;
           //catch
           
           //try
           String x = scan.substring(scan.indexOf("= ") + 1);
           x = x.substring(0, x.indexOf(","));
           x = betterReplace(x , 2);
           Qnumber = Integer.parseInt(x);
           System.out.println(x);
           //catch
           
           //try
               
           // x = scan.replaceAll(".*%", "");
           x = scan.substring(scan.indexOf("%") + 1);
           x = x.substring(0, x.indexOf("%"));
           x = betterReplace(x , 2);
           percent = Integer.parseInt(x);
           System.out.println(x);
           //catch
           
           
           //try
           ArrayString2 = ArrayString[l].split("\\|"); //splits up wrong answers
           //spitting this up into that mutidimentional list
           ArrayList<String> tempList = new ArrayList<String>();
           i = 0;
           //WANSWER ADDING
           WAnswer.clear();
           String[] tempA, oneA = null, twoA = null, threeA = null, fourA = null;
           for(String a: ArrayString2) {
                tempA = ArrayString2[i].split(",");
                
                //======removing brackets========
                int n = tempA.length - 1;
                //this is for the [
                String[] temperA = tempA[0].split(""); //an array of each individual character 
                ArrayList<String> list = new ArrayList(Arrays.asList(temperA));
                int ind = 0;
                //gets rid of the brackets once and for all
                while (ind < list.size()) {
                    if(list.get(ind).equals("[") || list.get(ind).equals("]"))  {
                        // Remove item
                        list.remove(ind);
                        list.add(ind, "");
                        break;
                    } else {
                        ++ind;
                    }
                }
                temperA = list.toArray(temperA);
                tempA[0] = arrayToString(temperA);
                
                //now for the ]
                temperA = tempA[n].split("");
                list = new ArrayList(Arrays.asList(temperA));
                ind = 0;
                while (ind < list.size()) {
                    if(list.get(ind).equals("[") || list.get(ind).equals("]"))  {
                        // Remove item
                        list.remove(ind);
                        list.add(ind, "");
                        break;
                    } else {
                        ++ind;
                    }
                }
                temperA = list.toArray(temperA);
                tempA[n] = arrayToString(temperA);
                // =====END removing brackets======
                
            //Collections.addAll(tempList, tempA);
           if(i == 0) oneA = tempA;
           if(i == 1) twoA = tempA;
           if(i == 2) threeA = tempA;
           if(i == 3) fourA = tempA;
           i++; 
           }
           ArrayList<String> temp =  new ArrayList<String>(Arrays.asList(oneA));
           WAnswer.add(0, temp);
           if(perQuest >= 3){
               temp = new ArrayList<String>(Arrays.asList(twoA));
               WAnswer.add(1, temp);
           }
           if(perQuest >= 4){
               temp = new ArrayList<String>(Arrays.asList(threeA));
               WAnswer.add(2, temp);
           }
           if(perQuest == 5){
               temp = new ArrayList<String>(Arrays.asList(fourA));
               WAnswer.add(3, temp);
           }
           //END WANSWER ADDING
           
           //catch
           
           //try
           QuestA.clear();
           tempA = ArrayString[1].split(",");
           Collections.addAll(QuestA, tempA);
           RAnswer.clear();
           tempA = ArrayString[2].split(",");
           Collections.addAll(RAnswer, tempA); 
           //catch //final
           
           //now for the properties.
           System.out.println("WAnswer:" + WAnswer);
           System.out.println("RAnswer:" + RAnswer);
           System.out.println("Questions:" + QuestA);
           
           if(debug){
           if(Qnumber != RAnswer.size()) {
               System.out.println("MisMatch Error: Qnumb & RightSize");
               System.out.println("Qnumb:" + Qnumber + " RightSize:" + RAnswer.size());
           }
           if(WAnswer.get(0).size() != RAnswer.size()) {
               System.out.println("MisMatch Error: WrongSize & RightSize");
               System.out.println("WrongSize:" +  WAnswer.get(0).size() + " RightSize:" + RAnswer.size());
           }
           if(WAnswer.get(0).size() != Qnumber) {
              System.out.println("MisMatch Error: Qnumb & Wrongsize");
              System.out.println("Qnumb:" + Qnumber + " WrongSize:" +  WAnswer.get(0).size());
           }
           }
           System.out.println("Test:" + WAnswer.size());
           
           Qnumber = RAnswer.size();
           //perQuest
           
           
           
           s.setText("Loading Quiz was scucess: Quiz Ready"); //if not sucess, prevent next from appearing
           next.setEnabled(true); 
    }
    private class Iload implements ActionListener {
    @Override
         public void actionPerformed(ActionEvent ae) {
             start();
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
        double div = percent / 100;
        double newnumb = Qnumber * div;
        Qnumber = (int) Math.round(newnumb);
        hour  = Calendar.HOUR;
        minute = Calendar.MINUTE;
        printd("Hour & Minute: " + hour + ":" + minute);
        startDate = new Date();
        startDate.getTime();
        
        next.setEnabled(false);
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
        nextQuestion(0);
        printd("QuizsetupAFTERcount: " + count);
        a.addItemListener(new canMove());
        b.addItemListener(new canMove());
        c.addItemListener(new canMove());
        d.addItemListener(new canMove());
        e.addItemListener(new canMove());
        
    }
    private class canMove implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent ie) {
            if(a.getState() || b.getState() || c.getState() || d.getState() || e.getState())
            next.setEnabled(true);
        }
    }
    
    private class Move implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //if(secondT) 
            if(screen) quizSetup();
            else {
                printd("Count HERE IS: " + count);
                
                int currentHour = Calendar.HOUR;
                int currentMinute = Calendar.MINUTE;
                int totalHour = currentHour - hour;
                int totalMin = currentMinute - minute;
                int totalTime = (totalHour * 60) + totalMin;
                
                printd("Hour & Minute: " + currentHour + ":" + currentMinute);
                printd("Total H & R: " + totalHour + ":" + totalMin);
                printd("TotalTime: " + totalTime);
                boolean timeUp = false;
                if(timerTime < totalTime) {
                    timeUp = true; boolt = true;}
                
            if(count <= 0 || timeUp) {
                takenTimes = takenTimes + 1;
                leave();//the final screen
            } else {
                if(Ans.getSelectedCheckbox().getLabel().equalsIgnoreCase
                (RAnswer.get(Qnumber - count - 1)) ) {
                    Qright = Qright + 1;
                } else {
                    Qwrong = Qwrong + 1;
                }
                nextQuestion(Qnumber - count);
                if(Ans.getSelectedCheckbox() == null) next.setEnabled(false);
                
            }
            }
        }
    }
    public void leave() {
        Score s = new Score();
        s.scoring(trans, boolt, takenTimes, repeatTime);
        int useless = 1;
    }
    public void nextQuestion(int i) {
        //WAnswer.get(0).size() = how many answer choices there are
        //WAnswer.size() = how many questios there are
        printd("Went into qSetup");
        
        //special spefications for the button
        
        next.setLabel( (count - 1) + " Questions Left");
        if(next.getLabel().equals("1 Questions Left"))
            next.setLabel("1 Question Left");
        if(next.getLabel().equals("0 Questions Left"))
            next.setLabel("Done with Quiz");
        
        //End speical button speifications
        ArrayList<String> toRand= new ArrayList<String>();
        if(RAnswer.get(i) != null)
        toRand.add(RAnswer.get(i));
        else a.setLabel("Error: Something went wrong");
        
        if(WAnswer.get(0).get(i) != null)
        toRand.add(WAnswer.get(0).get(i)); 
        else b.setLabel("Error: Somthing went wrong");
        
        if(perQuest >= 3) {
            if(WAnswer.get(1).get(i) != null)
            toRand.add(WAnswer.get(1).get(i)); 
            else c.setLabel("Error: Doesn't Exist"); 
        }
        
        if(perQuest >= 4) {
            if(WAnswer.get(2).get(i) != null)
            toRand.add(WAnswer.get(2).get(i)); 
            else d.setLabel("Error: Doesn't Exist");
        }
        
        if(perQuest == 5) {
            if(WAnswer.get(3).get(i) != null)
            toRand.add(WAnswer.get(3).get(i)); 
            else e.setLabel("Error: Doesn't Exist");
        }
       Random r = new Random(Math.abs(System.nanoTime() * System.currentTimeMillis())); //this sounds awesome
       Collections.shuffle(toRand,r);
       
       
       for(int j = 0; j < toRand.size(); j++) {
           if(j == 0)a.setLabel(toRand.get(0));
           if(j == 1)b.setLabel(toRand.get(1));
           if(j == 2)c.setLabel(toRand.get(2));
           if(j == 3)d.setLabel(toRand.get(3));
           if(j == 4)e.setLabel(toRand.get(4));
           
       if(!secondT) start.setVisible(true);
       }
       
       
       Quest.setText("<html><p>"+QuestA.get(i)+"</p></html>");
       if(count > 0) count = (count - 1);

    }
    public void restart() {
        next = new Button("Take Quiz");
        takePane.add(next);
        next.addActionListener(new Move());
        next.setEnabled(false);
        next.setPreferredSize(new Dimension(200,40));
        takePane = start.getContentPane();
        takePane.setLayout(layout);
        a = new Checkbox("",false,Ans);takePane.add(a);
        b = new Checkbox("",false,Ans);takePane.add(b);
        c = new Checkbox("",false,Ans);takePane.add(c);
        d = new Checkbox("",true,Ans);takePane.add(d);
        e = new Checkbox("",true,Ans);takePane.add(e);
        Quest = new JLabel();
        start.setVisible(false);
        start();
        quizSetup();
    }
    public String arrayToString(String[] arr) {
        StringBuilder result = new StringBuilder();
        for (String arr1 : arr) {
            result.append(arr1);
        }
        String thisString = result.toString();
        return thisString;
    }
    public void printd(String a) {
        if(debug) System.out.println(a);
    }
}

