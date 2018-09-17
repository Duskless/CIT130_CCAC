/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donutland;

import java.util.Random;

/**
 *
 * @author mfaux02
 */
public class Animal {
    private final Random rng = new Random();
    public String type = findType();
    private final int AMOUNT_EATEN = rng.nextInt(25) + 10;
    private final String LIKES = doLike();
    
    
    public void eatDonut(Donut dToEat, String foodOrigin){
        int amountEaten;
        if(foodOrigin.equals(LIKES)){
            amountEaten = AMOUNT_EATEN;
        } else{
            amountEaten = AMOUNT_EATEN - 10;
        }
        dToEat.simulateEating(amountEaten);
    }
    
    public int getAmountEaten(String foodOrigin){
        if(foodOrigin.equals(LIKES)){
            return AMOUNT_EATEN;
        } else{
        return AMOUNT_EATEN - 10;
        }
    }
    private String findType(){
        String animalType = "Furry";
        return animalType;
    }
    
    public String doLike(){
        return "Snowwy Plains";
    }
}
