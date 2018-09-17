/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mfaux02
 */
public class Desktop extends Merchandise {
    
    Desktop(int upc){
        this.type = "Desktop";
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
