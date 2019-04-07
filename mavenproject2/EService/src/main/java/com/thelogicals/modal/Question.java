/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author prawal
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "AllQuestions", query = "SELECT u FROM Question u")
})
public class Question implements Serializable {

    @Id
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "kaugen")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestionMeta meta;

    /**
     * Get the value of meta
     *
     * @return the value of meta
     */
    public QuestionMeta getMeta() {
        return meta;
    }

    /**
     * Set the value of meta
     *
     * @param meta new value of meta
     */
    public void setMeta(QuestionMeta meta) {
        this.meta = meta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    private QuestionTypeEnum type;

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public QuestionTypeEnum getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(QuestionTypeEnum type) {
        this.type = type;
    }

}
