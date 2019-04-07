/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.encephmodel;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author prawal
 */
@Entity
public class EncephUser implements Serializable {

    @Id
    private long userId;

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Set the value of userId
     *
     * @param userId new value of userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }
    
}
