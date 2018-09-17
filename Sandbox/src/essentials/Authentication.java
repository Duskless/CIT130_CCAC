/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essentials;

import java.util.Scanner;
/**
 *
 * @author mfaux02
 */
public class Authentication {
    private final static String PASSWORD = "Password17";
    public static boolean access = false;
    
    
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int attemptsLeft = 3;
        
        while(access == false & attemptsLeft > 0){
            System.out.println("Enter your password.");
            String userPassword = scanner.next();    
            
            if(userPassword.equals(PASSWORD)){
                System.out.println("Welcome User!");
                access = true;
            }
            else{

                System.out.println("We're sorry, That is the wrong password.");
                System.out.println("You have " + attemptsLeft + " attempts left.");
                System.out.println("Please Try again.");
                attemptsLeft -= 1;
            }
            
        }
    } 
}
