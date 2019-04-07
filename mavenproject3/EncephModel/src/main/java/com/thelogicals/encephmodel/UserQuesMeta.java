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
public class UserQuesMeta implements Serializable {

    @Id
    private Long id;

    private EncephUser user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public EncephUser getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(EncephUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
