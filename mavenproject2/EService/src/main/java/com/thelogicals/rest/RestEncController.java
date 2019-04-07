/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.rest;

import com.google.gson.Gson;
import com.thelogicals.eservice.utilities.Utilities;
import com.thelogicals.modal.ModalClass;
import com.thelogicals.modal.PointToRemember;
import com.thelogicals.modal.QuestionMeta;
import com.thelogicals.modal.QuestionTypeEnum;
import com.thelogicals.modal.WordMeaning;
import com.thelogicals.service.QuestionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author prawal
 */
@RestController
public class RestEncController {

    @Autowired
    Utilities utilities;

    @Autowired
    @Qualifier("dbDataSource")
    private DataSource dataSource;

    private QuestionService qServ;
    //-------------------Retrieve All Users--------------------------------------------------------

    @Autowired(required = true)
    @Qualifier(value = "questionService")
    public void setqServ(QuestionService qServ) {
        this.qServ = qServ;
    }

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<ModalClass> listAllUsers() {
        ModalClass md = new ModalClass();
        md.setString(dataSource != null);
        md.setqServ(qServ != null);
        return new ResponseEntity<>(md, HttpStatus.OK);
    }

    @RequestMapping(value = "/pointToRemember/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ModalClass> addPointToRemember(@RequestBody JSONObject obj) {
        ModalClass md = new ModalClass();
        System.out.println(" JSON: " + obj.toJSONString());
        PointToRemember ptr = new PointToRemember();
        QuestionMeta qm = new QuestionMeta();
        qm.setAddedByUser((String) obj.get("userName"));
        Boolean b = (Boolean) obj.get("personalQuestion");
        qm.setPersonalQuestion((b == null) ? false : b);
        ptr.setType(QuestionTypeEnum.POINT_TO_REMEMBER);
        ptr.setMeta(qm);
        System.out.println("Created point");
        ptr.setPoint((String) obj.get("point"));
        ptr.setAnswer((String) obj.get("answer"));
        System.out.println("Sending to service");
        qServ.addPointToRemember(ptr);
        return new ResponseEntity<>(md, HttpStatus.OK);
    }

    @RequestMapping(value = "/wordMeaning/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addWordMeaning(@RequestBody JSONObject obj) {

        System.out.println(" JSON: " + obj.toJSONString());
        WordMeaning wm = new WordMeaning();
        ArrayList arr = (ArrayList) obj.get("results");
        Map tmpObj = (Map) arr.get(0);
        wm.setWord((String) tmpObj.get("word"));
        QuestionMeta qm = new QuestionMeta();
        wm.setOxfordResponse(obj);
        wm.setType(QuestionTypeEnum.WORD_MEANING);
        qm.setAddedByUser((String) obj.get("userName"));
        wm.setMeta(qm);
        qServ.addWordMeaning(wm);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/revisionQuestionPaper/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity revisionQuestionPaper(@RequestBody JSONObject inputJson) {
        //TODO Write some input JSON Validation Code here, what if userName is not sent in input
        JSONObject preparedExam = new JSONObject();
   
        String ll = qServ.createQuestionPaperJSONString(inputJson);

        preparedExam.put("questionList", (ll));
        System.out.println("Prepared Question: " + preparedExam.toJSONString());
        return new ResponseEntity<>(preparedExam, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchWord/{word}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> searchWordMeaning(@PathVariable("word") String word) {

        try {
            System.out.println("word: " + word);
            //TODO check if word already exists in the database
            //qServ.searchWordJSONString(word);
            String result = utilities.fetchWordFromOxford(word);
            JSONParser parser = new JSONParser();
            JSONObject jObj = (JSONObject) parser.parse(result);
            System.out.println("result: " + result);

            return new ResponseEntity<>(jObj, HttpStatus.OK);
        } catch (ParseException ex) {
            Logger.getLogger(RestEncController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject obj = new JSONObject();
        return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/testingMethod/", method = RequestMethod.GET)
    public ResponseEntity<JSONArray> testingMethod() {

//        List ll = qServ.createQuestionPaper();
        JSONArray arr = new JSONArray();
//        for (Object o : ll) {
//            arr.add(o);
//        }
        System.out.println("result: " + arr.toJSONString());

        return new ResponseEntity<>(arr, HttpStatus.OK);

    }

    @RequestMapping(value = "/examResult/", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> examResult(@RequestBody JSONObject inputJson) {

//        List ll = qServ.createQuestionPaper();
        JSONObject arr = new JSONObject();
        qServ.updateExamResultJSONString(inputJson);
        System.out.println("result: " + arr.toJSONString());

        return new ResponseEntity<>(arr, HttpStatus.OK);

    }

    @RequestMapping(value = "/myQuestions/", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> myQuestions(@RequestBody JSONObject inputJson) {

//        List ll = qServ.getUserQuestions(inputJson);
        String ll = qServ.getUserQuestionsJSONString(inputJson);
        JSONParser parser = new JSONParser();

        JSONObject obj = new JSONObject();

        try {
            obj.putAll((JSONObject) parser.parse(ll));
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);

    }
    @RequestMapping(value = "/myCharts/", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> myCharts(@RequestBody JSONObject inputJson) {
        
//        List ll = qServ.getUserQuestions(inputJson);
        String ll = qServ.getUserChartsJSONString(inputJson);
        JSONParser parser = new JSONParser();

        JSONObject obj = new JSONObject();

        try {
            obj.putAll((JSONObject) parser.parse(ll));
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);

    }
}
