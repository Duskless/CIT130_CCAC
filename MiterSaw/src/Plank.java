/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author efaux01
 */
public class Plank {
    public double length;
    public double width;
    public double thickness;
    public double endABevel;
    public double endBBevel;
    public double endAMiter;
    public double endBMiter;
    
    Plank(double Length,double Width, double Thickness){
        length = Length;
        width = Width;
        thickness = Thickness;
        endABevel = 0;
        endBBevel = 0;
        endAMiter = 0;
        endBMiter = 0;
    }
}
