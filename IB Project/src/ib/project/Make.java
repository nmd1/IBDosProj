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
    Button nextQ, submit;
    Container buttonPane;
    Checkbox timer, repeat, tryA;
    Checkbox Ctwo, Cthree, Cfour, Cfive;
    Label Re = new Label(), Ti = new Label(), Nq = new Label(), Pc = new Label();
    SpringLayout layout = new SpringLayout();
    Label[] Empty = new Label[100];
    CheckboxGroup PerQuest = new CheckboxGroup();
    TextField QuestNum, PercentNum, a,b,c,d,e;
    TextArea Question;
    Choice s;
    int xc,yc;
    public Make() {
        prop.addMouseListener(new PanelListener());
    }
        private class PanelListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            xc = e.getX();
            yc = e.getY();
            System.out.println("(" + (xc - 8) + ", " + (yc - 30) + ")");
        }
    }
    
    
    public void QuizCreation() {
        prop.setVisible(true);
        main.setVisible(false);
        buttonPane = prop.getContentPane();
        //FlowLayout myLayout = new FlowLayout(FlowLayout.CENTER, 10,10); //lets focus on this
        buttonPane.setLayout(layout);
        ordered();   
        Buttons();
        Checkbox();
        Options();
        Textfield();
    }
    public void Layout(Component c, int x, int y) {
        layout.putConstraint(SpringLayout.WEST, c,x, SpringLayout.WEST, buttonPane);
        layout.putConstraint(SpringLayout.NORTH, c,y,SpringLayout.NORTH, buttonPane);
    }
    public void Layouts() {
        int x=0,y=0;
        Layout(timer, x+50, y+10); Layout(Ti, x+130, y+14);
        Layout(repeat, x+230, y+10); Layout(Re, x+355, y+14);
        Layout(Ctwo, x+150, y+40);
        Layout(Cthree, x+200, y+40);
        Layout(Cfour, x+250, y+40);
        Layout(Cfive, x+300, y+40);
        Layout(QuestNum, x+170, y+75);Layout(Nq, x+40, y+75);
        Layout(PercentNum, x+425, y+75);Layout(Pc, x+220, y+75);
        Layout(nextQ, x+185, y+115);
        
        
        int xx = -10;
        int yy = 60;
        Layout(a, xx+80, yy+225);
        Layout(b, xx+80, yy+260);
        Layout(c, xx+80, yy+285);
        Layout(d, xx+80, yy+310);
        Layout(e, xx+80, yy+335);
        Layout(Question, 70, 180);
    }
    public void ordered() {
        for(Label i : Empty) {i = new Label();}
        timer = new Checkbox("Timed?");buttonPane.add(timer);buttonPane.add(Ti);
        repeat = new Checkbox("Repeat Exam?"); buttonPane.add(repeat);buttonPane.add(Re);
        Ctwo = new Checkbox("two",false,PerQuest);buttonPane.add(Ctwo);
        Cthree = new Checkbox("three",false,PerQuest);buttonPane.add(Cthree);
        Cfour = new Checkbox("four",false,PerQuest);buttonPane.add(Cfour);
        Cfive = new Checkbox("five",false,PerQuest);buttonPane.add(Cfive);
        QuestNum = new TextField("", 2);buttonPane.add(Nq);
        buttonPane.add(QuestNum);
        PercentNum = new TextField("",1); buttonPane.add(Pc);
        buttonPane.add(PercentNum);
        nextQ = new Button("Next");buttonPane.add(nextQ);
        a = new TextField("", 45); buttonPane.add(a);
        b = new TextField("", 45); buttonPane.add(b);
        c = new TextField("", 45); buttonPane.add(c);
        d = new TextField("", 45); buttonPane.add(d);
        e = new TextField("", 45); buttonPane.add(e);
        Question = new TextArea(5, 46);
        //Question.setSize(new Dimension(23,23)); 
        System.out.print("This is ");
        Question.setRows(5);buttonPane.add(Question);
        System.out.println("Sparta.");
        Layouts();
        setState(true);
    }
    
    public void Buttons() {
        nextQ.setPreferredSize(new Dimension(100,40));
        nextQ.addActionListener(new prop()); 
    }
    public void Checkbox() {
        theIL(timer, Ti, "How long (In Minutes) should the quiz last", "Time");
        theIL(repeat, Re, "Input Amount of Times to repeat","Repeat"); 
        Ti.setPreferredSize(new Dimension(100,15));
        Re.setPreferredSize(new Dimension(100,15));

    }
    public void theIL(final Checkbox but, final Label l, final String ask, final String title) {
        
        but.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if (e.getStateChange() == ItemEvent.SELECTED) { //if the checkbox is selected
                    String wait = ""; //creates a empty string to write to
                    if (debug == true) System.out.println("True line reached"); //debug
                    boolean breaks = true; //to stop the while loop
                    while(breaks) { //keep looping until the input is correct
                        String response; //the input from the question box
                        response = JOptionPane.showInputDialog(null, 
                                ask, title, JOptionPane.QUESTION_MESSAGE); //gets the response
                        if ((response != null) && (response.length() > 0)) { //tests if the response exists
                            if(isNum(response)) { //if it does, tests if its a number
                                if(response.length() <= 4) {
                                    System.out.println("9NHQH-4939B-P3RHX-XYC28-29WKB");
                                    wait = response; //if its a number, the empty string at the top becomes that number
                                    breaks = false; //stops the loop
                                } else { //if for some reason its not less than 4 or greater than 4
                                    continue; //ask again to prevent some weird logic error
                                }
                                if(response.contains("-")){
                                    System.out.println("Why does this not work?");
                                    wait = response.replace("-", "");
                                }
                                if(response.length() > 4){
                                    wait = "9999"; //by default the value will be 9999
                                }
                            }
                        } else { //if its empty
                            but.setState(false); //uncheck the checkbox
                        }   
                        if(null == response) { //checks if the cancel is clicked
                            but.setState(false); //if it is, uncheck the checkbox
                            breaks = false; //and leave the loop
                        }
                    } //end of loop
                    
                    l.setVisible(true); //set the label to visible
                    //JOptionPane.show
                    l.setText(title + ": " +wait); //change the text of the label to the resonse
                    } else { //if the checkbox is unselected
                    if (debug == true) System.out.println("False line reached");
                    l.setVisible(false); //remove the label
                    }   
           }
        });
        
    }
    public boolean isNum(String s)  {  
        try {  
            double temp = Double.parseDouble(s);  
        }  
        catch(NumberFormatException e) {  
            return false;  
        }  
        return true;  
    }
   
    public void Options() {
        //little to do with option buttons... may delete...
    }
    public void Textfield() {
        Nq.setText("Numer of Questions:");
        Pc.setText("Percent of Questions to be asked:");
        QuestNum.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int tempI = ke.getKeyChar();
                boolean tempB = tempI == KeyEvent.VK_LEFT ||
                        tempI == KeyEvent.VK_RIGHT || tempI == 8;
                if( (tempI < 48 || tempI > 57) && !tempB) { 
                    ke.consume();//only numbers and backspace can be typed
                }
                
                if(QuestNum.getText().length() > 2) { 
                    ke.consume();//limits to 3 digits
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }
 
           @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        
        PercentNum.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int tempI = ke.getKeyChar();
                boolean tempB = tempI == KeyEvent.VK_LEFT ||
                        tempI == KeyEvent.VK_RIGHT || tempI == 8;
                if( (tempI < 48 || tempI > 57) && !tempB) { 
                    ke.consume();//only numbers and backspace can be typed
                }
                
                if(PercentNum.getText().length() > 1 && Integer.parseInt(PercentNum.getText()) != 10) { 
                    ke.consume();//limits to 3 digits
                }
                if(PercentNum.getText().equalsIgnoreCase("10")) {
                    if(tempI < 48 || tempI > 48) {
                       ke.consume(); //the only three digit number you can type is 100
                    }
                }
                if(PercentNum.getText().equalsIgnoreCase("0")) {
                    ke.consume();
                }
                
                
            }   

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                int tempL;
                try{
                    tempL = Integer.parseInt(PercentNum.getText());
                } catch (NumberFormatException e) {
                    if (debug = true) System.out.println(e);
                    PercentNum.setText("");
                    tempL = 0;
                }
                if(tempL >= 100) {
                    ke.consume();
                }
                if(tempL < 0) {
                    tempL = Math.abs(tempL); 
                    String tempS = "" + tempL;
                    PercentNum.setText(tempS);
                }
                if(tempL == 0) System.out.println("Can't do that");
            }
    });
    }
    private class prop implements ActionListener {
        //When Next is presed, go to the next screen and place all the properties
        //in the properties string.
        @Override
        public void actionPerformed(ActionEvent ae) {//turn this into an arrayList
            if(debug == true) System.out.println(ae.paramString());
            if(nextQ.getLabel().equalsIgnoreCase("next")) {
                
            
            properties.clear();
            if (debug = true) System.out.println("Timer is " + timer.getState());
            
            if(timer.getState()) {
               properties.add("Timer = true");
               properties.add("TimerValue = " + Ti.getText());
            } else {
                properties.add("Timer = false");
            }
            
            if (debug = true) System.out.println("Repeat is " + repeat.getState() + " With value " + Re.getText());
            if(repeat.getState()) {
                properties.add( "Repeat = true");
                properties.add("RepeatValue = " + Re.getText());
            } else {
                properties.add("Repeat = false");
            }
            
            if(PerQuest.getSelectedCheckbox() == Ctwo) properties.add("PerQuest = 2");
            if(PerQuest.getSelectedCheckbox() == Cthree) properties.add("PerQuest = 3");
            if(PerQuest.getSelectedCheckbox() == Cfour) properties.add("PerQuest = 4");
            if(PerQuest.getSelectedCheckbox() == Cfive) properties.add("PerQuest = 5");
            
            properties.add("QuestNum = " + QuestNum.getText());
            if(!PercentNum.getText().equalsIgnoreCase("") || !PercentNum.getText().equalsIgnoreCase("0"))
                properties.add("PercentNum = " + PercentNum.getText());
            else
                properties.add("PercentNum = 100");
            
            if (debug = true) System.out.println("\nPROPERTIES" + properties);            
            QuestionsIn();
            /*
            0 refers to the If there is a timer
            1 refers to the timer value (may not exist)
            2 refers to the If there is a repeat
            3 refers to the Repeat value
            4 refers to how many options will be in each question of the quiz
            */
            } else if(nextQ.getLabel().equalsIgnoreCase("back")) {
                setState(true);
                prop.setSize(500, 200);
                nextQ.setLabel("Next");
                //erase info?
            }
        }
    }
    
    public void setState(boolean state) {
            timer.setEnabled(state);
            repeat.setEnabled(state);
            QuestNum.setEnabled(state);
            PercentNum.setEnabled(state);
            Ctwo.setEnabled(state);
            Cthree.setEnabled(state);
            Cfour.setEnabled(state);
            Cfive.setEnabled(state);
            Ti.setEnabled(state);
            Re.setEnabled(state);
            Pc.setEnabled(state);
            Nq.setEnabled(state);
            
            a.setEnabled(!state);
            b.setEnabled(!state);
            c.setEnabled(!state);
            d.setEnabled(!state);
            e.setEnabled(!state);
            Question.setEnabled(!state);
    }
    
    public void QuestionsIn() {
        setState(false);
        nextQ.setLabel("Back");
        prop.setSize(500,550);
    }
}
