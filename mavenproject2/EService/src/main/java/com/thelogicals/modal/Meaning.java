/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author prawal
 */
@Entity
public class Meaning implements Serializable {

    @Id
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "kaugen")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Definition> noun;

    /**
     * Get the value of noun
     *
     * @return the value of noun
     */
    public Set<Definition> getNoun() {
        return noun;
    }

    /**
     * Set the value of noun
     *
     * @param noun new value of noun
     */
    public void setNoun(Set<Definition> noun) {
        this.noun = noun;
    }

}
