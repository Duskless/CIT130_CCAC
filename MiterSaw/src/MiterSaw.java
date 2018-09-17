/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author efaux01
 */
public class MiterSaw{
    private boolean power;
    private boolean trigger;
    private boolean laser;
    private double miterAngle;
    private double bevelAngle;
    private float speed;
    private Blade currentBlade;
    public boolean holdDownLatch;
    public DustBag dustBag;
    public final String upc;
    
    MiterSaw(String upc){
        power = false;
        trigger = false;
        laser = false;
        miterAngle = 0.0;
        bevelAngle = 0.0;
        speed = 4800;
        currentBlade = new Blade();
        dustBag = new DustBag();
        this.upc = upc;
    }
    
    MiterSaw(){
        power = false;
        trigger = false;
        laser = false;
        miterAngle = 0.0;
        bevelAngle = 0.0;
        speed = 4800;
        currentBlade = new Blade();
        dustBag = new DustBag();
        this.upc = "FOI13489";
    }
    
    public void powerOn(){
        if(holdDownLatch){
            sendText("Saw is still latched!");
            power = false;
        }else{
            sendText("Saw is now on!");
            power = true;
        }
    }
    
    public void powerOff(){
        power = false;
        laser = false;
        sendText("Power off.");
    }
    
    public void enableLaser(){
        if(power){
            laser = true;
            sendText("Laser is on!");
        }else{
            laser = false;
            sendText("Power is off!");
        }
    }
    
    public void disableLaser(){
        laser = false;
        sendText("Laser is now off.");
    }
    
    public void changeMiterAngle(double newAngle){
        if(90 > newAngle & newAngle > -90){
            miterAngle = newAngle;
            sendText("Miter Angle changed to " + miterAngle + " Degrees");
            
        }
    }
    public void changeBevelAngle(double newAngle){
        if(90 > newAngle & newAngle > -90){
            bevelAngle = newAngle;
            sendText("Bevel Angle changed to " + bevelAngle + " Degrees");
        }
    }
    
    public Blade changeBlade(Blade newBlade){
        Blade oldBlade = currentBlade;
        currentBlade = newBlade;
        sendText("Blade Changed!");
        return oldBlade;
    }
    
    public Plank useSaw(Plank toCut,char endToCut,double lengthToCut){
        if(lengthToCut > toCut.length & endToCut == 'a' & endToCut == 'b'){
            trigger = true;
            playSound("Whirrrrrrr");
            toCut.length = lengthToCut;
            playSound("Buzzzzzzz");
            if(endToCut == 'a'){
                toCut.endABevel = bevelAngle;
                toCut.endAMiter = miterAngle;
            }else if(endToCut == 'b'){
                toCut.endBBevel = bevelAngle;
                toCut.endBMiter = miterAngle;
            }
            playSound("Whirrrrrrr");
            trigger = false;
            return toCut;
        }else{
            sendError("Plank.useInvalid");
            return toCut;
        }
        
    }
    
    public static void playSound(String txt){
        System.out.println(txt);
    }
    
    public static void sendError(String txt){
        System.out.println("Error." + txt);
    }
    
    public static void sendText(String txt){
        System.out.println(txt);
    }
}
