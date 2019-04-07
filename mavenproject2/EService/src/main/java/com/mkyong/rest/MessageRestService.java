package com.mkyong.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService {

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) {

        String result = "Restful example : " + msg;
        System.out.println("Attempting Hibernate");
        
        System.out.println("em: "+em);
        return Response.status(200).entity(result).build();

    }

    @PersistenceContext(unitName = "EncephPU")
    EntityManager em;
}
