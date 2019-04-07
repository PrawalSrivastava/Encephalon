/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author prawal
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class PointToRemember extends Question {

//    @Id
//    @GenericGenerator(name = "kaugen", strategy = "increment")
//    @GeneratedValue(strategy = GenerationType.TABLE,generator = "kaugen")
//    private Long id;
    @Lob
    private String point;

    /**
     * Get the value of point
     *
     * @return the value of point
     */
    public String getPoint() {
        return point;
    }

    /**
     * Set the value of point
     *
     * @param point new value of point
     */
    public void setPoint(String point) {
        this.point = point;
    }
    @Lob()
    private String answer;

    /**
     * Get the value of answer
     *
     * @return the value of answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the value of answer
     *
     * @param answer new value of answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

}
