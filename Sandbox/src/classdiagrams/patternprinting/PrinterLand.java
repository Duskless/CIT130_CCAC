/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classdiagrams.patternprinting;

/**
 * Demo client class which instantiates objects
 * who are part of a multi-class structure.
 * @author delores
 */
public class PrinterLand {
    public static void main(String[] args) {
        System.out.println("Box:");
        BoxPrinter bp = new LoopingBoxer();
        bp.printABox(12);
        
        System.out.println("");
        System.out.println("Pyramid");
        PyramidPrinter pp = new CoolShapeMaker();
        pp.printAPyramid(45);
        
        System.out.println("");
        System.out.println("Parrallellogram");
        ParallellogramPrinter pP = new LoopingParallellogramer();
        pP.printAParallellogram(13,22);
    }
    
}
