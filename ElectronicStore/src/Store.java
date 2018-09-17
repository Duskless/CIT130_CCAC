/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author mfaux02
 */
public class Store {
    public static void main(String[] args) {
        
        LinkedList<Merchandise> merch = new LinkedList<>();
        
        for(int i = 0; i < rng(2 + 1); i++){
            merch.add(new Mouse(rng(4)));
        }for(int i = 0; i < rng(2 + 1); i++){
            merch.add(new Keyboard(rng(4)));
        }for(int i = 0; i < rng(2 + 1); i++){
            merch.add(new Monitor(rng(4)));
        }for(int i = 0; i < rng(2 + 1); i++){
            merch.add(new Desktop(rng(4)));
        }
        
        Scanner read = new Scanner(System.in);
        
        /*System.out.println("Populate the LinkedList Please.");
        System.out.println();
        System.out.println();
        */
        
        boolean run = true;
        while(run){
            System.out.println("");
            System.out.println("");
            System.out.println("You have a list with " + merch.size() + " piece(s) or merchandise.");
            System.out.println("How would you like to Intereact");
            System.out.println("1. Push an item");
            System.out.println("2. Add an item");
            System.out.println("3. Pop an item");
            System.out.println("4. Peek at an item");
            System.out.println("5. Remove the last item");
            System.out.println("6. Remove the middle item");
            System.out.println("7. List item upc's");
            System.out.println("8. Exit");
            System.out.println("");
            
            int input = read.nextInt();
            System.out.println("");
            
            int upc;
            
            switch(input){
                case 1: System.out.println("Please enter a upc ");//push an item
                    System.out.println("*4 numbers only ");
                    upc = read.nextInt();
                    if(upc < 10000 && upc > 999){
                        merch.add(new Mouse(upc));
                    }else if(upc < 10000){
                        System.out.println("Too long!");
                    }else{
                        System.out.println("Too short!");
                    }
                    
                    break;
                case 2: System.out.println("Please enter a upc ");//add an item
                    System.out.println("*4 numbers only ");
                    upc = read.nextInt();
                    if(upc < 10000 && upc > 999){
                        merch.add(new Mouse(upc));
                    }else if(upc < 10000){
                        System.out.println("Too long!");
                    }else{
                        System.out.println("Too short!");
                    }
                    
                    System.out.println("What position would you like to add it?");
                    int pos = read.nextInt() - 1;
                    if(pos <= merch.size() && upc >= 0){
                        merch.add(pos,new Mouse(upc));
                    }
                    
                    break;
                case 3: 
                    Merchandise popped = merch.pop();
                    System.out.println("You popped a "+ popped.type +" with the upc: " + popped.upc);//pop an item
                    break;
                case 4: 
                    Merchandise peeked = merch.peek();
                    System.out.println("You peeked at a " + peeked.type + " with the upc: " + peeked.upc);//peek an item
                    break;
                case 5: 
                    Merchandise removeLast = merch.removeLast();
                    System.out.println("You removed the last " + removeLast.type + " with the upc: " + removeLast.upc);//remove the last item
                    break;
                case 6: 
                    Merchandise removeMiddle = merch.remove(merch.size()/2);
                    System.out.println("You removed the middle " + removeMiddle.type + " with the upc: " + removeMiddle.upc);//remove the middle item
                    break;
                case 7: //display all merch
                    for(int i = 0; i < merch.size(); i ++){
                        Merchandise merchandise = merch.get(i);
                        String sUpc = merchandise.upc + "";
                        while(sUpc.length() < 4){
                            sUpc = 0 + sUpc;
                        }
                        System.out.println((i + 1) + ". :" +merchandise.type + ": " + " upc: " + sUpc);
                    }
                    break;
                case 8: System.out.println("Thank you for coming!");//exit
                    run = false;
                    break;
                default: System.out.println("Opps! I didn't reconize that!");//default for weird occurances 
                    break;
            }
        }
    }
    
    public static int rng(int numOfDigits){//random number generator for starting upc codes
        Random rng = new Random();
        
        String digits = "";
        while(digits.length() < numOfDigits){
            int x = rng.nextInt(10);
            digits += "" + x;
        }
        int export = Integer.valueOf(digits);
        return export;
    }
}
