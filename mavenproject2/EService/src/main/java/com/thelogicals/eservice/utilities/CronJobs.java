/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.eservice.utilities;

import com.thelogicals.modal.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author prawal
 */
public class CronJobs {

    @Autowired
    Utilities utilities;

    
    @Autowired(required = true)
    @Qualifier(value = "questionDAO")
    private QuestionDAO questionDAO;


    public void firstJob() {
        questionDAO.maintenance();
    }
    public void topicMaintenance() {
        questionDAO.topicMaintenance();
    }
}
