/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donutland;

/**
 *
 * @author mfaux02
 */
public class DonutLand {
    final static int FURRY_ANIMAL_EATS = 25;
    final static int WATER_ANIMAL_EATS = 10;
    
    public static void main(String[] args){
        Donut firstDonut = new Donut();
        firstDonut.name = "Chloe";
        Donut secondDonut = new Donut();
        secondDonut.name = "Bob";
        
        System.out.println(firstDonut.name + "'s percent remaining: " + firstDonut.getPercRemaining());
        doAnimalEatsDonut(firstDonut, "Furry");
        System.out.println(firstDonut.name + "'s percent remaining: " + firstDonut.getPercRemaining());
        System.out.println(secondDonut.name + "'s percent remaining: " + secondDonut.getPercRemaining());
        doAnimalEatsDonut(secondDonut, "Watery");
        System.out.println(secondDonut.name + "'s percent remaining: " + secondDonut.getPercRemaining());
    }
    
    public static void doAnimalEatsDonut(Donut dToEat, String type){
        switch(type){
            case "Furry":
                dToEat.simulateEating(FURRY_ANIMAL_EATS);
                System.out.println("OM NOM NOM!");
                break;
            case "Watery": 
                dToEat.simulateEating(WATER_ANIMAL_EATS);
                System.out.println("MUNCH MUNCH MUNCH!");
                break;
        }
    }
}
