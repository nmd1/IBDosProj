package ib.project;
import static ib.project.Main.*;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Container;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
public class Reset {

    public Reset(){}
    
    /**
     * resets static variables used in the 'Main' file
     */
    public void resetMain() {
    main = null; prop = null; start = null; save = null; scor = null;
    pane = null;
    makeB = null; takeB = null; helpB = null;
    check = null;
    equations = null;
    //static String properties = null;
    properties = new ArrayList<String>();
    QuestA = new ArrayList<String>();
    RAnswer = new ArrayList<String>();
    WAnswer = new ArrayList<ArrayList<String>>();
    Qright = 0; Qwrong = 0;
    }

    /**
     * resets the static variables used in the 'Make' file
     */
    public void resetMake() {
        Make m = new Make();
    m.nextQ = null; m.submit = null;
    m.buttonPane = null;
    m.timer = null; m.repeat = null; m.tryA = null;
    m.Ctwo = null; m.Cthree = null; m.Cfour = null; m.Cfive = null;
    m.Re = new Label(); m.Ti = new Label(); m.Nq = new Label(); m.Pc = new Label();
    m.Ri = new Label(); m.On = new Label(); m.Tw = new Label(); m.Th = new Label(); 
    m.Fo = new Label(); m.Qu = new Label();
    m.layout = new SpringLayout();
    m.PerQuest = new CheckboxGroup();;
    m.QuestNum = null; m.PercentNum = null; m.a = null;m.b= null;m.c= null;m.d= null;m.e= null;
    m.Question = null;
    m.s = null;
    m.xc = 0;m.yc = 0;m.Qnumb = 0;
    }

    /**
     *resets the static variables used in the 'Save' file
     */
    public void resetSave() {
        Save s = new Save();
    s.SavetoText = null; s.SaveandTake = null; 
    s.TakeQuiz = null; s.leave = null; s.mainB = null;
    s.saverPane = null;
    s.fc = new JFileChooser();
    s.layout = new SpringLayout();
    s.Sucess = new Label();
    }

    /**
     *resets the static variables used in the 'Take' file
     */
    public void resetTake() {
        Take t = new Take();
    t.takePane = null;
    t.layout = null;
    t.drop = null;
    t.getQuiz = null; t.propB = null; t.next = null;
    t.fc = new JFileChooser();
    t.theFiles = null;
    t.everything = null;
    t.s = new Label(); t.Quest = new Label();
    t.a = null; t.b = null; t.c = null; t.d = null; t.e = null;
    t.Ans = new CheckboxGroup();
    t.screen = true;
    t.xc = 0; t.yc = 0; t.Qnumber = 0;
    t.timerTime = 0; t.repeatTime = 0; t.perQuest = 0; t.percent = 0; 
    t.left = 0; t.right = 0; t.count = 0; t.subt = 0;
    t.timerV = false; t.repeatV = false;
    }

    /**
     * resets the static variables used in the 'Main' file
     * Method not complete yet.
     */
    public void resetScore() {
        Score sc = new Score();
    }
    
    
    /////

    /**
     * resets all of the static variables used throughout the entire project
     * upon destroying the static variables it returns to the main screen.
     * it is the equivalent of restarting the program.
     * use wisely.
     */
        public void destroy() {
        //be wary of this method.
        
        resetMake();
        resetSave();
        resetTake();
        resetScore();
        resetMain();
        mainRun();
    }
    ////
}
