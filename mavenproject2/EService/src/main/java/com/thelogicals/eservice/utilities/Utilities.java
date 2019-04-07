/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.eservice.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.internet.code.HibernateProxyTypeAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author prawal
 */
public class Utilities {
//
//    private static EntityManagerFactory emf;
//
////    private static StandardServiceRegistry ssr;
////    private static Metadata meta;
////    private static SessionFactory factory;
//    static {
////        System.out.println("Static initialization");
//////        ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
////        System.out.println("ssr: "+ssr);
//////        meta = new MetadataSources(ssr).getMetadataBuilder().build();
////        System.out.println("meta: "+meta);
//////        factory = meta.getSessionFactoryBuilder().build();
////        System.out.println("factory: "+factory);
//        emf = Persistence.createEntityManagerFactory("EncephPU");
//    }
//
//    public static EntityManager getPeristence() {
//        System.out.println("Got Request getPeristence");
////        Session session = factory.openSession();
////        System.out.println("returning session: " + session);
////        return session;
//        return emf.createEntityManager();
//    }
//    

    public String fetchWordFromOxford(String word) {
        final String language = "en";
//        final String word = "Ace";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        String s = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
        //TODO: replace with your own app id and app key
        final String app_id = "a39b2c0d";
        final String app_key = "171fd439c57fadcec7c7996928a1bba7";
        try {
            URL url = new URL(s);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }


    public Gson getGson() {
        GsonBuilder b = new GsonBuilder();

        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);

        Gson gson = b.create();
        return gson;
    }
}
