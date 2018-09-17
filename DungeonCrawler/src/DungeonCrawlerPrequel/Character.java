/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerPrequel;

import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Mike
 */
public class Character {
    public String Name; //Character Name set to unnamed for test purposes.
    public double exp;              //Character Exp
    public int level;            //Character Level
    public double stamina;          //Character Current Stamina
    public double staminaMax;       //Character Max Stamina
    public double health;           //Character Current Health
    public double healthMax;        //Character Max Health
    
    public int coinage;             //Character Money
    public int weaDmg;              //Character Weapon Damage
    public String Weapon;           //Character Weapon
    public int armorAC;             //Character Armor Class
    public String Armor;            //Character Armor
    
    public int str;                 //Character Stat Strength
    public int dex;                 //Character Stat Dexterity
    public int vit;                 //Character Stat Vitality
    public int sta;                 //Character Stat Stamina
    public int acc;                 //Character Stat Accuracy
    public int cri;                 //Character Stat Critical
    public int monDestroyed;        //Lifetime Monsters Destroyed
    
    public void load(String fileName)throws IOException{
        File file = new File(fileName);
        Scanner loadSave = new Scanner(file); //file save/load from book
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
    }
    
    public void save(String file) throws IOException{
        PrintWriter saved = new PrintWriter(file);
            saved.println(Name);
            saved.println(exp);
            saved.println(coinage);
            saved.println(level);
            saved.println(str);
            saved.println(dex);
            saved.println(vit);
            saved.println(sta);
            saved.println(acc);
            saved.println(cri);
            saved.println(health);
            saved.println(stamina);
            saved.println(monDestroyed);
            saved.close();
        System.out.println("Saved!");
    }
    
    public void createStats(int extra, String name){
        Name = name;
        Scanner scanner = new Scanner(System.in);
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
    }
    
    public void levelUp(int lvl){
        Scanner scanner = new Scanner(System.in);
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
        if(level >= 10){
            statPoints = 10;
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
    }
}
