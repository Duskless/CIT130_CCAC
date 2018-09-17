/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Stack;

/**
 *
 * @author efaux01
 */
public class WoodShop {
    public static void main(String[] args){
        MiterSaw saw1 = new MiterSaw();
        Stack<MiterSaw> sawStack = new Stack<>();
        sawStack.push(saw1);
        MiterSaw saw2 = sawStack.pop();
        
    }
}
