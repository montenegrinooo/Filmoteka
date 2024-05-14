package me.fit.rest.server;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse.Status;

import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.exception.DirectorException;
import me.fit.model.Director;
import me.fit.service.DirectorService;

@Path("/api/director")
public class DirectorRest {
	@Inject
	private DirectorService directorService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createDirector")
	@Operation(summary = "Web servis koji kreira novog direktora.", description = "Direktor mora biti unikatan.")
	public Response createDirector(Director director) {
		Director d = null;

		try {
			d = directorService.createDirector(director);
		} catch (DirectorException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
		return Response.ok().entity(d).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllDirectors")
	public Response getAllDirectors() {
		List<Director> directors = directorService.getAllDirectors();
		return Response.ok().entity(directors).build();

	}

}
