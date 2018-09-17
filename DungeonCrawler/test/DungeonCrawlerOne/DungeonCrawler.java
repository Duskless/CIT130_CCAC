/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerOne;

import static DungeonCrawlerOne.DungeonCrawler.player;
import static DungeonCrawlerOne.DungeonCrawler.ww;
import java.util.Random;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.xml.bind.*;

/**
 *
 * @author mfaux02
 */
public class DungeonCrawler {
    public static Player player;        //objects from oracle         //objects from oracle
    
    static String saveName;         //File Save name
    
    static WindowWorld ww;
    
    //add heal and rest options and equations
    //shift stats to DND stats
    //add death
    //add items
    
    public static void main(String[] args) throws IOException{
        ww = new WindowWorld();//create main Gui Window
        createPlayer();//generate Player Object
        ww.createChar();//create player save/load old player
    }//close main
    
    public static void sendToOutput(String txt){
        ww.sendOutput(txt);//main window text
    }//close sendToOutput
    
    public static void createPlayer() throws IOException{
        player = new Player();//initilize player object
        player.realizeCharacter();//setting max health + checking xp for levelup
        
        ww.updateStatus();//showing status on Gui
    }//close createPlayer
    
    public static void savePlayer() throws JAXBException, FileNotFoundException{
        JAXBContext jc = JAXBContext.newInstance( "com.acme.foo" );
        Unmarshaller u = jc.createUnmarshaller();
        Object element = u.unmarshal( new File( "foo.xml" ) );
        Marshaller m = jc.createMarshaller();
        
        OutputStream os = new FileOutputStream( player.name.concat(".xml") );
        m.marshal( element, os );
    
    }
}//close DungeonCrawler

class Combat extends Applet implements ActionListener{
    static Random randGen = new Random();               //Random Number Generator
    
    private Monster[] mon;      //monster array
    private int enemies;        //number of enemies
    private TextArea output;    //combat textOutput
    private TextArea[] status;  //status of monsters array
    
    @Override
    public void init(){
        Panel m = new Panel();
        m.setSize(this.getHeight(),this.getWidth()/2);
        Panel s = new Panel();
        s.setSize(this.getHeight(),this.getWidth()/2);
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(m);
        m.setBackground(Color.darkGray);
        this.add(s);
        s.setBackground(Color.darkGray);
        
        //this.setLayout(bl);
        
        createThreat();
        
        List menu = new List(10, false);
        
        menu.setFont(new Font("SansSerif", Font.PLAIN, 18));
        menu.add("Attack");
        menu.add("Double Attack");
        menu.add("Block");
        menu.add("Dodge");
        menu.add("Run");
        menu.addActionListener(this);
        menu.setSize(m.getSize());
        menu.setForeground(Color.blue);
        menu.setBackground(Color.black);
        
        m.add(menu);
        
        output = new TextArea("", 10, 40, TextArea.SCROLLBARS_NONE);
        output.setEditable(false);
        
        output.setForeground(Color.yellow);
        output.setBackground(Color.black);
        
        m.add(output);
        
        m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));
        
        
        
        status = new TextArea[enemies];
        
        for(int i = 0; i < enemies; i++){
            status[i] = new TextArea("Status", 5, 20, TextArea.SCROLLBARS_NONE); 

            status[i].setFont(new Font("SansSerif", Font.PLAIN, 18));

            status[i].setForeground(Color.yellow);
            status[i].setBackground(Color.black);
            status[i].setEditable(false);

            s.add(status[i]);
        }
    }//Applet overrided class creating a Combat Applet
    
    private void sendOutput(String txt){
        output.setText(txt);//Combat output text sender
    }//close sendOutput
    
    private void createThreat(){//setup threat generation
        //create enemies
        //needs to be generated randomly
        enemies = 1;//currently hardcoded
        mon = new Monster[enemies];
        mon[0] = new Monster(player.level);
    }//close createThreat
    
    public void updateStatuses(){
        ww.updateStatus();//update mainwindow status
        updateStatus();//update monsterStatus
    }//close updateStatuses
    
    public void updateStatus(){
        //for loop updating all monster statuses
        for(int i = 0; i < status.length; i++){
            String stat = "Monster Type: " + mon[i].name + "\n"
                            + "Monster Level: " + mon[i].level + "\n"
                            + "Monster Status: " + mon[i].getStatus() + "\n";
            if(mon[i].boss){
                stat = stat + "\n"
                        + "BOSS MONSTER";
            }
            status[i].setText(stat);
        }//close updateStatus
    }//close updateStatus
    
    public void doCombat(Monster mon){
        //needs method call for player vs monster exchanges
        if(mon.health < 1){
            sendOutput("Congratulations, you killed a " + mon.name + "!");
            sendOutput("You earned " + mon.exp + " Experieance Points!");
            sendOutput("You earned " + mon.coinage + " Coins!");
            player.exp += mon.exp;
            player.coinage += mon.coinage;
            player.monDestroyed ++;
        }
        if(player.health < 1){
            sendOutput("You have died");
            sendOutput("You have slain a total " + player.monDestroyed + " Monsters.");
            player.exp /= 10;
            player.coinage = 0;
        }
        
        player.realizeCharacter();
    }//close doCombat
    
    public boolean run(){
        double[] run = new double[mon.length];
        boolean escape = false;
        for(int i = 0; i < mon.length; i++){
            run[i] = (randGen.nextInt(76) + 25 + player.dex * .25 - player.level * 10) / (randGen.nextInt(51) + 20 + mon[i].dex * .25 - mon[i].level * 10);
            if(run[i] > 1){
                escape = true;
            }
        }
        return escape;
    }//close run
    
    public void escape(){
        //write escape code to close combat window
    }//close escape
    
    public void fight(Monster monster, int action){//rewrite fighting to use different/multiple enemies
        double doubleAttack = 0;
        double block = 0;
        double dodge = 0;
        switch(action){
            default:  player.stamina += player.sta * .25 + player.level;  
                if (player.stamina > player.staminaMax){
                    player.stamina = player.staminaMax;
                }
                break;    //attack
            case 2: if(player.stamina >= 30){
                    doubleAttack = (player.str * .5 - randGen.nextInt(player.level + 1));
                    player.stamina -= 30;
                }   
                else{
                    sendOutput("Not enough Stamina.");
                    sendOutput("30 Stamina Needed.");
                }
                break;          //heavy attack
            case 3:  if(player.stamina >= 35){
                    block = (player.str * .5 - randGen.nextInt(player.level + 1));
                    player.stamina -= 35;
                }   
                else{
                    sendOutput("Not enough Stamina.");
                    sendOutput("35 Stamina Needed.");
                }            
                break;          //block
            case 4: if(player.stamina >= 20){
                    dodge = (player.dex * .5 - randGen.nextInt(player.level + 5));
                    player.stamina -= 20;
                }   
                else{
                    sendOutput("Not enough Stamina.");
                    sendOutput("20 Stamina Needed.");
                }           
                break;          //counter
        }
        
        double toHit = (randGen.nextInt(76) + player.acc * .5) / (randGen.nextInt(51) + 25 + monster.dex * .5);
        double toDodge = (randGen.nextInt(76) + monster.acc * .5) / (randGen.nextInt(51) + 25 + player.dex * .5 + dodge);
        
        if(toHit >= 1){
            double damageGiven = randGen.nextInt(26) + player.str * .5 + doubleAttack;
            double critChance = randGen.nextInt(21)  + player.cri * .5 + 10 - player.level;
            if(critChance > 100){
                damageGiven *= (4 + player.cri * .05);
                sendOutput("Oh, A Critical Hit!");
                }//close if
            monster.health -= damageGiven;
            sendOutput("You dealt " + damageGiven + " damage");
        }//close if
        else{
            sendOutput("Missed.");
        }//close else
        if(toDodge >= 1){
            double damageTaken = randGen.nextInt(26) + player.str * .25 + 5 - player.level * .25 - block;
            double critChance = randGen.nextInt(21) + monster.acc * .25 - player.level * .25;
            if(critChance > 100){
                damageTaken *= 2;
                sendOutput("The " + monster.name + " Scored a Critical Hit!");
                }//close if
            player.health -= damageTaken;
            sendOutput(monster.name + " dealt " + damageTaken + " damage");
        }//close if
        else{
            sendOutput("The " + monster.name + " missed.");
        }//close else
    }//close fight
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //needs to impliment each attack 
        String ac = ae.getActionCommand();
        switch(ac){
            case "Attack":
            case "Double Attack":
            case "Block":
            case "Dodge":
            case "Run": if(run()){
                    escape();
                }
                break;
        }
    }//close actionPerformed
}//close Combat

class Character extends Applet implements ActionListener{
    public TextField txtFld;
    protected Button makeButton(String name, GridBagLayout gridbag, GridBagConstraints c) {
    Button button = new Button(name);
    button.setBackground(Color.black);
    button.setForeground(Color.white);
    gridbag.setConstraints(button, c);
    add(button);
    return button;
    }//close makeButton
    
    protected TextArea makeTextArea(String text, GridBagLayout gridbag, GridBagConstraints c){
        TextArea txt = new TextArea(text, 1, 35, TextArea.SCROLLBARS_NONE);
        txt.setEditable(false);
        txt.setBackground(Color.black);
        txt.setForeground(Color.white);
        gridbag.setConstraints(txt, c);
        add(txt);
        
        return txt;
    }//close makeTextArea
    
    protected TextField makeTextField(GridBagLayout gridbag, GridBagConstraints c, int size){
        TextField txt = new TextField();
        txt.setEditable(true);
        txt.setBackground(Color.black);
        txt.setForeground(Color.white);
        gridbag.setConstraints(txt, c);
        add(txt);
        
        return txt;
    }//close makeTextField
    
    protected Panel makePanel(String text, GridBagLayout gridbag, GridBagConstraints c){
        Panel p = new Panel();
        gridbag.setConstraints(p, c);
        p.setBackground(Color.darkGray);
        add(p);
        
        return p;
    }//close makePanel
    
    @Override
    public void init(){
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        setVisible(true);
        
        setFont(new Font("SansSerif", Font.PLAIN, 24));
        setLayout(gridbag);
        
        setBackground(Color.darkGray);
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 4;
        c.weighty = 3;
        
        c.gridwidth = 1;
        c.gridheight = 1;
        makePanel("",gridbag, c);
        TextArea teller = makeTextArea("Please enter your Character Name",gridbag, c);
        teller.setFocusable(false);
        c.gridwidth = GridBagConstraints.REMAINDER;
        makePanel("",gridbag, c);
        c.gridwidth = 1;
        makePanel("",gridbag, c);
        txtFld = makeTextField(gridbag, c, 20);
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        makePanel("",gridbag, c);
        c.gridwidth = 1;
        makePanel("",gridbag, c);
        Button confirm = makeButton("Enter", gridbag, c);
        makePanel("",gridbag, c);
        confirm.addActionListener(this);
        
        setSize(200, 150);
    }//Applet overrided class creating a Character Applet to get character name
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String ac = ae.getActionCommand();
        switch(ac){
            case "Enter": player.name = txtFld.getText();
                player.setSaveName(txtFld.getText() + ".txt");
                File file = new File(player.getSaveName());
                if(file.exists()){
                    try {
                        player.load();
                    } catch (IOException ex) {
                        Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    DungeonCrawler.sendToOutput("Welcome back " + player.name + "!");
                }else{
                    player.createNew();
                    DungeonCrawler.ww.raiseStats(10);
                    player.realizeCharacter();
                    player.health = player.healthMax;
                    player.stamina = player.staminaMax;
                    DungeonCrawler.sendToOutput("Welcome, " + player.name + ", to DungeonCrawlerOne.");
                }
                DungeonCrawler.ww.charact.destroy();
                DungeonCrawler.ww.c.dispose();
                player.active = true;
                DungeonCrawler.ww.updateStatus();
                break;
            case "":
        }
    }//close actionPerfoemed
}//close Character

class Stats extends Applet implements ActionListener{
    //variables setting lower limit of stats based on current stats
    public TextField str;
    public int strengthBase = player.str;
    public int strength = strengthBase;
    public TextField dex;
    public int dexterityBase = player.dex;
    public int dexterity = dexterityBase;
    public TextField vit;
    public int vitalityBase = player.vit;
    public int vitality = vitalityBase;
    public TextField sta;
    public int staminaBase = player.sta;
    public int stamina = staminaBase;
    public TextField acc;
    public int accuracyBase = player.acc;
    public int accuracy = accuracyBase;
    public TextField cri;
    public int criticalBase = player.cri;
    public int critical = criticalBase;
    public TextField point;
    public int points;
    
    public Stats(int Points){
        //needs to be set up for level up
        points = Points;
    }//close Stats
    
    protected Button makeButton(String name, GridBagLayout gridbag, GridBagConstraints c) {
    Button button = new Button(name);
    button.setActionCommand(name);
    char def = name.charAt(0);
    button.setLabel(def + "");
        switch (def) {
            case '+':
                button.setBackground(Color.green);
                break;
            case 'C':
                button.setBackground(Color.blue);
                break;
            default:
                button.setBackground(Color.red);
                break;
        }
    gridbag.setConstraints(button, c);
    add(button);
    return button;
    }//close makeButton
    
    protected TextField makeText(String name, GridBagLayout gridbag, GridBagConstraints c){
        TextField txt = new TextField(name);
        txt.setEditable(false);
        txt.setBackground(Color.black);
        txt.setForeground(Color.white);
        gridbag.setConstraints(txt, c);
        add(txt);
        
        return txt;
    }//close makeText
    
    @Override
    public void init(){
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    
    setBackground(Color.darkGray);
    
    setVisible(true);
    
    setFont(new Font("SansSerif", Font.PLAIN, 24));
    setLayout(gridbag);
    
    c.fill = GridBagConstraints.BOTH;
    
    
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weighty = 1.0;
    makeText("Strength", gridbag, c);
    str = makeText(strength + "", gridbag, c);
    
    c.weighty = 0.0;                
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.gridheight = 1;
    Button strPlus = makeButton("+str", gridbag, c);
    Button strMinus = makeButton("-str", gridbag, c);
    
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weighty = 1.0;
    makeText("Dexterity", gridbag, c);
    dex = makeText(dexterity + "", gridbag, c);
    
    c.weighty = 0.0;                
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.gridheight = 1;
    Button dexPlus = makeButton("+dex", gridbag, c);
    Button dexMinus = makeButton("-dex", gridbag, c);
    
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weighty = 1.0;
    makeText("Vitality", gridbag, c);
    vit = makeText(vitality + "", gridbag, c);
    
    c.weighty = 0.0;                
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.gridheight = 1;
    Button vitPlus = makeButton("+vit", gridbag, c);
    Button vitMinus = makeButton("-vit", gridbag, c);
    
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weighty = 1.0;
    makeText("Stamina", gridbag, c);
    sta = makeText(stamina + "", gridbag, c);
    
    c.weighty = 0.0;                
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.gridheight = 1;
    Button staPlus = makeButton("+sta", gridbag, c);
    Button staMinus = makeButton("-sta", gridbag, c);
    
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weighty = 1.0;
    makeText("Accuracy", gridbag, c);
    acc = makeText(accuracy + "", gridbag, c);
    
    c.weighty = 0.0;                
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.gridheight = 1;
    Button accPlus = makeButton("+acc", gridbag, c);
    Button accMinus = makeButton("-acc", gridbag, c);
    
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weighty = 1.0;
    makeText("Critical", gridbag, c);
    cri = makeText(critical + "", gridbag, c);
    
    c.weighty = 0.0;                
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.gridheight = 1;
    Button criPlus = makeButton("+cri", gridbag, c);
    Button criMinus = makeButton("-cri", gridbag, c);
    
    c.gridwidth = 1;
    c.gridheight = 2;
    c.weighty = 1.0;
    makeText("Stat Points", gridbag, c);
    point = makeText(points + "", gridbag, c);
                   
    c.gridwidth = GridBagConstraints.REMAINDER;
    Button Confirm = makeButton("Confirm", gridbag, c);
    
    strPlus.addActionListener(this);
    strMinus.addActionListener(this);
    dexPlus.addActionListener(this);
    dexMinus.addActionListener(this);
    vitPlus.addActionListener(this);
    vitMinus.addActionListener(this);
    staPlus.addActionListener(this);
    staMinus.addActionListener(this);
    accPlus.addActionListener(this);
    accMinus.addActionListener(this);
    criPlus.addActionListener(this);
    criMinus.addActionListener(this);
    Confirm.addActionListener(this);
    
    setSize(300, 100);
    }//Applet overrided class creating a Stats Applet

    @Override
    public void actionPerformed(ActionEvent ae){
        String ac = ae.getActionCommand();
        switch(ac){
            case "+str": if(points > 0){
                    strength++;
                    str.setText(strength + "");
                    points--;}
                break;
            case "-str": if(strength > strengthBase){
                    strength--;
                    str.setText(strength + "");
                    points++; }
                break;
            case "+dex": if(points > 0){
                    dexterity++;
                    dex.setText(dexterity + "");
                    points--;}
                break;
            case "-dex": if(dexterity > dexterityBase){
                    dexterity--;
                    dex.setText(dexterity + "");
                    points++;}
                break;
            case "+vit": if(points > 0){
                    vitality++;
                    vit.setText(vitality + "");
                    points--;}
                break;
            case "-vit": if(vitality > vitalityBase){
                    vitality--;
                    vit.setText(vitality + "");
                    points++;}
                break;
            case "+sta": if(points > 0){
                    stamina++;
                    sta.setText(stamina + "");
                    points--;}
                break;
            case "-sta": if(stamina > staminaBase){
                    stamina--;
                    sta.setText(stamina + "");
                    points++;}
                break;
            case "+acc": if(points > 0){
                    accuracy++;
                    acc.setText(accuracy + "");
                    points--;}
                break;
            case "-acc": if(accuracy > accuracyBase){
                    accuracy--;
                    acc.setText(accuracy + "");
                    points++;}
                break;
            case "+cri": if(points > 0){
                    critical++;
                    cri.setText(critical + "");
                    points--;}
                break;
            case "-cri": if(critical > criticalBase){
                    critical--;
                    cri.setText(critical + "");
                    points++;}
                break;
            case "Confirm": if(points == 0){
                player.str = strength;
                player.dex = dexterity;
                player.vit = vitality;
                player.sta = stamina;
                player.acc = accuracy;
                player.cri = critical;
                DungeonCrawler.ww.stat.destroy();
                DungeonCrawler.ww.f.dispose();
                DungeonCrawler.ww.updateStatus();
            }
                break;
        }
        point.setText(points + "");
    }//close actionPerformed
}

class WindowWorld extends Frame implements ActionListener{
    //applets and frames for applets
    public Stats stat;
    public Character charact;
    public Combat combat;
    public Frame f;
    public Frame c;
    public Frame C;
    //textAreas to be updated
    private static TextArea status;
    private static TextArea output;
    
    public void sendOutput(String txt){
        output.setText(txt);//sending output for mainWindow
    }//close sendOutput
    
    public WindowWorld(){
        addWindowListener(new MyWindowAdapter());
        
        setSize(new Dimension(600, 600));
        setTitle("Dungeon Crawler One");
        setBackground(Color.red);
        menuCreation();
        setVisible(true);
        
        doLayout();
        
        pack();
    }//close constructor
    
    public void menuCreation(){
        Panel m = new Panel();
        m.setSize(this.getHeight(),this.getWidth()/2);
        Panel s = new Panel();
        s.setSize(this.getHeight(),this.getWidth()/2);
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(m);
        m.setBackground(Color.darkGray);
        this.add(s);
        s.setBackground(Color.darkGray);
        
        //this.setLayout(bl);
        
        List menu = new List(20, false);
        
        menu.setFont(new Font("SansSerif", Font.PLAIN, 18));
        menu.add("Adventure");
        menu.add("Temple");
        menu.add("Inn");
        menu.add("Save");
        menu.add("Exit");
        menu.addActionListener(this);
        menu.setSize(m.getSize());
        menu.setForeground(Color.blue);
        menu.setBackground(Color.black);
        
        m.add(menu);
        
        output = new TextArea("", 10, 40, TextArea.SCROLLBARS_NONE);
        output.setEditable(false);
        
        output.setForeground(Color.yellow);
        output.setBackground(Color.black);
        
        m.add(output);
        
        m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));
        
        status = new TextArea("Status", 20, 20, TextArea.SCROLLBARS_NONE); 
        
        status.setFont(new Font("SansSerif", Font.PLAIN, 18));
                
        status.setForeground(Color.yellow);
        status.setBackground(Color.black);
        
        s.add(status);
        
        status.setEditable(false);
        
        status.setSize(s.getSize());
    }
    
    public void updateStatus(){
       status.setName("Status");
       status.setText("\n---------------\n"
        + "Character: " + player.name + "\n"
        + "---------------\n"
        //+ "Class: " + player.Class + "\n"
        + "Level:   " + player.level + "\n"
        + "Exp:   " + player.exp + "\n"
        + "---------------\n"
        + "Health:  " + player.health + "/" + player.healthMax + "\n"
        + "Stamina: " + player.stamina + "/" + player.staminaMax + "\n"
        + "---------------\n"
        + "Stats\n"
        + "---------------\n"
        + "Dexterity: " + player.dex + "\n"
        + "Strength:  " + player.str + "\n"
        + "Vitality:  " + player.vit + "\n"
        + "Stamina:   " + player.sta + "\n"
        + "Critical:  " + player.cri + "\n"
        + "Accuracy:  " + player.acc + "\n"
        + "---------------\n");
    }
    
    public void raiseStats(int points){
        f = new Frame("Stat Point Distribution");
        
        stat = new Stats(points);
        stat.init();
        
        
        
        f.add("Center", stat);
        f.pack();
        f.setSize(f.getPreferredSize());
        f.setResizable(false);
        
        f.setVisible(true);
    }
    
    public void createChar(){
        c = new Frame("Name");
        
        charact = new Character();
        charact.init();
        
        c.add("Center", charact);
        c.pack();
        c.setSize(c.getPreferredSize());
        c.setResizable(false);
        
        c.setVisible(true);
    }
    
    public void initCombat(){
        C = new Frame("To Battle!");
        
        combat = new Combat();
        combat.init();
        
        C.add("Center", combat);
        C.pack();
        C.setSize(C.getPreferredSize());
        C.setResizable(false);
        
        C.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String ac = ae.getActionCommand();
        switch(ac){
            case "Adventure":
                if(player.health <= 1){
                    sendOutput("You must heal first!");
                }else{
                    initCombat();
                }
                break;
            case "Temple":
                break;
            case "Inn":
                break;
            case "Save": {
            try {
                player.save();
            } catch (JAXBException | FileNotFoundException ex) {
                Logger.getLogger(WindowWorld.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WindowWorld.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case "Exit":   
                try {
                    DungeonCrawler.player.save();
                } catch (IOException ex) {
                    Logger.getLogger(MyWindowAdapter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JAXBException ex) {
            Logger.getLogger(WindowWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
                System.exit(0); 
                break;
        }
    }
}//close public class

class MyWindowAdapter extends WindowAdapter{
    @Override
    public void windowClosing(WindowEvent e){
        if(!player.active){
            DungeonCrawler.ww.charact.destroy();
        }else{
            try {
                DungeonCrawler.player.save();
            } catch (IOException | JAXBException ex) {
                Logger.getLogger(MyWindowAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.exit(0);
    }
}