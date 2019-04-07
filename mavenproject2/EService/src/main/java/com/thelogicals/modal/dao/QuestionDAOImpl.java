/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.modal.dao;

import com.google.gson.Gson;
import com.thelogicals.eservice.utilities.Utilities;
import com.thelogicals.modal.Question;
import com.thelogicals.modal.Topic;
import com.thelogicals.modal.WordMeaning;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AuthProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author prawal
 */
@Repository
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            procedureName = "UserQuestionInteractionProcedure",
            name = "UserQuestionInteractionProcedure"
    )
    ,
    @NamedStoredProcedureQuery(
            procedureName = "UserChartProcedure",
            name = "UserChartProcedure"
    )
    ,
    @NamedStoredProcedureQuery(
            procedureName = "TopicMaintenanceProcedure",
            name = "TopicMaintenanceProcedure"
    ),
    @NamedStoredProcedureQuery(
            procedureName = "MaintenanceProcedure",
            name = "MaintenanceProcedure"
    )}
)

public class QuestionDAOImpl implements QuestionDAO {

    @Autowired
    Utilities utilities;

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addQuestion(Question ptr) {

        Session openSession = sessionFactory.openSession();

        Transaction beginTransaction = openSession.beginTransaction();

        openSession.save(ptr);

        beginTransaction.commit();

        openSession.close();
    }

    @Override
    public void updateQuestions() {

    }

    @Override
    public void removeQuestion(long index) {

    }

    @Override
    public List selectQuestion(String userName, int noofQuestions) {
        Session openSession = sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        StoredProcedureQuery pc = openSession.createStoredProcedureCall("UserQuestionInteractionProcedure")
                .registerStoredProcedureParameter(0,
                        String.class, ParameterMode.IN);
        pc.setParameter(0, userName);

        List<BigInteger> l = pc.getResultList();
        List<Long> ll = new LinkedList<>();
        for (BigInteger b : l) {
            ll.add(b.longValue());
        }

        Query< Question> q = openSession.createQuery("SELECT p FROM WordMeaning p WHERE p.id IN :ids").setParameter("ids", ll);
        Query<Question> q1 = openSession.createQuery("SELECT p FROM PointToRemember p WHERE p.id IN :ids").setParameter("ids", ll);
        List<Question> newList = q.getResultList();
        newList.addAll(q1.getResultList());
        System.out.println("newList: " + newList.get(0).getMeta().getAddedByUser());
        System.out.println("newList: " + newList.get(1).getMeta().getAddedByUser());
        /**
         * Since at the moment I have no choice, but to stringify the object
         * here. Later I will look into Detaching Object from Hibernate Session
         */
        Gson json = utilities.getGson();
        String lls = json.toJson(newList);
        System.out.println("GSON: " + lls);
        beginTransaction.commit();
        openSession.close();
        return newList;
    }

    @Override
    public String selectQuestionJSONString(String userName, int noofQuestions, JSONObject inputJSON) {
        Session openSession = sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        StoredProcedureQuery pc = openSession.createStoredProcedureCall("UserQuestionInteractionProcedure")
                .registerStoredProcedureParameter(0,
                        String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(1,
                        int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2,
                        String.class, ParameterMode.IN);
        pc.setParameter(0, userName);
        pc.setParameter(1, noofQuestions);

        HashMap hm = (HashMap) inputJSON.get("examType");

        pc.setParameter(2, (String) hm.get("href"));
        List<Integer> ll = pc.getResultList();
        List<Long> l = new LinkedList<>();
        //TODO Necessary sin
        for (Integer i : ll) {
            l.add(Long.parseLong(i.toString()));
        }
        Query< Question> q = openSession.createQuery("SELECT p FROM WordMeaning p WHERE p.id IN :ids").setParameter("ids", l);
        Query<Question> q1 = openSession.createQuery("SELECT p FROM PointToRemember p WHERE p.id IN :ids").setParameter("ids", l);
        List<Question> newList = q.getResultList();
        newList.addAll(q1.getResultList());
        Collections.shuffle(newList);
        /**
         * Since at the moment I have no choice, but to stringify the object
         * here. Later I will look into Detaching Object from Hibernate Session
         */
        Gson json = utilities.getGson();
        String lls = json.toJson(newList);
        beginTransaction.commit();
        openSession.close();
        return lls;
    }

    @Override
    public void updateExamResult(JSONObject update) {
        ArrayList pos = ((ArrayList) update.get("positive"));
        ArrayList neg = ((ArrayList) update.get("negative"));
        String user = (String) update.get("userName");
        Session openSession = sessionFactory.openSession();
        Transaction t = openSession.beginTransaction();
        Query queryUserId = openSession.createSQLQuery("Select id from EncephUser where username = :userName");
        queryUserId.setParameter("userName", user);
        List ll = queryUserId.getResultList();
        if (!ll.isEmpty()) {
            System.out.println("ll");
            BigInteger idBig = (BigInteger) ll.get(0);
            int userid = idBig.intValue();
            if (!pos.isEmpty()) {
                //Now make the values part
                StringBuffer sb = new StringBuffer();
                for (int x = 0; x < pos.size(); x++) {
                    Integer sucid;
                    sucid = (Integer) pos.get(x);
                    sb.append("(" + userid + "," + sucid + ",1),");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                Query query = openSession.createSQLQuery("INSERT INTO QuestionUserIntraction (UserId,QuestionId,TimesCorrect) values  " + sb.toString()
                        + "ON DUPLICATE KEY UPDATE LastAsked=current_date(),TimesCorrect =TimesCorrect+1,"
                        + "TimesAsked=TimesAsked+1");
                int x = query.executeUpdate();

            }
            if (!neg.isEmpty()) {
                //Now make the values part
                StringBuffer sb = new StringBuffer();
                for (int x = 0; x < neg.size(); x++) {
                    Integer sucid;
                    sucid = (Integer) neg.get(x);
                    sb.append("(" + userid + "," + sucid + "),");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                Query query = openSession.createSQLQuery("INSERT INTO QuestionUserIntraction (UserId,QuestionId) values  " + sb.toString()
                        + "ON DUPLICATE KEY UPDATE LastAsked=current_date(),"
                        + "TimesAsked=TimesAsked+1");
                int x = query.executeUpdate();

            }
        }
        t.commit();
        openSession.close();

    }

    @Override
    public List getUserQuestions(JSONObject obj) {
        Session openSession = sessionFactory.openSession();
        Transaction t = openSession.beginTransaction();
        Query< Question> q = openSession.createQuery("SELECT p FROM WordMeaning p WHERE p.meta.addedByUser = :userName").setParameter("userName", obj.get("userName"));
        Query<Question> q1 = openSession.createQuery("SELECT p FROM PointToRemember p WHERE p.meta.addedByUser = :userName").setParameter("userName", obj.get("userName"));
        List<Question> newList = q.getResultList();
        newList.addAll(q1.getResultList());

        t.commit();
        openSession.close();
        return new LinkedList();
    }

    @Override
    public String getUserQuestionsJSONString(JSONObject obj) {
        Session openSession = sessionFactory.openSession();
        Transaction t = openSession.beginTransaction();
        Query< Question> q = openSession.createQuery("SELECT p FROM WordMeaning p WHERE p.meta.addedByUser = :userName").setParameter("userName", obj.get("userName"));
        Query<Question> q1 = openSession.createQuery("SELECT p FROM PointToRemember p WHERE p.meta.addedByUser = :userName").setParameter("userName", obj.get("userName"));
        List<Question> wordMeaningList = q.getResultList();
        List<Question> PointToRememberList = q1.getResultList();

        Gson json = utilities.getGson();
        String wms = json.toJson(wordMeaningList);
        String ptrs = json.toJson(PointToRememberList);
        JSONObject resp = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            resp.put("wordMeanings", parser.parse(wms));
            resp.put("pointToRemember", parser.parse(ptrs));
        } catch (ParseException ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        t.commit();
        openSession.close();
        return resp.toJSONString();

    }

    @Override
    public String getUserChartsJSONString(JSONObject obj) {
        Session openSession = sessionFactory.openSession();
        Transaction t = openSession.beginTransaction();
        JSONObject resp = new JSONObject();

        String chartType = (String) obj.get("chartType");

        StoredProcedureQuery pc = openSession.createStoredProcedureCall("UserChartProcedure")
                .registerStoredProcedureParameter(0, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        pc.setParameter(0, obj.get("userName"));
        pc.setParameter(1, chartType.toUpperCase());

        ArrayList l = (ArrayList) pc.getResultList();
        resp.put("data", null);
        Gson json = utilities.getGson();
        JSONParser parser = new JSONParser();
        try {
            resp.put("data", parser.parse(json.toJson(l)));
        } catch (ParseException ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        t.commit();
        openSession.close();
        return resp.toJSONString();

    }

    @Override
    public String searchWordJSONString(String word) {
        Session openSession = sessionFactory.openSession();
        Transaction t = openSession.beginTransaction();
        Query< WordMeaning> q = openSession.createQuery("SELECT p FROM WordMeaning p WHERE p.word = :word").setParameter("word", word);

        List<WordMeaning> wordMeaningList = q.getResultList();
        if (!wordMeaningList.isEmpty()) {
            Gson json = utilities.getGson();
            String wms = json.toJson(wordMeaningList);

            JSONObject resp = new JSONObject();
            JSONParser parser = new JSONParser();
            try {
                resp.put("wordMeanings", parser.parse(wms));

            } catch (ParseException ex) {
                Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            t.commit();
            openSession.close();

            return resp.toJSONString();
        } else {
            return null;
        }
    }

    @Override
    public WordMeaning searchWord(String word) {
        return null;
    }

    @Override
    public void maintenance() {
        Session openSession = sessionFactory.openSession();
        Transaction t = openSession.beginTransaction();
        StoredProcedureQuery pc = openSession.createStoredProcedureCall("MaintenanceProcedure");
        pc.execute();
        t.commit();
        openSession.close();

    }

    @Override
    public void topicMaintenance() {
        Session openSession = sessionFactory.openSession();
        Transaction t = openSession.beginTransaction();
        StoredProcedureQuery pc = openSession.createStoredProcedureCall("TopicMaintenanceProcedure");
        pc.execute();
        t.commit();
        openSession.close();
    }

}
