/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.areas;

import com.uas.object.ObjectDTO;

/**
 *
 * @author jonathangil
 */
public class areaDTO extends ObjectDTO{
    boolean superuser, enabled;

    public areaDTO() {
        superuser = false;
        enabled = true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }
    
    
}
