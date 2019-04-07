/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal.dao;

import com.thelogicals.modal.Question;
import com.thelogicals.modal.WordMeaning;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author prawal
 */
public interface QuestionDAO {
    void addQuestion(Question ptr);
    void updateQuestions();
    List selectQuestion(String userName, int noofQuestions);
    String selectQuestionJSONString(String userName, int noofQuestions,JSONObject inputJSONObject);
    void removeQuestion(long index);
    List getUserQuestions(JSONObject obj);
    String getUserQuestionsJSONString(JSONObject obj);
    String getUserChartsJSONString(JSONObject obj);
    
    void updateExamResult(JSONObject update);
    WordMeaning searchWord(String word);
    String searchWordJSONString(String word);
    void maintenance();
    void topicMaintenance();
}
