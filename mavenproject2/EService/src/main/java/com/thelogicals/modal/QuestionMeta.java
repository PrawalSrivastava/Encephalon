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
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author prawal
 */
@Entity
public class QuestionMeta implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    private Topic topic;

    /**
     * Get the value of topic
     *
     * @return the value of topic
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * Set the value of topic
     *
     * @param topic new value of topic
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }


    @Id
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "kaugen")
    private Long id;

    private boolean personalQuestion;

    /**
     * Get the value of personalQuestion
     *
     * @return the value of personalQuestion
     */
    public boolean isPersonalQuestion() {
        return personalQuestion;
    }

    /**
     * Set the value of personalQuestion
     *
     * @param personalQuestion new value of personalQuestion
     */
    public void setPersonalQuestion(boolean personalQuestion) {
        this.personalQuestion = personalQuestion;
    }

    public Long getId() {
        return id;
    }
    private String addedByUser;

    /**
     * Get the value of addedByUser
     *
     * @return the value of addedByUser
     */
    public String getAddedByUser() {
        return addedByUser;
    }

    /**
     * Set the value of addedByUser
     *
     * @param addedByUser new value of addedByUser
     */
    public void setAddedByUser(String addedByUser) {
        this.addedByUser = addedByUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
