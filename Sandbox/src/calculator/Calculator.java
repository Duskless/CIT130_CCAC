/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author mfaux02
 */
public class Calculator {
    public static final int MULTIPLY = 1;
    public static final int DIVIDE = 2;
    public static final int ADD = 3;
    public static final int SUBTRACT = 4;
    
    private final boolean debug = true;
    
    private double result;
    private double num1;
    private double num2;
    private int operator;
    private boolean setAnsToNum1 = false;
    private boolean setAnsToResult = true;
    public Calculator(double number1, double number2, int op){
        num1 = number1;
        num2 = number2;
        operator = op;
        setAnsToNum1 = false;
    }//close Constructor Calculator
    public Calculator(double number1, double number2){
        num1 = number1;
        num2 = number2;
        operator = MULTIPLY;
        setAnsToNum1 = false;
    }//close Constructor Calculator
    public Calculator(int op){
        num1 = 1;
        num2 = 1;
        operator = op;
        setAnsToNum1 = false;
    }//close Constructor Calculator
    public Calculator(){
        num1 = 1;
        num2 = 1;
        operator = MULTIPLY;
        setAnsToNum1 = false;
    }//close Constructor Calculator
    
    private void debug(String error){
        System.out.println("Debug." + error);
    }
    
    private double operate(){
        double endProduct;
        switch(operator){
            case MULTIPLY: endProduct = multiply();
                if(debug){
                    debug("Multiply");
                }
                break;
            case DIVIDE: endProduct = divide();
                if(debug){
                    debug("Divide");
                }
                break;
            case ADD: endProduct = add();
                if(debug){
                    debug("Add");
                }
                break;
            case SUBTRACT: endProduct = subtract();
                if(debug){
                    debug("Subtract");
                }
                break;
            default: endProduct = -1;
                break;
        }
        if(setAnsToNum1){
            num1 = endProduct;
        }
        return endProduct;
    }//close operation
    public double doOperation(){
        if(debug){
                    debug("Num1=" + num1);
                    debug("Num2=" + num2);
                }
        double endProduct = operate();
        if(setAnsToResult){
            result = endProduct;
        }
        return endProduct;
    }//close doOperation
    
    public double doOperation(int op){
        operator = op;
        double endProduct = operate();
        if(setAnsToResult){
            result = endProduct;
        }
        return endProduct;
    }//close doOperation
    
    public double doOperation(long number1, long number2){
        num1 = number1;
        num2 = number2;
        double endProduct = operate();
        if(setAnsToResult){
            result = endProduct;
        }
        return endProduct;
    }//close doOperation
    
    public double doOperation(long number1, long number2, int op){
        num1 = number1;
        num2 = number2;
        operator = op;
        double endProduct = operate();
        if(setAnsToResult){
            result = endProduct;
        }
        return endProduct;
    }//close doOperation
    
    private double multiply(){
        double endProduct = num1 * num2;
        return endProduct;
    }//close multiply
    private double divide(){
        double endProduct = num1 / num2;
        return endProduct;
    }//close divide
    private double add(){
        double endProduct = num1 + num2;
        return endProduct;
    }//close add
    private double subtract(){
        double endProduct = num1 - num2;
        return endProduct;
    }//close subtract

    /**
     * @return the num1
     */
    public double getNum1() {
        return num1;
    }//close num1 getter

    /**
     * @param num1 the num1 to set
     */
    public void setNum1(double num1) {
        this.num1 = num1;
    }//close num1 setter

    /**
     * @return the num2
     */
    public double getNum2() {
        return num2;
    }//close num2 getter

    /**
     * @param num2 the num2 to set
     */
    public void setNum2(double num2) {
        this.num2 = num2;
    }//close num2 setter

    /**
     * @param operator the operator to set
     */
    public void setOperator(int operator) {
        if(operator >= 1 && operator <= 4){
            this.operator = operator;
        }
    }//close operator  setter

    /**
     * @param setAnsToNum1 the setAnsToNum1 to set
     */
    public void setAnsToNum1(boolean setAnsToNum1) {
        this.setAnsToNum1 = setAnsToNum1;
    }//close setAnsToNum1 setter

    /**
     * @return the result
     */
    public double getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(long result) {
        this.result = result;
    }

    /**
     * @param setAnsToResult the setAnsToResult to set
     */
    public void setSetAnsToResult(boolean setAnsToResult) {
        this.setAnsToResult = setAnsToResult;
    }
}
