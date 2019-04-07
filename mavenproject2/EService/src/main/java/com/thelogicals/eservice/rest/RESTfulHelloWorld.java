/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals.eservice.rest;

/**
 *
 * @author prawal
 */
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;

@Path("/Hello")
public class RESTfulHelloWorld {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    public String getStartingPage() {
        
        JSONObject obj=new JSONObject();
        obj.put("myKey", "MyVal");
        return obj.toJSONString();
    }
}
