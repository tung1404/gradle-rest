package vn.tungnt.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.tungnt.service.IFilmService;

@Component
@Path("/films")
public class FilmResource {
	
	@Autowired
	IFilmService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFilms() {
		return Response.ok().build();
	}
}