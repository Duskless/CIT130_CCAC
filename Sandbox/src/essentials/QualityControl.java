/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essentials;

import java.util.Random;

/**
 *
 * @author mfaux02
 */
public class QualityControl {
        final static int UPPER_RANGE = 101;
        static int qualityThreshold = 700;
        static int failedRowCountStop = 500;
        static double pricePerUnit = 5;
        
                
    public static void main(String[] args){
        Random randGenerator = new Random();
        
        int totalUnitsMade = 0;
        int unitsFailed = 0;
        int unitsToMake = 1000;
        int unitsPassed = 0;
        int totalPassedQuality = 0;
        int totalFailedQuality = 0;
        int failedRowCount = 0;
        
        
        while(unitsToMake > unitsPassed){
            int rand1 = randGenerator.nextInt(UPPER_RANGE);
            int rand2 = randGenerator.nextInt(UPPER_RANGE);
            int rand3 = randGenerator.nextInt(UPPER_RANGE);
            int rand4 = randGenerator.nextInt(UPPER_RANGE);
            int rand5 = randGenerator.nextInt(UPPER_RANGE);
            int rand6 = randGenerator.nextInt(UPPER_RANGE);
            int rand7 = randGenerator.nextInt(UPPER_RANGE);
            int rand8 = randGenerator.nextInt(UPPER_RANGE);
            int rand9 = randGenerator.nextInt(UPPER_RANGE);
            int rand10 = randGenerator.nextInt(UPPER_RANGE);
            
            int unitQuality = rand1+rand2+rand3+rand4+rand5+rand6+rand7+rand8+rand9+rand10;
            
            totalUnitsMade ++;
            if(unitQuality >= qualityThreshold){
                unitsPassed ++;
                System.out.println("Unit passed at " + unitQuality + "/1000 Quality");
                totalPassedQuality += unitQuality;
                failedRowCount = 0;
            } 
            else{
                unitsFailed ++;
                totalFailedQuality += unitQuality;
                failedRowCount ++;
                
                if(failedRowCount >= failedRowCountStop){
                unitsToMake = 0;
                System.out.println("--System Stop Initiated--");
                System.out.println("-RowFailures: " + failedRowCount);
                }//close if
            }//close else
            
            if(failedRowCount > failedRowCountStop){
                unitsToMake = 0;
            }//close if
        }//close while
        
        double avgQuality = totalPassedQuality / unitsPassed;
        double avgFailedQuality = totalFailedQuality / (totalUnitsMade - unitsPassed);
        double pricePerPassed = pricePerUnit * totalUnitsMade / unitsPassed;
        
                
        System.out.println(unitsPassed + " unit passed at an average of " + avgQuality + "/1000.0");
        System.out.println(unitsFailed + " units failed out of " + totalUnitsMade);
        System.out.println("Passed Unit Price of $" + pricePerPassed);
        System.out.println("Average overall Quality: " + avgFailedQuality  + "/1000.0");
    }
}
