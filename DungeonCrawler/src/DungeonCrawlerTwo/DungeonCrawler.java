/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DungeonCrawlerTwo;

import java.util.Scanner;

/**
 *
 * @author Mafau
 */
public class DungeonCrawler {
    public static void main(String[] args) {
        start();
    }

    public static void start(){
        Player player = createChar();
    }
    
    public static Player createChar(){
        boolean charCreate = true;
        Scanner read = new Scanner(System.in);
        Player player = new Player();
        
        System.out.println("Welcome to Dungeon Crawler!");
        
        while(charCreate){
            System.out.println("Please enter your name.");
            String name = read.next();
            System.out.println("Is this right?");
            System.out.println("--- " + name + " ---");
            System.out.println("Yes/No");
            String response = read.next();
            response = response.toLowerCase();
            char i = response.charAt(0);
            //load();
            if(i == 'y'){
                player.name = name;
                player = selectClass(player);
                player = createStats(player);
                player.initialize();
                charCreate = false;
            }
        }
        return player;
    }
    
    public static void load(){
        
    }

    private static Player selectClass(Player player) {
        player.charClass = new Class("Default");
        return player;
    }

    private static Player createStats(Player player) {
        while(player.statPoints > 0){
            System.out.println("Please distribute stat points.");
        }
        return player;
    }
        
}
