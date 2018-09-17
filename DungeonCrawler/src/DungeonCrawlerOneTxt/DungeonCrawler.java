/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerOneTxt;

import java.util.Random;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author mfaux02
 */
public class DungeonCrawler {
    static Random randGen = new Random();               //Random Number Generator
    static Scanner scanner = new Scanner(System.in);    //Scanner
    
    static Character player;        //objects from oracle
    
    static Monster mon;             //objects from oracle
    
    static String saveName;         //File Save Name
    
    public static void main(String[] args) throws IOException{
        createCharacter();
        doGameStart();
        player.save(saveName);
    }//close main
    
    public static void doGameStart(){
        boolean running = true;
        while(running){
            if(player.health < 1){
                System.out.println("You are Dead");
                System.out.println(" Continue?");
                System.out.println("  Yes/No");
                char resp = ((scanner.next()).toLowerCase()).charAt(0);
                if(resp == 'y'){
                    player.createStats(player.level, player.Name);
                    player.level = 0;
                    realizeCharacter();
                    System.out.println("Welcome back " + player.Name);
                    player.stamina = player.staminaMax;
                    player.health = player.healthMax;
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
                        int healing = (int)(player.healthMax - player.health)/10;
                        System.out.println("Health:  " + player.health + "/" + player.healthMax);
                        System.out.println("It will take " + healing + " coins to heal fully");
                        System.out.println("You have " + player.coinage + " coins");
                        System.out.println("Proceed?");
                        System.out.println("Yes/No");
                        response = (scanner.next().toLowerCase());
                        if(response.charAt(0) == 'y' & player.coinage >= healing){
                            player.health = player.healthMax;
                            player.coinage -= healing;
                        }//close if
                        else if (player.coinage < healing){
                            System.out.println("Not enough coins");
                        }//close else if
                        break;
                        case "rest":
                        System.out.println("Stamina:  " + player.stamina + "/" + player.staminaMax);
                        System.out.println("It will take 5 coins to rest fully");
                        System.out.println("You have " + player.coinage + " coins");
                        System.out.println("Proceed?");
                        System.out.println("Yes/No");
                        response = (scanner.next().toLowerCase());
                        if(response.charAt(0) == 'y' & player.coinage >= 5){
                            player.stamina = player.staminaMax;
                            player.coinage -= 5;
                        }//close if
                        else if (player.coinage < 5){
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
        mon = new Monster(player.level);
        System.out.println("Monster Type: " + mon.name);
        System.out.println("Monster Level: " + mon.level);
        System.out.println("Monster Exp Worth: " + mon.exp);
        if (mon.boss){
            System.out.println("MONSTER IS A BOSS");
        }//close if
        boolean fight = true;
        System.out.println("");
        System.out.println("Health:  " + player.health + "/" + player.healthMax);
        System.out.println("Stamina: " + player.stamina + "/" + player.staminaMax);
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
            if(mon.health < 1){
                System.out.println("Congratulations, you killed a " + mon.name + "!");
                System.out.println("You earned " + mon.exp + " Experieance Points!");
                System.out.println("You earned " + mon.coinage + " Coins!");
                player.exp += mon.exp;
                player.coinage += mon.coinage;
                player.monDestroyed ++;
                fight = false;
            }
            if(player.health < 1){
                System.out.println("You have died");
                System.out.println("You have slain a total " + player.monDestroyed + " Monsters.");
                player.exp /= 10;
                player.coinage = 0;
                fight = false;
            }
        System.out.println("Health:  " + player.health + "/" + player.healthMax);
        System.out.println("Stamina: " + player.stamina + "/" + player.staminaMax);
        }//close while
        realizeCharacter();
    }//close doCombat
    
    public static boolean run(){
        double run = (randGen.nextInt(76) + 25 + player.dex * .25 - player.level * 10) / (randGen.nextInt(51) + 20 + mon.dex * .25 - mon.level * 10);
        boolean escape = false;
        if(run > 1){
            escape = true;
            System.out.println("You have escaped!");
        }
        return escape;
    }
    
    public static void fight(int action){
        double heavyAttack = 0;
        double block = 0;
        double dodge = 0;
        switch(action){
            default:  player.stamina += player.sta * .25 + player.level;  
                if (player.stamina > player.staminaMax){
                    player.stamina = player.staminaMax;
                }
                break;    //attack
            case 2: if(player.stamina >= 30){
                    heavyAttack = (player.str * .5 - randGen.nextInt(player.level + 1));
                    player.stamina -= 30;
                }   
                else{
                    System.out.println("Not enough Stamina.");
                    System.out.println("30 Stamina Needed.");
                }
                break;          //heavy attack
            case 3:  if(player.stamina >= 35){
                    block = (player.str * .5 - randGen.nextInt(player.level + 1));
                    player.stamina -= 35;
                }   
                else{
                    System.out.println("Not enough Stamina.");
                    System.out.println("35 Stamina Needed.");
                }            
                break;          //block
            case 4: if(player.stamina >= 20){
                    dodge = (player.dex * .5 - randGen.nextInt(player.level + 5));
                    player.stamina -= 20;
                }   
                else{
                    System.out.println("Not enough Stamina.");
                    System.out.println("20 Stamina Needed.");
                }           
                break;          //counter
        }
        
        double toHit = (randGen.nextInt(76) + player.acc * .5) / (randGen.nextInt(51) + 25 + mon.dex * .5);
        double toDodge = (randGen.nextInt(76) + mon.acc * .5) / (randGen.nextInt(51) + 25 + player.dex * .5 + dodge);
        
        if(toHit >= 1){
            double damageGiven = randGen.nextInt(26) + player.str * .5 + heavyAttack;
            double critChance = randGen.nextInt(21)  + player.cri * .5 + 10 - player.level;
            if(critChance > 100){
                damageGiven *= (4 + player.cri * .05);
                System.out.println("Oh, A Critical Hit!");
                }//close if
            mon.health -= damageGiven;
            System.out.println("You dealt " + damageGiven + " damage");
        }//close if
        else{
            System.out.println("Missed.");
        }//close else
        if(toDodge >= 1){
            double damageTaken = randGen.nextInt(26) + player.str * .25 + 5 - player.level * .25 - block;
            double critChance = randGen.nextInt(21) + mon.acc * .25 - player.level * .25;
            if(critChance > 100){
                damageTaken *= 2;
                System.out.println("The " + mon.name + " Scored a Critical Hit!");
                }//close if
            player.health -= damageTaken;
            System.out.println(mon.name + " dealt " + damageTaken + " damage");
        }//close if
        else{
            System.out.println("The " + mon.name + " missed.");
        }//close else
    }//close fight
    
    public static void createCharacter() throws IOException{
        player = new Character();
        
        boolean nLoop = true;
        boolean sLoop = true;
        String name = "unnamed";
        while (nLoop){
            

            System.out.println("Are you a returning Player?");
            System.out.println("Yes/No");
            String response = (scanner.next()).toLowerCase();
            char respS = response.charAt(0);
            System.out.println("What is your name?");
            name = scanner.next();
            saveName = name + ".txt";
            
            File load = new File(saveName);
            System.out.println("Is this right: " + name + "?");
            System.out.println("Yes/No");
            response = (scanner.next()).toLowerCase();
            char resp = response.charAt(0);
            if(resp == 'y'){
                if(respS == 'y' & load.exists()){
                    player.load(saveName);
                    System.out.println("Welcome back " + player.Name + "!");
                    sLoop = false;
                }//close if
                nLoop = false;
            }//close if
        }//close while
        
        
        while (sLoop){
            player.createStats(0, name);
            realizeCharacter();
            player.health = player.healthMax;
            player.stamina = player.staminaMax;
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
        double xp = player.exp / 100;
        while (xp >= 3){
            xp = xp / 3;
            lvl ++;
            if(lvl > player.level){
                player.levelUp(lvl);
                player.healthMax = player.vit * 50;
                player.staminaMax = player.sta * 50;
                player.health = player.healthMax;
                player.stamina = player.staminaMax;
            }
        }
        
        player.healthMax = player.vit * 50;
        player.staminaMax = player.sta * 50;
        
    }//close realizeCharacter
    
    public static void checkStatus(){
        System.out.println("");
        System.out.println("*---------------");
        System.out.println("|Character: " + player.Name);
        System.out.println("*---------------");
        System.out.println("|Class: " + player.Class);
        System.out.println("|Level:   " + player.level);
        System.out.println("*---------------");
        System.out.println("|Health:  " + player.health + "/" + player.healthMax);
        System.out.println("|Stamina: " + player.stamina + "/" + player.staminaMax);
        System.out.println("*---------------");
        System.out.println("|Stats");
        System.out.println("*---------------");
        System.out.println("|Dexterity: " + player.dex);
        System.out.println("|Strength:  " + player.str);
        System.out.println("|Vitality:  " + player.vit);
        System.out.println("|Stamina:   " + player.sta);
        System.out.println("|Critical:  " + player.cri);
        System.out.println("|Accuracy:  " + player.acc);
        System.out.println("*---------------");
        System.out.println("");
    }//close checkStatus
}//close Project Class
