/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import org.json.simple.JSONObject;

/**
 *
 * @author prawal
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class WordMeaning extends Question {

    private String word;

    /**
     * Get the value of word
     *
     * @return the value of word
     */
    public String getWord() {
        return word;
    }

    /**
     * Set the value of word
     *
     * @param word new value of word
     */
    public void setWord(String word) {
        this.word = word;
    }
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private Meaning meaning;
    @Column(columnDefinition = "nvarchar(20000)")
    private String oxfordResponse;

    /**
     * Get the value of oxfordResponse
     *
     * @return the value of oxfordResponse
     */
    public String getOxfordResponse() {
        return oxfordResponse;
    }

    /**
     * Set the value of oxfordResponse
     *
     * @param oxfordResponse new value of oxfordResponse
     */
    public void setOxfordResponse(String oxfordResponse) {
        this.oxfordResponse = oxfordResponse;
    }
    /**
     * Set the value of oxfordResponse
     *
     * @param oxfordResponse new value of oxfordResponse
     */
    public void setOxfordResponse(JSONObject oxfordResponse) {
        this.oxfordResponse = oxfordResponse.toJSONString();
    }

    /**
     * Get the value of meaning
     *
     * @return the value of meaning
     */
    public Meaning getMeaning() {
        return meaning;
    }

    /**
     * Set the value of meaning
     *
     * @param meaning new value of meaning
     */
    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

}
