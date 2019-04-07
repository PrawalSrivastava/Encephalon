/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
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
public class Definition implements Serializable {

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
    private String definition;

    /**
     * Get the value of definition
     *
     * @return the value of definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Set the value of definition
     *
     * @param definition new value of definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    private String example;

    /**
     * Get the value of example
     *
     * @return the value of example
     */
    public String getExample() {
        return example;
    }

    /**
     * Set the value of example
     *
     * @param example new value of example
     */
    public void setExample(String example) {
        this.example = example;
    }
    @ElementCollection
    @CollectionTable

    private Set<String> synonyms;

    /**
     * Get the value of synonyms
     *
     * @return the value of synonyms
     */
    public Set<String> getSynonyms() {
        return synonyms;
    }

    /**
     * Set the value of synonyms
     *
     * @param synonyms new value of synonyms
     */
    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }

}
