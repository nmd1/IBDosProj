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

public class Make {
    Button nextQ, submit;
    Container buttonPane;
    Checkbox timer, repeat, tryA;
    Checkbox Ctwo, Cthree, Cfour, Cfive;
    Label Re = new Label(), Ti = new Label(), Nq = new Label(), Pc = new Label(),
            Ri = new Label(), On = new Label(), Tw = new Label(), Th = new Label(), 
                Fo = new Label(), Qu = new Label();
    SpringLayout layout = new SpringLayout();
    CheckboxGroup PerQuest = new CheckboxGroup();
    TextField QuestNum, PercentNum, a,b,c,d,e;
    TextArea Question;
    Choice s;
    int xc,yc,Qnumb;
    public Make() {
        if(debug == true) prop.addMouseListener(new PanelListener());
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
        save.setVisible(false);
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
        
        
        int xx = 40;
        int yy = 60;
        Layout(a, xx+80, yy+225);
        Layout(b, xx+90, yy+260);
        Layout(c, xx+90, yy+285);
        Layout(d, xx+90, yy+310);
        Layout(e, xx+90, yy+335);
        Layout(Question, 35, 180);
        Layout(Ri, xx-30, yy+225);
        Layout(On, xx-30, yy+260);
        Layout(Tw, xx-30, yy+285);
        Layout(Th, xx-35, yy+310);
        Layout(Fo, xx-30, yy+335);
        Layout(submit, x+160, 445);
    }
    public void ordered() {
        timer = new Checkbox("Timed?");buttonPane.add(timer);buttonPane.add(Ti);
        repeat = new Checkbox("Repeat Exam?"); buttonPane.add(repeat);buttonPane.add(Re);
        Ctwo = new Checkbox("two",false,PerQuest);buttonPane.add(Ctwo);
        Cthree = new Checkbox("three",false,PerQuest);buttonPane.add(Cthree);
        Cfour = new Checkbox("four",false,PerQuest);buttonPane.add(Cfour);
        Cfive = new Checkbox("five",true,PerQuest);buttonPane.add(Cfive);
        QuestNum = new TextField("", 2);buttonPane.add(Nq);
        buttonPane.add(QuestNum);
        PercentNum = new TextField("",1); buttonPane.add(Pc);
        buttonPane.add(PercentNum);
        nextQ = new Button("Next");buttonPane.add(nextQ);
        submit = new Button(""); buttonPane.add(submit);
        a = new TextField("", 45); buttonPane.add(a);
        b = new TextField("", 45); buttonPane.add(b);
        c = new TextField("", 45); buttonPane.add(c);
        d = new TextField("", 45); buttonPane.add(d);
        e = new TextField("", 45); buttonPane.add(e);
        Question = new TextArea(5, 55);buttonPane.add(Question);
        Ri.setText("The Right Answer:");buttonPane.add(Ri);
        On.setText("Wrong Answer One:");buttonPane.add(On);
        Tw.setText("Wrong Answer Two:");buttonPane.add(Tw);
        Th.setText("Wrong Answer Three:");buttonPane.add(Th);
        Fo.setText("Wrong Answer Four:");buttonPane.add(Fo);
        Qu.setText("Input Question Here:");
        Layouts();
        setState(true);
    }
    
    public void Buttons() {
        nextQ.setPreferredSize(new Dimension(100,40));
        nextQ.addActionListener(new Propr()); 
        submit.setPreferredSize(new Dimension(150,40));
        submit.addActionListener(new Sub());
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
    private class Propr implements ActionListener {
        //When Next is presed, go to the next screen and place all the properties
        //in the properties string.
        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean goahead =(!QuestNum.getText().equals("")) 
                    && (!PercentNum.getText().equals(""));
            if(goahead) {
            if(nextQ.getLabel().equalsIgnoreCase("next")) {
                
            
            properties.clear();
            if (debug = true) System.out.println("Timer is " + timer.getState());
            
            if(timer.getState()) {
               properties.add("Ttrue");
               properties.add("TV" + Ti.getText());
            } else {
                properties.add("Tfalse");
            }
            
            if (debug = true) System.out.println("Repeat is " + repeat.getState() + " With value " + Re.getText());
            if(repeat.getState()) {
                properties.add("Rtrue");
                properties.add("RV" + Re.getText());
            } else {
                properties.add("Rfalse");
            }
            
            if(PerQuest.getSelectedCheckbox() == Ctwo) properties.add("PQ2");
            if(PerQuest.getSelectedCheckbox() == Cthree) properties.add("PQ3");
            if(PerQuest.getSelectedCheckbox() == Cfour) properties.add("PQ4");
            if(PerQuest.getSelectedCheckbox() == Cfive) properties.add("PQ5");
            
            properties.add("QuestNum = " + QuestNum.getText());
            if(!PercentNum.getText().equalsIgnoreCase("") || !PercentNum.getText().equalsIgnoreCase("0"))
                properties.add("%" + PercentNum.getText() + "%");
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
                int choice = JOptionPane.showConfirmDialog(null, 
                        "Editing the previous settings will result in the current set of questions" +
                                " and answers being lost. \nAre you sure you want to proceed?", 
                        "Are you sure?", JOptionPane.YES_NO_OPTION); //yes == 0, no == 1
                if(choice == 0) {
                    setState(true);
                    prop.setSize(500, 200);
                    nextQ.setLabel("Next");
                    //erase info
                    a.setText("");b.setText("");d.setText("");c.setText("");
                    e.setText(""); Question.setText("");
                    QuestA.clear();
                    WAnswer.clear();
                    RAnswer.clear();
                    timer.setState(false);
                    Ti.setText("");Re.setText("");
                    repeat.setState(false);
                    QuestNum.setText("");
                    PercentNum.setText("");
                    submit.setLabel("");
                } else {
                    //do nothing.
                }
            }
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
        
        if(Ctwo.getState()) {
            c.setEnabled(false);
            d.setEnabled(false);
            e.setEnabled(false);
            WAnswer.add(new ArrayList<String>());
        }if(Cthree.getState()) {
            d.setEnabled(false);
            e.setEnabled(false);
            WAnswer.add(new ArrayList<String>());
            WAnswer.add(new ArrayList<String>());
        }if(Cfour.getState()) {
            e.setEnabled(false);
            WAnswer.add(new ArrayList<String>());
            WAnswer.add(new ArrayList<String>());
            WAnswer.add(new ArrayList<String>());
        }if(Cfive.getState()) {
            WAnswer.add(new ArrayList<String>());
            WAnswer.add(new ArrayList<String>());
            WAnswer.add(new ArrayList<String>());
            WAnswer.add(new ArrayList<String>());
        }
        
        //WAnswer.get(0).add(4, "This is adding a string at row 0, column 4");
        Qnumb = Integer.parseInt(QuestNum.getText()) -1;
        if(Qnumb != 1 && Qnumb != 0){ 
            submit.setLabel(Qnumb + " Questions Left");  
        } else if(Qnumb == 0) {
            submit.setLabel("Move on to Next Screen");
        }
    }
    private class Sub implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean ready = false;
            boolean test = Question.getText().equals("") || a.getText().equals("") ||
                     b.getText().equals("")|| (c.getText().equals("") && c.isEnabled())|| 
                    (d.getText().equals("") && d.isEnabled()) || 
                    (e.getText().equals("") && e.isEnabled());
            //replace at one point with Listeners for each feild
            if(!test) { //if the fields have stuff in them
                if(Qnumb > 0) {
                    Qnumb = Qnumb - 1; 
                    submit.setLabel(Qnumb + " Questions Left");
                    if(Qnumb == 0) {
                        submit.setLabel("Move on to Next Screen");
                    }
                } else if(Qnumb == 0) {
                    ready = true;
                }
                QuestA.add(Question.getText());
                RAnswer.add(a.getText());
                WAnswer.get(0).add(b.getText());
                if(Cthree.getState()) {
                    WAnswer.get(1).add(c.getText());
                }
                if(Cfour.getState()) {
                    WAnswer.get(1).add(c.getText());
                    WAnswer.get(2).add(d.getText());
                }
                if(Cfive.getState()) {
                    WAnswer.get(1).add(c.getText());
                    WAnswer.get(2).add(d.getText());
                    WAnswer.get(3).add(e.getText()); 
                }
                
                Question.setText("");
                a.setText("");
                b.setText("");
                c.setText("");
                d.setText("");
                e.setText("");
                if(ready) {
                    if(debug == true)  System.out.println("QUESTIONS:" + QuestA + 
                        "\n" + "ANSWERS:" + RAnswer + "\n" + "WRONG:" + WAnswer);                          
                Save s = new Save();
                s.Saving(); //next File 
                }
            }
        }
    }
}
