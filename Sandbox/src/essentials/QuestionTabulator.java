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
public class QuestionTabulator {
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
            }//close if
            else{

                System.out.println("We're sorry, That is the wrong password.");
                System.out.println("You have " + attemptsLeft + " attempts left.");
                System.out.println("Please Try again.");
                attemptsLeft -= 1;
            }//close else
            
        }//close while
        if(access == true){
            menu();
            
        }//close if
    } //close main
       
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to open the Questionaire?");
        System.out.println("yes/no");
        String userResponse = scanner.next();
        if(userResponse.equals("yes")){
            questionair();
        }
        
    }//close menu   
        
   
    public static void questionair(){
        int q1 = 0;
        int q2 = 0;
        int a2 = 0;
        int q3 = 0;
        int totalQuestionees = 0;
        boolean exit = true;
        double t1;
        double t2;
        double t3;
        
        Scanner scanner = new Scanner (System.in);
        
        while(exit){
            totalQuestionees += 1;
            System.out.println(""); //blank space for asetics
            System.out.println(""); //blank space for asetics
            System.out.println("Question set " + totalQuestionees);
            System.out.println("Do you Love Pizza?");
            System.out.println("1 for yes, 0 for no");
            




            int userResponse = scanner.nextInt();

            if(userResponse == 1){
                System.out.println("Another pizza Lover!");
                q1 = q1 + 1;//gathers int and adds to running total
                System.out.println(q1 + " people Love Pizza!");


            } //end if
            else{
                System.out.println("Don't love pizza?");
                System.out.println("Then Let them have Lasagna!");
            } //end else
            
            
            System.out.println(""); //blank space for asetics
            System.out.println(""); //blank space for asetics
            System.out.println("In a week, how often do you eat Pizza?");
            System.out.println("0-7");
            userResponse = scanner.nextInt();
            if(userResponse >= 1){
                
                q2 += userResponse;
                a2 += 1;
                System.out.println("Isn't Pizza good?");
                
                System.out.println(""); //blank space for asetics
                System.out.println(""); //blank space for asetics
                System.out.println("How many toppings(not encluding Cheese) do you like on Pizza?");
                userResponse = scanner.nextInt();
                if(userResponse < 0){
                System.out.println("Yumm");
                q3 += userResponse;
                } //if close
                else{
                    System.out.println("Aw");
                } //else close
                
            } //if close
            else{
                System.out.println("Aw, not even once?");
            } //else close
            
            
            
             
            
            
            
            
            System.out.println(""); //blank space for asetics
            System.out.println(""); //blank space for asetics
            System.out.println("...Exit Quiz?");
            System.out.println("1 for yes, 0 for no");
            userResponse = scanner.nextInt();
            if(userResponse == 1){
                exit = false;
            }
            System.out.println(""); //blank space for asetics
            System.out.println(""); //blank space for asetics
            System.out.println(""); //blank space for asetics
            System.out.println(""); //blank space for asetics
            
        }//close While
        
            t1 = q1 / totalQuestionees *100;
            t2 = q2 / a2;
            t3 = q3 / totalQuestionees *100;
            System.out.println(""); //blank space for asetics
            System.out.println(""); //blank space for asetics
            System.out.println("*****************************************************");
            System.out.println(totalQuestionees + " people took this test!");
            System.out.println(t1 + "% of questionees love pizza.");
            System.out.println("Each questionee eats pizza " + t1 + "time(s) a week!");
            if(a2 != 0){
               System.out.println("Each of the " + a2 + " people who eat pizza at least once a week,");
               
            }
            
            System.out.println("*****************************************************");
        
    } //close method Main
    
} //close class QuestionTabulator
