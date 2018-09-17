/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerTwo;

import java.util.LinkedList;

/**
 *
 * @author Mafaux
 */
public class Characters {
    public String name;             //Character Name
    public Class charClass;         //Character Class
    public int level;               //Character Level
    public double exp;              //Character Exp
    public double stamina;          //Character Current Stamina
    public double staminaMax;       //Character Max Stamina
    public double health;           //Character Current Health
    public double healthMax;        //Character Max Health
    public double mana;             //Character Current Mana
    public double manaMax;          //Character Max Mana
    
    public int coinage;             //Character Money
    public int weaDmg;              //Character Weapon Damage
    public Weapon weapon;           //Character Weapon
    public int armorAC;             //Character Armor Class
    public Armor armor;             //Character Armor
    public LinkedList<Item> inventory;
    
    public int con;                 //Character Stat Constitution
    public int end;                 //Character Stat Endurance
    public int wis;                 //Character Stat Wisdom
    public int dex;                 //Character Stat dexterity
    public int str;                 //Character Stat Strength
    public int spd;                 //Character Stat Speed
    public int inte;                //Character Stat Intelligence
    public int def;                 //Character Stat Defense
    
    public int vit;                 //Character Stat Vitality
    public int sta;                 //Character Stat Stamina
    public int acc;                 //Character Stat Accuracy
    public int cri;                 //Character Stat Critical
}
