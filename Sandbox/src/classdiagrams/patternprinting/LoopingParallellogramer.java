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
public class LoopingParallellogramer implements ParallellogramPrinter{
    final String SIDE_UNIT_CHAR = "*";
    /**
     * Implements PrintAParallellogram and interprets the
     * height and width in units of a character
     * printed to the console. 
     * @param height
     * @param width
     */
    @Override
    public void printAParallellogram(int height, int width) {
        for(int outer = 0; outer<height; outer++){
            
            for(int i = 0; i < width; i++){
                    System.out.print(SIDE_UNIT_CHAR);
            }
            System.out.println("");
        }
        
    }
    
}
