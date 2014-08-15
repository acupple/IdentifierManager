package org.mokey.mapping.identifiermanager;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableUserAgent;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.grizzly.http.server.Request;
import org.mokey.mapping.identifiermanager.dal.Database;
import org.mokey.mapping.identifiermanager.models.Identifier;
import org.mokey.mapping.identifiermanager.utils.IdentityMapper;
import org.mokey.mapping.identifiermanager.utils.UAParser;

@Path("welcome")
public class MyResource {
	
	Database db = new Database();
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt(@Context Request request) {
    	Identifier id = new Identifier();
    	try{
	    	id.setUuid(UUID.randomUUID().toString());
			id.setIp(request.getRemoteAddr());
			
			String[] tokens = StringUtils.split(id.getIp(),".");
			if(tokens.length != 4){
				throw new Exception("IP Format Error.");
			}
			id.setIp3(String.format("%s.%s.%s",tokens[0], tokens[1], tokens[3])); //Target collection parameter
			
			id.setUa(request.getHeader("user-agent"));;
			ReadableUserAgent agent = UAParser.parse(id.getUa());
			OperatingSystem ops = agent.getOperatingSystem();
			id.setOs(ops.getFamilyName());
			tokens = ops.getName().split(" ");
			if(tokens != null && tokens.length > 1){
				id.setOsvs(Integer.parseInt(tokens[1]));
			}
			
			id.setBrowser(agent.getFamily().getName().toLowerCase());
			
			tokens = request.getHeader("accept-language").split(",");
			if(tokens != null)
				id.setLng(tokens[0]);
			
			Identifier t = IdentityMapper.map(id);
			if(t == null)
				db.insert(id);
			else{
				id = t;
			}
			
    	} catch(Exception e){
    		return Response.serverError().build();
    	}
        return Response.ok(id.toString()).build();
    }
}
