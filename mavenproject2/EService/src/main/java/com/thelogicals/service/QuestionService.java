/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.service;

import com.thelogicals.modal.PointToRemember;
import com.thelogicals.modal.WordMeaning;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author prawal
 */
 public interface QuestionService {
    void dummy();
    void addPointToRemember(PointToRemember ptr);
    void addWordMeaning(WordMeaning ptr);
    /**
     * The decision of taking JSONObject as input parameter is not very well thought of.
     * @param inputJson
     * @return 
     */
    List createQuestionPaper(JSONObject inputJson);
    String createQuestionPaperJSONString(JSONObject inputJson);
    void updateExamResultJSONString(JSONObject inputJson);
    List getUserQuestions(JSONObject obj);
    String getUserQuestionsJSONString(JSONObject obj);
    String getUserChartsJSONString(JSONObject obj);
    WordMeaning searchWord(String word);
    String searchWordJSONString(String word);
//    void addWordMeaning(WordMeaning ptr);
}
