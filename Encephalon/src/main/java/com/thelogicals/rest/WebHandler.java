/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author prawal
 */
@RestController
public class WebHandler {

    @RequestMapping(value = "/account/addPointToRemember", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> addPointToRemember(@RequestBody JSONObject obj) {
        UserDetails userDetails
                = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String s = userDetails.getUsername();
        obj.put("userName", s);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<JSONObject> ss = rt.postForEntity("http://localhost:8080/EService-1.0-SNAPSHOT/pointToRemember/", obj, JSONObject.class);
        JSONObject response = ss.getBody();
        //We should analyse the response and send back appropriate response
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/addWordMeaning", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> addWordMeaning(@RequestBody JSONObject obj) {
        UserDetails userDetails
                = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String s = userDetails.getUsername();
        obj.put("userName", s);
        RestTemplate rt = new RestTemplate();
        System.out.println("maven add word meaning json: " + obj.toJSONString());
        ResponseEntity<JSONObject> ss = rt.postForEntity("http://localhost:8080/EService-1.0-SNAPSHOT/wordMeaning/", obj, JSONObject.class);
        JSONObject response = ss.getBody();
        //We should analyse the response and send back appropriate response
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/revisionQuestionPaper", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> revisionQuestionPaper(@RequestBody JSONObject obj) {
        UserDetails userDetails
                = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String s = userDetails.getUsername();
        obj.put("userName", s);
        RestTemplate rt = new RestTemplate();
        System.out.println("getting revision question paper : " + obj.toJSONString());
        ResponseEntity<JSONObject> ss = rt.postForEntity("http://localhost:8080/EService-1.0-SNAPSHOT/revisionQuestionPaper/", obj, JSONObject.class);
        JSONObject response = ss.getBody();
        /**
         * Dirty Code alert: Here I will manipulate the response from EService
         * to remove un wanted data from JSON
         */
        String questionList = (String) response.get("questionList");
        JSONParser parser = new JSONParser();
        try {
            JSONArray arr = (JSONArray) parser.parse(questionList);
            JSONArray arr1 = new JSONArray();
            for (int x = 0; x < arr.size(); x++) {
                JSONObject obj1;
                JSONObject newObj = new JSONObject();
                obj1 = (JSONObject) arr.get(x);
                if (obj1.containsKey("word")) {
                    //Word Meaning Object Manipulation
                    newObj.put("id", obj1.get("id"));
                    JSONObject ob = (JSONObject) parser.parse((String) obj1.get("oxfordResponse"));
                    ob.remove("userName");
                    ob.put("questionType", "Word Meaning");
                    newObj.putAll(ob);
                    arr1.add(newObj);
                } else if (obj1.containsKey("point")) {
                    obj1.remove("meta");

                    obj1.put("questionType", "Point To Remember");
                    arr1.add(obj1);
                }
            }
            response.put("questionList", arr1);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        //Here we expect complete question paper JSON
        //We should analyse the response and send back appropriate response
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/searchWord/{word}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> searchWord(@PathVariable("word") String word) {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<JSONObject> ss = rt.getForEntity("http://localhost:8080/EService-1.0-SNAPSHOT/searchWord/" + word, JSONObject.class);
        JSONObject response = ss.getBody();
        //We should analyse the response and send back appropriate response
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/examResult", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> examResult(@RequestBody JSONObject obj) {
        RestTemplate rt = new RestTemplate();
        UserDetails userDetails
                = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String s = userDetails.getUsername();
        obj.put("userName", s);
        
        ResponseEntity<JSONObject> ss = rt.postForEntity("http://localhost:8080/EService-1.0-SNAPSHOT/examResult/", obj, JSONObject.class);
        JSONObject response = new JSONObject();
        //We should analyse the response and send back appropriate response
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "/account/myQuestions", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> myQuestions() {
        RestTemplate rt = new RestTemplate();
        JSONObject obj=new JSONObject();
        UserDetails userDetails
                = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String s = userDetails.getUsername();
        obj.put("userName", s);
        
        ResponseEntity<JSONObject> ss = rt.postForEntity("http://localhost:8080/EService-1.0-SNAPSHOT/myQuestions/", obj, JSONObject.class);
        JSONObject response = new JSONObject();
        response.putAll(ss.getBody());

        /**
         * Dirty Code alert: Here I will manipulate the response from EService
         * to remove un wanted data from JSON
         */
        //We should analyse the response and send back appropriate response
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/account/trashQuestion/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> trashQuestion(@PathVariable("id") String id) {
        RestTemplate rt = new RestTemplate();
        UserDetails userDetails
                = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        
        JSONObject response = new JSONObject();
        response.put("al", "this is response");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "/account/myCharts", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> myChartData(@RequestBody JSONObject obj) {
        
        RestTemplate rt = new RestTemplate();
//        JSONObject obj=new JSONObject();
        UserDetails userDetails
                = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String s = userDetails.getUsername();
        obj.put("userName", s);
        
        ResponseEntity<JSONObject> ss = rt.postForEntity("http://localhost:8080/EService-1.0-SNAPSHOT/myCharts/", obj, JSONObject.class);
        JSONObject response = new JSONObject();
        response.putAll(ss.getBody());

        //We should analyse the response and send back appropriate response
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
