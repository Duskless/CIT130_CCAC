/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author mfaux02
 */
public class CalculatorGui extends Frame implements ActionListener{
    public static Calculator calc;
    public static boolean running = true;
    public static TextArea txt;
    public static String input = "";
    public static boolean numSet = false;
    
    public static void main(String[] args) {
        //Creates Gui
        CalculatorGui cg = new CalculatorGui();
        //set Size,Title, and visibility for GUI
        cg.setSize(new Dimension(300, 300));
        cg.setTitle("Calculator");
        cg.setVisible(true);
    }//close main
    
    public void makeText(GridBagLayout gridbag, GridBagConstraints c){
        //Makes a TextArea, sets Size and adds it to GUI
        txt = new TextArea("", 1, 20, TextArea.SCROLLBARS_NONE);
        gridbag.setConstraints(txt, c);
        txt.setEditable(false);
        add(txt);
    }//close makeText
    
    public void makeButton(String name, GridBagLayout gridbag, GridBagConstraints c){
        //Makes a Button, sets Size, adds an ActionListener, and adds it to GUI
        Button button = new Button(name);
        gridbag.setConstraints(button, c);
        button.addActionListener(this);
        button.setSize(30, 10);
        add(button);
    }//close makeButton
    
    public void makePanel(GridBagLayout gridbag, GridBagConstraints c){
        //Makes a blank Panel, sets Size and adds it to GUI
        Panel p = new Panel();
        gridbag.setConstraints(p, c);
        add(p);
    }//close makePanel
    
    public CalculatorGui(){
        //Creates a new Calculator
        calc = new Calculator(0, 0, Calculator.ADD);
        calc.setAnsToNum1(true);
        //Creates GUI LayoutManager 
        GridBagLayout gridbag = new GridBagLayout();
        //Creates Constraints to resize components like buttons, panels and textareas
        GridBagConstraints c = new GridBagConstraints();
        //Set font for GUI
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        //Adds LayoutManager to the GUI
        this.setLayout(gridbag);
        
        //Creates, shapes, and adds the Buttons, Panels, and the TextArea
        c.weighty = 0;
        c.weightx = .5;
        
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = 4;
        makeText(gridbag, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("<-", gridbag, c);
        
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        makeButton("1", gridbag, c);
        makeButton("2", gridbag, c);
        makeButton("3", gridbag, c);
        makeButton("+", gridbag, c);
        c.gridheight = 2;
        c.gridx = 4;
        c.gridy = 1;
        makeButton("C", gridbag, c);
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        makeButton("4", gridbag, c);
        c.gridx = GridBagConstraints.RELATIVE;
        makeButton("5", gridbag, c);
        makeButton("6", gridbag, c);
        makeButton("-", gridbag, c);
        c.gridwidth = 1;
        c.gridy = 4;
        makeButton("7", gridbag, c);
        makeButton("8", gridbag, c);
        makeButton("9", gridbag, c);
        makeButton("*", gridbag, c);
        c.gridheight = 2;
        makeButton("=", gridbag, c);
        c.gridheight = 1;
        c.gridy = 5;
        c.gridx = 0;
        makePanel(gridbag, c);
        c.gridx = GridBagConstraints.RELATIVE;
        makeButton("0", gridbag, c);
        makeButton(".", gridbag, c);
        makeButton("/", gridbag, c);
        
        
        //Adds WindowListener to close Window
        addWindowListener(new MyWindowAdapter());
        
        //pack();
    }//close Constructor CalculatorGui
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //ActionListener to impliment all the buttons
        String ac = e.getActionCommand();
        char a = ac.charAt(0);
        switch(a){
            case '1': if(input.equals("0")){
                input = "";
                }
                input = input + 1;
                CalculatorGui.txt.setText(input);
                break;
            case '2': if(input.equals("0")){
                input = "";
                }
                input = input + 2;
                CalculatorGui.txt.setText(input);
                break;
            case '3': if(input.equals("0")){
                input = "";
                }
                input = input + 3;
                CalculatorGui.txt.setText(input);
                break;
            case '4': if(input.equals("0")){
                input = "";
                }
                input = input + 4;
                CalculatorGui.txt.setText(input);
                break;
            case '5': if(input.equals("0")){
                input = "";
                }
                input = input + 5;
                CalculatorGui.txt.setText(input);
                break;
            case '6': if(input.equals("0")){
                input = "";
                }
                input = input + 6;
                CalculatorGui.txt.setText(input);
                break;
            case '7': if(input.equals("0")){
                input = "";
                }
                input = input + 7;
                CalculatorGui.txt.setText(input);
                break;
            case '8': if(input.equals("0")){
                input = "";
                }
                input = input + 8;
                CalculatorGui.txt.setText(input);
                break;
            case '9': if(input.equals("0")){
                input = "";
                }
                input = input + 9;
                CalculatorGui.txt.setText(input);
                break;
            case '0': if(input.equals("0")){
                input = "";
                }
                input = input + 0;
                CalculatorGui.txt.setText(input);
                break;
            case '+': if(numSet){
                CalculatorGui.calc.setNum2(Long.valueOf(input));
                double end = CalculatorGui.calc.doOperation();
                input = "0";
                CalculatorGui.txt.setText(end + "");
                CalculatorGui.calc.setOperator(Calculator.ADD);
                }else{
                CalculatorGui.calc.setNum1(Long.valueOf(input));
                input = "0";
                CalculatorGui.calc.setOperator(Calculator.ADD);
                numSet = true;
                }
                break;
            case '-': if(numSet){
                CalculatorGui.calc.setNum2(Long.valueOf(input));
                double end = CalculatorGui.calc.doOperation();
                input = "0";
                CalculatorGui.txt.setText(end + "");
                CalculatorGui.calc.setOperator(Calculator.SUBTRACT);
                }else{
                CalculatorGui.calc.setNum1(Long.valueOf(input));
                input = "0";
                CalculatorGui.calc.setOperator(Calculator.SUBTRACT);
                numSet = true;
                }
                break;
            case '*': if(numSet){
                CalculatorGui.calc.setNum2(Long.valueOf(input));
                double end = CalculatorGui.calc.doOperation();
                input = "0";
                CalculatorGui.txt.setText(end + "");
                CalculatorGui.calc.setOperator(Calculator.MULTIPLY);
                }else{
                CalculatorGui.calc.setNum1(Long.valueOf(input));
                input = "0";
                CalculatorGui.calc.setOperator(Calculator.MULTIPLY);
                numSet = true;
                }
                break;
            case '/': if(numSet){
                CalculatorGui.calc.setNum2(Double.valueOf(input));
                double end = CalculatorGui.calc.doOperation();
                input = "0";
                CalculatorGui.txt.setText(end + "");
                CalculatorGui.calc.setOperator(Calculator.DIVIDE);
                }else{
                CalculatorGui.calc.setNum1(Double.valueOf(input));
                input = "0";
                CalculatorGui.calc.setOperator(Calculator.DIVIDE);
                numSet = true;
                }
                break;
            case '=': if(numSet){
                CalculatorGui.calc.setNum2(Double.valueOf(input));
                double end = CalculatorGui.calc.doOperation();
                input = end + "";
                CalculatorGui.txt.setText(end + "");
                numSet = false;
                }else{
                CalculatorGui.calc.setNum1(Double.valueOf(input));
                double end = CalculatorGui.calc.doOperation();
                input = end + "";
                CalculatorGui.txt.setText(end + "");
                }
                break;
            case 'C': input = "0";
                CalculatorGui.txt.setText(input);
                CalculatorGui.calc.setNum1(0);
                CalculatorGui.calc.setNum2(0);
                numSet = false;
                break;
            case '.': input = input + ".";
                CalculatorGui.txt.setText(input);
                break;
            case '<': input = input.substring(0, input.length() - 1);
                CalculatorGui.txt.setText(input);
                break;
        }//close switch
    }//close actionPerformed
}//close class CalculatorGui

class MyWindowAdapter extends WindowAdapter{
    @Override
    public void windowClosing(WindowEvent e){
        //Closes Program
        System.exit(0);
    }//Close windowClosing
}//Close class MyWindowAdapter