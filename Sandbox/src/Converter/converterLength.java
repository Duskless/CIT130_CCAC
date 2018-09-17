/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author Mike
 */
public class converterLength {
    static Scanner scanner = new Scanner(System.in);
    
    static String unit;
    static String unitOne;
    static String unitTwo;
    
    
    public static void main(String[] args){
        double multiplierOne;
        double multiplierTwo;
        
        System.out.println("Hello, What system are you converting from?");
        System.out.println("1: Metric");
        System.out.println("2: English");
        int response = scanner.nextInt();
        
        System.out.println("");
        
        switch (response){
            case 1: multiplierOne = convertMetric();
                break;
            case 2: multiplierOne = convertImperial();
                break;
            default: System.out.println("error.option.null");
                System.exit(0); multiplierOne = 1;
                break;
        }//close switch
        unitOne = unit;
        
        System.out.println("");
        
        System.out.println("What system are you converting to?");
        System.out.println("1: Metric");
        System.out.println("2: Imperial/US");
        response = scanner.nextInt();
        
        System.out.println("");
        
        switch (response){
            case 1: multiplierTwo = convertMetric();
                break;
            case 2: multiplierTwo = convertImperial();
                break;
            default: System.out.println("error.option.null");
                System.exit(0); multiplierTwo = 1;
                break;
        }//close switch
        unitTwo = unit;
        
        System.out.println("");
        
        System.out.println("Insert " + unitOne + " to be converted.");
        double resp = scanner.nextDouble();
        double conversion = resp * multiplierOne / multiplierTwo;
        
        System.out.println("");
        
        System.out.println("Round?");
        System.out.println("Y/N");
        
        String respon = scanner.next();
        respon = respon.toLowerCase();
        char round = respon.charAt(0);
        
        System.out.println("");
        
        if(round == 'y'){
            conversion = Math.round(conversion);
        }
        
        System.out.println(resp + " " + unitOne + " is aproximately " + conversion + " " + unitTwo);
    }//close main
    
    public static double convertMetric(){
        double multiplier = 1;
        
        System.out.println("What unit would you like?");
        System.out.println("1:  Terametre");
        System.out.println("2:  Gigametre");
        System.out.println("3:  Megametre");
        System.out.println("4:  Kilometre");
        System.out.println("5:  Hectometre");
        System.out.println("6:  Decometre");
        System.out.println("7:  Metre");
        System.out.println("8:  Decimetre");
        System.out.println("9:  Centimetre");
        System.out.println("10: Millimetre");
        System.out.println("11: Micrometre");
        System.out.println("12: Nanometre");
        System.out.println("13: Picometre");
        int response = scanner.nextInt();
        
        System.out.println("");
        
        switch (response) {
            case 1: multiplier = 1e12;
                    unit = "Terametres";
                break;
            case 2: multiplier = 1e9;
                    unit = "Gigametres";
                break;
            case 3: multiplier = 1e6;
                    unit = "Megametres";
                break;
            case 4: multiplier = 1e3;
                    unit = "Kilometres";
                break;
            case 5: multiplier = 1e2;
                    unit = "Hectometres";
                break;
            case 6: multiplier = 10;
                    unit = "Decometres";
                break;
            case 7: multiplier = 1;
                    unit = "Metres";
                break;
            case 8: multiplier = .1;
                    unit = "Decimetres";
                break;
            case 9: multiplier = 1e-2;
                    unit = "Centimetres";
                break;
            case 10: multiplier = 1e-3;
                    unit = "Millimetres";
                break;
            case 11: multiplier = 1e-6;
                    unit = "Micometres";
                break;
            case 12: multiplier = 1e-9;
                    unit = "Nanometres";
                break;
            case 13: multiplier = 1e-12;
                    unit = "Picometres";
                break;
            default: System.out.println("error.option.null");
                System.exit(0);
        }
        return multiplier;
    }//close convertMetric
    
    public static double convertImperial(){
        double multiplier = 1;
        
        System.out.println("What unit would you like?");
        System.out.println("1: Thou");
        System.out.println("2: Inch");
        System.out.println("3: Foot");
        System.out.println("4: Yard");
        System.out.println("5: Chain");
        System.out.println("6: Furlong");
        System.out.println("7: Mile");
        System.out.println("8: League");
        int response = scanner.nextInt();
        
        System.out.println("");
        
        switch (response) {
            case 1: multiplier = 2.54e-5;
                    unit = "Thou";
                break;
            case 2: multiplier = 0.0254;
                    unit = "Inches";
                break;
            case 3: multiplier = 0.3048;
                    unit = "Feet";
                break;
            case 4: multiplier = 0.9144;
                    unit = "Yards";
                break;
            case 5: multiplier = 20.1168;
                    unit = "Chains";
                break;
            case 6: multiplier = 201.168;
                    unit = "Furlongs";
                break;
            case 7: multiplier = 1609.34;
                    unit = "Miles";
                break;
            case 8: multiplier = 4828.02;
                    unit = "Leagues";
                break;
            default: System.out.println("error.option.null");
                System.exit(0);
        }
        return multiplier;
    }//close convertImperial
}
