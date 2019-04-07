/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author prawal
 */
@Entity
public class ModalClass implements Serializable {

    private boolean qServ;

    /**
     * Get the value of qServ
     *
     * @return the value of qServ
     */
    public boolean isqServ() {
        return qServ;
    }

    /**
     * Set the value of qServ
     *
     * @param qServ new value of qServ
     */
    public void setqServ(boolean qServ) {
        this.qServ = qServ;
    }

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private boolean string;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public boolean getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(boolean string) {
        this.string = string;
    }

}
