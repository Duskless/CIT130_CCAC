/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classdiagrams.patternprinting;

/**
 *
 * @author Mafau
 */
public interface ParallellogramPrinter {
    /**
     * Sends data to System.out.println() to 
     * create a box with a side length
     * of the inputted value length and
     * a height of inputted value height.
     * 
     * It's up to the implementing method to
     * choose how to print this box to the console
     * in code and how to interpret the length
     * of a side integer passed in.
     * 
     * @param height
     * @param width 
     */
    public void printAParallellogram(int height, int width);
    
}
