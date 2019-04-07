/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author prawal
 */
@Entity
public class Topic implements Serializable {

    @Id
    @GenericGenerator(name = "kaugen", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "kaugen")
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastUpdated;

    /**
     * Get the value of lastUpdated
     *
     * @return the value of lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Set the value of lastUpdated
     *
     * @param lastUpdated new value of lastUpdated
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    private Topic parentTopic;

    /**
     * Get the value of parentTopic
     *
     * @return the value of parentTopic
     */
    public Topic getParentTopic() {
        return parentTopic;
    }

    private int nodeLevel;

    /**
     * Get the value of nodeLevel
     *
     * @return the value of nodeLevel
     */
    public int getNodeLevel() {
        return nodeLevel;
    }

    /**
     * Set the value of nodeLevel
     *
     * @param nodeLevel new value of nodeLevel
     */
    public void setNodeLevel(int nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    /**
     * Set the value of parentTopic
     *
     * @param parentTopic new value of parentTopic
     */
    public void setParentTopic(Topic parentTopic) {
        this.parentTopic = parentTopic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Lob
    private String parentTagString;

    /**
     * Get the value of parentTagString
     *
     * @return the value of parentTagString
     */
    public String getParentTagString() {
        return parentTagString;
    }

    /**
     * Set the value of parentTagString
     *
     * @param parentTagString new value of parentTagString
     */
    public void setParentTagString(String parentTagString) {
        this.parentTagString = parentTagString;
    }

    private String topicName;

    /**
     * Get the value of topicName
     *
     * @return the value of topicName
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * Set the value of topicName
     *
     * @param topicName new value of topicName
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
