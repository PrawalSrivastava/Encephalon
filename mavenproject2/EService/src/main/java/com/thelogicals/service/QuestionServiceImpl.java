/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.service;

import com.thelogicals.eservice.utilities.Utilities;
import com.thelogicals.modal.PointToRemember;
import com.thelogicals.modal.WordMeaning;
import com.thelogicals.modal.dao.QuestionDAO;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author prawal
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    Utilities utilities;

    private QuestionDAO questionDAO;

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    @Transactional
    public void dummy() {

    }

    @Override
    @Transactional
    public void addPointToRemember(PointToRemember ptr) {
        System.out.println("Sending to DAO");
        questionDAO.addQuestion(ptr);
    }

    @Override
    @Transactional
    public void addWordMeaning(WordMeaning ptr) {
        System.out.println("Sending to DAO");
        questionDAO.addQuestion(ptr);

    }

    /**
     * This method is responsible for creating the question paper. It will just
     * extract the Questions from the data base after manipulation of user
     * statics.
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public List createQuestionPaper(JSONObject inputJson) {
        System.out.println("Sending to DAO");
        //Now take out the userName from input JSON
        Map examParameters = (Map) inputJson.get("fields");
        String userName = (String) inputJson.get("userName");
        int noOfQuestions = Integer.parseInt((String) examParameters.get("noOfQuestions"));
        //Here we need to Query the data base on a table which might have schema like 
        //Userid, questionid, questiontype, datetime, nooftimesasked, nooftimescorrect, difficultycatagory
        //this will give us the id of questions to be asked
        //then we will select all these questions

        List ll = questionDAO.selectQuestion(userName, noOfQuestions);
        return ll;
    }

    @Override
    @Transactional
    public String createQuestionPaperJSONString(JSONObject inputJson) {
        System.out.println("Sending to DAO");
        //Now take out the userName from input JSON
        Map examParameters = (Map) inputJson.get("fields");
        String userName = (String) inputJson.get("userName");
        int noOfQuestions = Integer.parseInt((String) examParameters.get("noOfQuestions"));
        //Here we need to Query the data base on a table which might have schema like 
        //Userid, questionid, questiontype, datetime, nooftimesasked, nooftimescorrect, difficultycatagory
        //this will give us the id of questions to be asked
        //then we will select all these questions

        String ll = questionDAO.selectQuestionJSONString(userName, noOfQuestions,inputJson);
        return ll;
    }

    @Override
    public void updateExamResultJSONString(JSONObject inputJson) {
        questionDAO.updateExamResult(inputJson);

    }

    @Override
    public List getUserQuestions(JSONObject obj) {
        List ll = questionDAO.getUserQuestions(obj);
        return ll;
    }

    @Override
    public String getUserQuestionsJSONString(JSONObject obj) {
        String ll = questionDAO.getUserQuestionsJSONString(obj);
        return ll;
    }

    @Override
    public String getUserChartsJSONString(JSONObject obj) {
        String ll = questionDAO.getUserChartsJSONString(obj);
        return ll;
    }

    @Override
    public WordMeaning searchWord(String word) {

        return null;
    }
    @Override
    public String searchWordJSONString(String word) {

        return questionDAO.searchWordJSONString(word);
    }
}
