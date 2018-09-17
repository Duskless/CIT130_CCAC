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
public class Monster extends Characters{
    Monster(String type, int level){
        this.level = level;
        switch(type){
            default:
                name = "Default Creature";
                con = 10;
                end = 10;
                wis = 10;
                dex = 10;
                str = 10;
                spd = 10;
                inte= 10;
                def = 10;
                break;
        }
    }
}
