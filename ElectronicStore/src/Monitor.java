/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mfaux02
 */
public class Monitor extends Merchandise {
    
    Monitor(int upc){
        this.type = "Monitor";
        setUpc(upc);
    }
    
    /**
     * @param upc the upc to set
     */
    @Override
    public final void setUpc(int upc) {
        this.upc = upc;
    }
}
