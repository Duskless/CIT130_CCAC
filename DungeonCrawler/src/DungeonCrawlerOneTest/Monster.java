/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerOneTest;

import java.util.Random;

/**
 *
 * @author Mike
 */
public class Monster extends Characters{
    static Random randGen = new Random();
    
    public boolean boss;
    private String status;
    
    public String getStatus(){
        return "Dead";
    }
    
    public Monster(int lvl){
        if(lvl > 1){
        level = lvl - 1;
        }//close if
        else{
            level = 1;
        }
        int monSeed = randGen.nextInt(101);
        while (monSeed > 25){
            monSeed -= 25;
            level ++;
        }
        int statPoints;
        switch (monSeed){
            case 1: case 2: case 3: case 4: case 5:
            statPoints = (int)level * 5 + 20;
            while (statPoints >1){
                statPoints -= 5;
                vit ++;
                sta ++;
                dex += 2;
                acc ++;
            }
            name = "Skeleton";
            stamina = sta * 55;
            health = vit * 55;
            exp = level * (25 + randGen.nextInt(50));
            coinage = (int)(level * (5 + randGen.nextInt(21)));
            break;
            
            case 6: case 7: case 8: case 9:
            statPoints = (int)level * 6 + 24;
            while (statPoints >1){
                statPoints -= 6;
                vit += 2;
                sta ++;
                str += 2;
                acc ++;
            }
            name = "Golem";
            stamina = sta * 70;
            health = vit * 55;
            exp = level * (30 + randGen.nextInt(50));
            coinage = (int)(level * (10 + randGen.nextInt(21)));
            break;
            case 10: case 11: case 12:
            statPoints = (int)level * 7 + 28;
            while (statPoints >1){
                statPoints -= 7;
                str ++;
                sta ++;
                vit ++;
                dex ++;
                acc += 2;
                cri ++;
            }
            name = "Kenku";
            stamina = sta * 65;
            health = vit * 50;
            exp = level * (35 + randGen.nextInt(50));
            coinage = (int)(level * (12 + randGen.nextInt(21)));
            break;
            case 13: case 14: case 15:
            statPoints = (int)level * 8 + 32;
            while (statPoints >1){
                statPoints -= 8;
                str += 2;
                sta ++;
                vit ++;
                dex += 2;
                acc += 2;
            }
            name = "Wolf";
            stamina = sta * 60;
            health = vit * 55;
            exp = level * (35 + randGen.nextInt(50));
            coinage = (int)(level * (8 + randGen.nextInt(21)));
            break;
            case 16: case 17:
            statPoints = (int)level * 9 + 36;
            while (statPoints >1){
                statPoints -= 9;
                str += 2;
                sta ++;
                vit ++;
                dex += 4;
                acc ++;
            }
            level ++;
            name = "Ghost";
            stamina = sta * 70;
            health = vit * 40;
            exp = level * (35 + randGen.nextInt(50));
            coinage = (int)(level * (10 + randGen.nextInt(21)));
            break;
            case 18: case 19: case 20: default:
            statPoints = (int)level * 9 + 36;
            while (statPoints >1){
                statPoints -= 9;
                str += 2;
                sta ++;
                vit ++;
                dex += 4;
                acc ++;
            }
            level ++;
            name = "Mon1";
            stamina = sta * 70;
            health = vit * 40;
            exp = level * (35 + randGen.nextInt(50));
            coinage = (int)(level * (15 + randGen.nextInt(21)));
            break;
        }

    }
}
