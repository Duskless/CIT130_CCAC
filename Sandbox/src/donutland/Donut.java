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
public class Donut {
    private Random rng = new Random();
    public String name;
    private final int STARTING_AMOUNT = rng.nextInt(1000) + 20;
    private int amountRemaining = STARTING_AMOUNT;
    private final int AGE_IN_HOURS = rng.nextInt(10);
    private double percRemaining = 100;
    private final String ORIGIN_LOCATION = getOriginLocation();
   
    public void simulateEating(int amountEaten){
        amountRemaining -= amountEaten;
    }
    
    public double getPercRemaining(){
        percRemaining = STARTING_AMOUNT / amountRemaining;
        if(percRemaining < 0){
            percRemaining = 0;
        }
        return percRemaining;
    }
    
    private String getOriginLocation(){
        String origin = "Snowwy Plains";
        return origin;
    }
    
    public String getOrigin(){
        return ORIGIN_LOCATION;
    }
    
    public int getAge(){
        return AGE_IN_HOURS;
    }
}
