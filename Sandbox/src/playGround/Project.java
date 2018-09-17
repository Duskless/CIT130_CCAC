/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playGround;

import java.util.Random;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author mfaux02
 */
public class Project {
    static Random randGen = new Random();               //Random Number Generator
    static Scanner scanner = new Scanner(System.in);    //Scanner
    
    static String Name = "unnamed"; //Character Name set to unnamed for test purposes.
    static double exp;              //Character Exp
    static int level;            //Character Level
    static double stamina;          //Character Current Stamina
    static double staminaMax;       //Character Max Stamina
    static double health;           //Character Current Health
    static double healthMax;        //Character Max Health
    
    static int coinage;             //Character Money
    static int weaDmg;              //Character Weapon Damage
    static String Weapon;           //Character Weapon
    static int armorAC;             //Character Armor Class
    static String Armor;            //Character Armor
    
    static int str;                 //Character Stat Strength
    static int dex;                 //Character Stat Dexterity
    static int vit;                 //Character Stat Vitality
    static int sta;                 //Character Stat Stamina
    static int acc;                 //Character Stat Accuracy
    static int cri;                 //Character Stat Critical
    static int monDestroyed;        //Lifetime Monsters Destroyed
    
    static String monName; //Character Name
    static boolean boss = false;
    static double monExp;              //Monster Exp
    static double monLevel;            //Monster Level
    static int monCoinage;            //Monster Level
    static double monStamina;          //Monster Current Stamina
    static double monHealth;           //Monster Current Health
    
    static int monStr = 8;                 //Monster Stat Strength
    static int monDex = 8;                 //Monster Stat Dexterity
    static int monAcc = 8;                 //Monster Stat Accuracy
    static int monCri = 8;                 //Monster Stat Critical
    
    static String saveName;         //File Save Name
    
    public static void main(String[] args) throws IOException{
        createCharacter();
        doGameStart();
        saveGame();
    }//close main
    
    public static void doGameStart(){
        boolean running = true;
        while(running){
            if(health < 1){
                System.out.println("You are Dead");
                System.out.println(" Continue?");
                System.out.println("  Yes/No");
                char resp = ((scanner.next()).toLowerCase()).charAt(0);
                switch(resp){
                    case 'y': createStats(level);
                    level = 0;
                    realizeCharacter();
                    System.out.println("Welcome back " + Name);
                    stamina = staminaMax;
                    health = healthMax;
                }
            }//close if
            else{
                System.out.println("What would you like to do?");
                System.out.println("Heal");
                System.out.println("Rest");
                System.out.println("Status");
                System.out.println("Adventure");
                System.out.println("Exit");
                String response = (scanner.next().toLowerCase());
                System.out.println("");
                switch(response){
                    case "heal":
                        int healing = (int)(healthMax - health);
                        System.out.println("Health:  " + health + "/" + healthMax);
                        System.out.println("It will take " + healing/50 + " coins to heal fully");
                        System.out.println("You have " + coinage + " coins");
                        System.out.println("Proceed?");
                        System.out.println("Yes/No");
                        response = (scanner.next().toLowerCase());
                        if(response.charAt(0) == 'y' & coinage >= healing/50){
                            health = healthMax;
                            coinage -= healing/50;
                        }//close if
                        else if (coinage < healing/50){
                            System.out.println("Not enough coins");
                        }//close else if
                        break;
                        case "rest":
                        System.out.println("Stamina:  " + stamina + "/" + staminaMax);
                        System.out.println("It will take 5 coins to rest fully");
                        System.out.println("You have " + coinage + " coins");
                        System.out.println("Proceed?");
                        System.out.println("Yes/No");
                        response = (scanner.next().toLowerCase());
                        if(response.charAt(0) == 'y' & coinage >= 5){
                            stamina = staminaMax;
                            coinage -= 5;
                        }//close if
                        else if (coinage < 5){
                            System.out.println("Not enough coins");
                        }//close else if
                        break;
                    case "adventure":
                        doCombat();
                        break;
                    case "status":
                        checkStatus();
                        break;
                    case "exit":
                        running = false;
                        break;
                    default: System.out.println("I didn't get that.");
                        break;
                }//close switch
            }//close else
            System.out.println("");
        }//close while
    }//close doGameStart
    
    public static void doCombat(){
        generateMonster();
        System.out.println("Monster Type: " + monName);
        System.out.println("Monster Level: " + monLevel);
        System.out.println("Monster Exp Worth: " + monExp);
        if (boss){
            System.out.println("MONSTER IS A BOSS");
        }//close if
        boolean fight = true;
        System.out.println("");
        System.out.println("Health:  " + health + "/" + healthMax);
        System.out.println("Stamina: " + stamina + "/" + staminaMax);
        while(fight){
            System.out.println("");
            System.out.println("What would you like to do?");
            System.out.println("Attack");
            System.out.println("Heavy Attack");
            System.out.println("Block");
            System.out.println("Dodge");
            System.out.println("Run");
            System.out.println("");
            String response = scanner.next();
            System.out.println("");
            switch(response.toLowerCase()){
                case "attack":fight(1);
                    break;
                case "heavy attack":fight(2);
                    break;
                case "block":fight(3);
                    break;
                case "dodge":fight(4);
                    break;
                case "run": boolean  escape = run();
                    if(escape){
                        fight = false;
                    }
                    break;
                default:    System.out.println("Sorry, I didn't get that.");
                    break;
            }//close switch
            if(monHealth < 1){
                System.out.println("Congratulations, you killed a " + monName + "!");
                System.out.println("You earned " + monExp + " Experieance Points!");
                System.out.println("You earned " + monCoinage + " Coins!");
                exp += monExp;
                coinage += monCoinage;
                monDestroyed ++;
                fight = false;
            }
            if(health < 1){
                System.out.println("You have died");
                System.out.println("You have slain a total " + monDestroyed + " Monsters.");
                exp -= exp / 10;
                coinage = 0;
                fight = false;
            }
        System.out.println("Health:  " + health + "/" + healthMax);
        System.out.println("Stamina: " + stamina + "/" + staminaMax);
        }//close while
        realizeCharacter();
    }//close doCombat
    
    public static boolean run(){
        double run = (randGen.nextInt(76) + 25 + dex * .25 - level * 10) / (randGen.nextInt(51) + 20 + monDex * .25 - monLevel * 10);
        boolean escape = false;
        if(run > 1){
            escape = true;
        }
        return escape;
    }
    
    public static void fight(int action){
        double heavyAttack = 0;
        double block = 0;
        double dodge = 0;
        switch(action){
            default:  stamina += sta * .25 + level;  
                if (stamina > staminaMax){
                    stamina = staminaMax;
                }
                break;    //attack
            case 2: if(stamina >= 30){
                    heavyAttack = (str * .5 - randGen.nextInt(level + 1));
                    stamina -= 30;
                }   
                else{
                    System.out.println("Not enough Stamina.");
                    System.out.println("30 Stamina Needed.");
                }
                break;          //heavy attack
            case 3:  if(stamina >= 35){
                    block = (str * .5 - randGen.nextInt(level + 1));
                    stamina -= 35;
                }   
                else{
                    System.out.println("Not enough Stamina.");
                    System.out.println("35 Stamina Needed.");
                }            
                break;          //block
            case 4: if(stamina >= 20){
                    dodge = (dex * .5 - randGen.nextInt(level + 5));
                    stamina -= 20;
                }   
                else{
                    System.out.println("Not enough Stamina.");
                    System.out.println("20 Stamina Needed.");
                }           
                break;          //counter
        }
        
        double toHit = (randGen.nextInt(76) + acc * .25 - level * 10) / (randGen.nextInt(51) + 25 + monDex * .25 - monLevel * 10);
        double toDodge = (randGen.nextInt(76) + monAcc * .25 - monLevel * 10) / (randGen.nextInt(51) + 25 + dex * .25 - level * 10 + dodge);
        
        if(toHit >= 1){
            double damageGiven = randGen.nextInt(26) + str * .25 + 5  - level * .25 + heavyAttack;
            double critChance = randGen.nextInt(21)  + cri * .25 - level ;
            if(critChance > 100){
                damageGiven *= 2;
                System.out.println("Oh, A Critical Hit!");
                }//close if
            monHealth -= damageGiven;
            System.out.println("You dealt " + damageGiven + " damage");
        }//close if
        else{
            System.out.println("Missed.");
        }//close else
        if(toDodge >= 1){
            double damageTaken = randGen.nextInt(26) + str * .25 + 5 - level * .25 - block;
            double critChance = randGen.nextInt(21) + monAcc * .25 - level * .25;
            if(critChance > 100){
                damageTaken *= 2;
                System.out.println("The " + monName + " Scored a Critical Hit!");
                }//close if
            health -= damageTaken;
            System.out.println(monName + " dealt " + damageTaken + " damage");
        }//close if
        else{
            System.out.println("The " + monName + " missed.");
        }//close else
    }//close fight
    
    public static void generateMonster(){
    int monVit = 8;                 //Monster Stat Vitality
    int monSta = 8;                 //Monster Stat Stamina
    if(level > 1){
    monLevel = level - 1;
    }//close if
    else{
        monLevel = 1;
    }
    int monSeed = randGen.nextInt(101);
    while (monSeed > 25){
        monSeed -= 25;
        monLevel ++;
    }
    int statPoints;
    switch (monSeed){
        case 1: case 2: case 3: case 4: case 5:
            statPoints = (int)monLevel * 5 + 20;
            while (statPoints >1){
                statPoints -= 5;
                monVit ++;
                monSta ++;
                monDex += 2;
                monAcc ++;
            }
            monName = "Skeleton";
            monStamina = monSta * 55;
            monHealth = monVit * 55;
            monExp = monLevel * (25 + randGen.nextInt(50));
            monCoinage = (int)(monLevel * (5 + randGen.nextInt(21)));
            break;
            
            case 6: case 7: case 8: case 9:
            statPoints = (int)monLevel * 6 + 24;
            while (statPoints >1){
                statPoints -= 6;
                monVit += 2;
                monSta ++;
                monStr += 2;
                monAcc ++;
            }
            monName = "Golem";
            monStamina = monSta * 70;
            monHealth = monVit * 55;
            monExp = monLevel * (30 + randGen.nextInt(50));
            monCoinage = (int)(monLevel * (10 + randGen.nextInt(21)));
            break;
            case 10: case 11: case 12:
            statPoints = (int)monLevel * 7 + 28;
            while (statPoints >1){
                statPoints -= 7;
                monStr ++;
                monSta ++;
                monVit ++;
                monDex ++;
                monAcc += 2;
                monCri ++;
            }
            monName = "Kenku";
            monStamina = monSta * 65;
            monHealth = monVit * 50;
            monExp = monLevel * (35 + randGen.nextInt(50));
            monCoinage = (int)(monLevel * (12 + randGen.nextInt(21)));
            break;
            case 13: case 14: case 15:
            statPoints = (int)monLevel * 8 + 32;
            while (statPoints >1){
                statPoints -= 8;
                monStr += 2;
                monSta ++;
                monVit ++;
                monDex += 2;
                monAcc += 2;
            }
            monName = "Wolf";
            monStamina = monSta * 60;
            monHealth = monVit * 55;
            monExp = monLevel * (35 + randGen.nextInt(50));
            monCoinage = (int)(monLevel * (8 + randGen.nextInt(21)));
            break;
            case 16: case 17:
            statPoints = (int)monLevel * 9 + 36;
            while (statPoints >1){
                statPoints -= 9;
                monStr += 2;
                monSta ++;
                monVit ++;
                monDex += 4;
                monAcc ++;
            }
            monLevel ++;
            monName = "Ghost";
            monStamina = monSta * 70;
            monHealth = monVit * 40;
            monExp = monLevel * (35 + randGen.nextInt(50));
            monCoinage = (int)(monLevel * (10 + randGen.nextInt(21)));
            break;
            case 18: case 19: case 20: default:
            statPoints = (int)monLevel * 9 + 36;
            while (statPoints >1){
                statPoints -= 9;
                monStr += 2;
                monSta ++;
                monVit ++;
                monDex += 4;
                monAcc ++;
            }
            monLevel ++;
            monName = "Mon1";
            monStamina = monSta * 70;
            monHealth = monVit * 40;
            monExp = monLevel * (35 + randGen.nextInt(50));
            monCoinage = (int)(monLevel * (15 + randGen.nextInt(21)));
            break;
        }
    }//close generateMonster
    
    public static void createCharacter() throws IOException{
        boolean nLoop = true;
        boolean sLoop = true;
        
        while (nLoop){
        System.out.println("What is your name?");
        Name = scanner.next();
        File load = new File(Name + ".txt");
        if(load.exists()){
            System.out.println("Returning Player?");
            System.out.println("Yes/No");
            String response = scanner.next();
            response = response.toLowerCase();
            char resp = response.charAt(0);
            if(resp == 'y'){
                saveName = Name + ".txt";
                Scanner loadSave = new Scanner(load); //file save/load from book
                String tempLine;
                Name = loadSave.nextLine();
                tempLine = loadSave.nextLine();
                exp = Double.valueOf(tempLine); //https://docs.oracle.com/javase/tutorial/java/data/converting.html
                tempLine = loadSave.nextLine(); //valueOf to convert String to int/double
                coinage = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                level = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                str = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                dex = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                vit = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                sta = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                acc = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                cri = Integer.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                health = Double.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                stamina = Double.valueOf(tempLine);
                tempLine = loadSave.nextLine();
                monDestroyed = Integer.valueOf(tempLine);
                //how to load inventory?
                nLoop = false;
                sLoop = false;
                checkStatus();
                }//close if
            else{
                saveName = Name + "X.txt";
            }//close else
        }//close if
        else{
            saveName = Name + ".txt";
        }//close else
        
        System.out.println("Is this right: " + Name + "?");
        System.out.println("Yes/No");
        String response = scanner.next();
        response = response.toLowerCase();
        char resp = response.charAt(0);
        if(resp == 'y'){
            nLoop = false;
        }//close if
    }//close while
        
        
        while (sLoop){
            createStats(0);
            realizeCharacter();
            health = healthMax;
            stamina = staminaMax;
            System.out.println("Is this right?");
            checkStatus();
            System.out.println("Yes/No");
            String response = scanner.next();
            response = response.toLowerCase();
            char resp = response.charAt(0);
            if(resp == 'y'){
            sLoop = false;
            }//close if
        }//close while  
        realizeCharacter();
    }//close createCharacter
    
    public static void realizeCharacter(){
        int lvl = 1;
        double xp = exp / 100;
        while (xp >= 3){
            xp = xp / 3;
            lvl ++;
            if(lvl > level){
                levelUp(lvl);
                healthMax = vit * 50;
                staminaMax = sta * 50;
                health = healthMax;
                stamina = staminaMax;
            }
        }
        
        healthMax = vit * 50;
        staminaMax = sta * 50;
        
    }//close realizeCharacter
    
    public static void levelUp(int lvl){
        System.out.println("Congratulations!");
        System.out.println("You've Leveled Up!");
        System.out.println("You are now Level: " + level);
        System.out.println("(press any button)");
        scanner.next();
        level = lvl;
        health = healthMax;
        stamina = staminaMax;
        coinage += lvl*50;
        int statPoints = 5;
        if(level >= 10 & level < 50){
            statPoints = 10;
        }
        else if(level >= 50 & level < 100){
            statPoints = 25;
        }
        else if(level >= 500 & level < 1000){
            statPoints = 50;
        }
        else if(level >= 500 & level < 1000){
            statPoints = 100;
        }
        else if(level >= 1000){
            statPoints = 200;
        }
        while(statPoints > 0){
            System.out.println("Assign Stat Points");
            System.out.println("Remaining Points: " + statPoints);
            System.out.println("");
            System.out.println("Dexterity: " + dex);
            System.out.println("Strength: " + str);
            System.out.println("Vitality: " + vit);
            System.out.println("Stamina: " + sta);
            System.out.println("Critical: " + cri);
            System.out.println("Accuracy: " + acc);
            System.out.println("Which Stat?");
            String response = scanner.next();
            response = response.toLowerCase();
            response = response.substring(0, 3);
            System.out.println("How many points?");
            int add = scanner.nextInt();
            if(add > statPoints){
                System.out.println("Too many Points.");
            }//close if
            else if(add < 1){
                System.out.println("Not enough Points.");
            }//close else if
            else{
                switch (response) {
                    case "dex": dex += add; statPoints -= add;
                        break;
                    case "str": str += add; statPoints -= add;
                        break;
                    case "vit": vit += add; statPoints -= add;
                        break;
                    case "sta": sta += add; statPoints -= add;
                        break;
                    case "cri": cri += add; statPoints -= add;
                        break;
                    case "acc": acc += add; statPoints -= add;
                        break;
                    default:    System.out.println("Stat not available.");
                        break;
                }//close switch
            }//close else
        }//close while
    }//close levelUp
    
    public static void createStats(int extra){
        str = 8;
        dex = 8;
        vit = 8;
        sta = 8;
        acc = 8;
        cri = 8;
        int statPoints = 50 + extra;
        while(statPoints > 0){
            System.out.println("Assign Stat Points");
            System.out.println("Remaining Points: " + statPoints);
            System.out.println("");
            System.out.println("Dexterity: " + dex);
            System.out.println("Strength: " + str);
            System.out.println("Vitality: " + vit);
            System.out.println("Stamina: " + sta);
            System.out.println("Critical: " + cri);
            System.out.println("Accuracy: " + acc);
            System.out.println("Which Stat?");
            String response = scanner.next();
            response = response.toLowerCase();
            response = response.substring(0, 3);
            System.out.println("How many points?");
            int add = scanner.nextInt();
            if(add > statPoints){
                System.out.println("Too many Points.");
            }//close if
            else if(add < 1){
                System.out.println("Not enough Points.");
            }//close else if
            else{
                switch (response) {
                    case "dex": dex += add; statPoints -= add;
                        break;
                    case "str": str += add; statPoints -= add;
                        break;
                    case "vit": vit += add; statPoints -= add;
                        break;
                    case "sta": sta += add; statPoints -= add;
                        break;
                    case "cri": cri += add; statPoints -= add;
                        break;
                    case "acc": acc += add; statPoints -= add;
                        break;
                    default:    System.out.println("Stat not available.");
                        break;
                }//close switch
            }//close else
            System.out.println("");
            
        }//close while
    }//close createStats
    
    public static void checkStatus(){
        System.out.println("");
        System.out.println("*---------------");
        System.out.println("|Character: " + Name);
        System.out.println("*---------------");
        System.out.println("|Level:   " + level);
        System.out.println("|Health:  " + health + "/" + healthMax);
        System.out.println("|Stamina: " + stamina + "/" + staminaMax);
        System.out.println("*---------------");
        System.out.println("|Stats");
        System.out.println("*---------------");
        System.out.println("|Dexterity: " + dex);
        System.out.println("|Strength:  " + str);
        System.out.println("|Vitality:  " + vit);
        System.out.println("|Stamina:   " + sta);
        System.out.println("|Critical:  " + cri);
        System.out.println("|Accuracy:  " + acc);
        System.out.println("*---------------");
        System.out.println("");
    }//close checkStatus
    
    public static void saveGame() throws IOException{
            PrintWriter save = new PrintWriter(Name + ".txt");
            save.println(Name);
            save.println(exp);
            save.println(coinage);
            save.println(level);
            save.println(str);
            save.println(dex);
            save.println(vit);
            save.println(sta);
            save.println(acc);
            save.println(cri);
            save.println(health);
            save.println(stamina);
            save.println(monDestroyed);
            save.close();
        System.out.println("Saved!");
    }//close saveGame
}//close Project Class
