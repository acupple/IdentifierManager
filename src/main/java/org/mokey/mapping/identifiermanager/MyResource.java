package org.mokey.mapping.identifiermanager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.Request;
import org.mokey.mapping.identifiermanager.models.Identifier;

@Path("welcome")
public class MyResource {
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt(@Context Request request) {

    	Identifier id = new Identifier(request.getRemoteAddr(), 
    			request.getHeader("user-agent"), 
    			request.getHeader("accept-language"));
   
        return Response.ok(id.toString()).build();
    }
}
