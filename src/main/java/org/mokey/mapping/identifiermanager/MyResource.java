package org.mokey.mapping.identifiermanager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.Request;

@Path("welcome")
public class MyResource {
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt(@Context Request request) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("hello: ").append(System.lineSeparator())
    		.append(request.getHeader("user-agent")).append(System.lineSeparator())
    		.append(request.getRemoteAddr()).append(System.lineSeparator())
    		.append(request.getHeader("accept-language"));
        return Response.ok(sb.toString()).build();
    }
}
