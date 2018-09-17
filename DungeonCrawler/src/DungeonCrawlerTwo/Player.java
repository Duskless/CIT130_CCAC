/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerTwo;

import java.util.LinkedList;

/**
 *
 * @author Mike
 */
public class Player extends Characters{
    
    public boolean active;
    public int statPoints;
    
    Player(){
        active = false;
    }
    
    public void reinitialize(){
        int lvl = 1;
        double xp = exp / 100;
        while (xp >= 3){
            xp = xp / 3;
            lvl ++;
            if(lvl > level){
                levelUp(lvl);
                healthMax = con * 50;
                staminaMax = end * 50;
                manaMax = wis * 50;
            }
        }
        
        healthMax = con * 50;
        staminaMax = end * 50;
        manaMax = wis * 50;
        
    }//close realizeCharacter
    
    public void initialize(){
        healthMax = con * 50;
        staminaMax = end * 50;
        manaMax = wis * 50;
        health = healthMax;
        stamina = staminaMax;
        mana = manaMax;
    }//close realizeCharacter
    
    public void createNew(){
        inventory = new LinkedList<>();
        exp = 0;
        coinage = 0;
        level = 1;
        con = 8;
        end = 8;
        wis = 8;
        dex = 8;
        str = 8;
        spd = 8;
        inte = 8;
        def = 8;
        statPoints = 5;
    }
    
    public void levelUp(int lvl){
        level = lvl;
        statPoints = 1;
        System.out.println(name + " has leveled up!");
        System.out.println(name + "is now Level: " + level);
    }
}
