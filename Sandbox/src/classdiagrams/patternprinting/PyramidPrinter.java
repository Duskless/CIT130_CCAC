/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classdiagrams.patternprinting;

/**
 *
 * @author delores
 */
public interface PyramidPrinter {
     /**
     * Sends data to System.out.println() to 
     * create a pyramid with a base size
     * of the inputted value.
     * 
     * It's up to the implementing method to
     * choose how to print this box to the console
     * in code and how to interpret the length
     * of a side integer passed in.
     * 
     * @param baseSize 
     */
    public void printAPyramid(int baseSize);
    
}
