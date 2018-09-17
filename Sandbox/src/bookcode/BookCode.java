/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcode;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Mike
 */
public class BookCode {
    public static String[][] book;
    public static int bookLength = 16990;
    public static int codeLength = 1;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        readBook("Odessey.txt");
        
        boolean loop = true;
        while(loop){
            logln("");
            logln("What would you like to do?");
            logln("(U)nCode");
            logln("(C)ode");
            logln("(E)xit");
            String resp = getLog().toLowerCase();
            switch(resp){
                case "u": logln("");    uncode(); break;
                case "c": logln("");    code(); break;
                case "e": loop = false; break;
                default: logln("Sorry didn't get that.");   break;
            }
        }
        logln("GoodBye!");
        
    }//close main
    
    public static void code(){
        String[][] coded = convertToCode();
        
        for(int l = 0; l < coded.length; l++){
            for(int w = 0; w < coded[l].length; w++){
                log(coded[l][w] + " ");
            }
            logln("");
        }
    }
    
    public static void uncode(){
        String[][] uncoded = convertFromCode();
        
        for(int l = 0; l < uncoded.length; l++){
            for(int w = 0; w < uncoded[l].length; w++){
                log(uncoded[l][w] + " ");
            }
            logln("");
        }
    }
    
    public static String getLog(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    public static void log(String event){
        System.out.print(event);
    }
    
    public static void logln(String event){
        System.out.println(event);
    }
    
    public static String[][] convertToCode(){
        String[][] code;
        String[] split;
        logln("You have 25 words per line");
        logln("Enter number of lines to code:");
        int lines = Integer.valueOf(getLog());
        logln("Begin sending message now");
        code = new String[lines][25];
        
        for(int i = 0; i < lines; i++){
            String line = getLog();
            split = line.split(" ");
            String[] codeLines = new String[split.length];
                    
            for(int a = 0; a < split.length; a++){
                //System.out.println("Code.searchFor." + split[a]);
                String temp = searchFor(split[a].toLowerCase());
                codeLines[a] = temp;
            }
            code[i] = codeLines;
        }
        //System.out.println("Code.return");
        
        
        return code;
    }
    
    public static String searchFor(String toCode){
        toCode = toCode.toLowerCase();
        String codes = null;
        int found = 0;
        
        for(int searched = 0; searched < bookLength && found < 1; searched++){
            for(int a = 0; a < book[searched].length; a++){
                if(toCode.equals(book[searched][a])){
                    codes = (searched + ":" + a);
                    logln("Code.found");
                    found++;
                }
            }
            searched++;
        }
        if(found == 0){
            logln("Code.notFound");
            return null;
        }
        else{
            return codes;
        }
    }
    
    public static String[][] convertFromCode(){
        String[] code;
        
        logln("You can uncode 25 words per line");
        logln("Enter number of lines to uncode:");
        int lines = Integer.valueOf(getLog());
        logln("Begin sending code now");
        code = new String[lines];
                
        int[] bookNav = new int[2];
        String[] nav;
        String[] line;
        String[][] uncoded = new String[codeLength][25];
        String[] uncodedLines;
        
        for(int i = 0; i < codeLength; i++){
            line = code[i].split(" ");
            uncodedLines = new String[line.length];
            for(int a = 0; a < line.length; a++){
                nav = line[a].split(":");
                bookNav[0] = Integer.valueOf(nav[0]);
                bookNav[1] = Integer.valueOf(nav[1]);
                uncodedLines[a] = book[bookNav[0]][bookNav[1]];
            }
            uncoded[i] = uncodedLines;
        }
        return uncoded;
    }//close convertFromCode
    
    public static void readBook(String bookName) throws IOException{
        File file = new File(bookName);
        Scanner bookRead = new Scanner(file);
        
        book = new String[bookLength][25];
        String[] split;
        
        for(int i = 0; i < bookLength; i++){
            String line = bookRead.nextLine().replaceAll("\\.", "").toLowerCase();
            line = line.replaceAll(",", "");
            line = line.replaceAll(";", "");
            line = line.replaceAll(":", "");
            line = line.replaceAll("'", "");
            split = line.split(" ");
            book[i] = split;
            
        }//close for
        
    }//close readBook
}//close Class
