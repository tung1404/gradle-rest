package vn.tungnt.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/actors")
public class ActorResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActors() {
		return Response.ok().build();
	}
}
