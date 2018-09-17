/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classdiagrams.patternprinting;

/**
 *
 * @author mfaux02
 */
public class CoolShapeMaker implements PyramidPrinter{
    final String SIDE_UNIT_CHAR = "*";
    final String SIDE_UNIT_BLANK_CHAR = " ";
    
    
    /**
     * Implements printAPyramid and interprets the
     * side length in units of a character
     * printed to the console. 
     * @param bottomWidth 
     */
    @Override
    public void printAPyramid(int bottomWidth) {
        int width;
        for(double level = (int)bottomWidth/2; level>=0; level--){
            width = (int)(bottomWidth - (level *2));
            for(int i = 0; i < (bottomWidth - width)/2; i++){
                    System.out.print(SIDE_UNIT_BLANK_CHAR);
            }
            
            for(int i = 0; i < width; i++){
                    System.out.print(SIDE_UNIT_CHAR);
            }
            System.out.println("");
        }
    }
}
