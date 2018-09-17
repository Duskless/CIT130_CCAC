/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerOne;

import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Mike
 */
public class Player extends Characters{
    
    public boolean active;
    private String saveName;
    public int monDestroyed;        //Lifetime Monsters Destroyed
    
    Player(){
        active = false;
    }
    
    public void load()throws IOException{
        File file = new File(saveName);
        Scanner loadSave = new Scanner(file); //file save/load from book
            String tempLine;
            name = loadSave.nextLine();
            Class = loadSave.nextLine();
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
    
    public void save() throws IOException{
        PrintWriter saved = new PrintWriter(saveName);
            saved.println(name);
            saved.println(Class);
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
    }
    
    public void realizeCharacter(){
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
    
    public void createNew(){
        exp = 0;
        coinage = 0;
        level = 1;
        str = 8;
        dex = 8;
        vit = 8;
        sta = 8;
        acc = 8;
        cri = 8;
        monDestroyed = 0;
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
        coinage += lvl * 10;
        int statPoints = 2;
    }

    /**
     * @return the saveName
     */
    public String getSaveName() {
        return saveName;
    }

    /**
     * @param saveName the saveName to set
     */
    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }
}
