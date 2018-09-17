/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerTwo;

/**
 *
 * @author Mafau
 */
public class Class {
    String name;
    
    public int conBonus;                 
    public int endBonus;                 
    public int wisBonus;                 
    public int dexBonus;                 
    public int strBonus;                 
    public int spdBonus;                 
    public int inteBonus;                
    public int defBonus;                 
    
    Class(String type){
        switch(type){
            default:
                name = "Default";
                conBonus = 0;
                endBonus = 0;
                wisBonus = 0;
                dexBonus = 0;
                strBonus = 0;
                spdBonus = 0;
                inteBonus= 0;
                defBonus = 0;
                break;
        }
    }
}
