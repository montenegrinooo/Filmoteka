package me.fit.rest.server;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import me.fit.exception.ActorException;
import me.fit.model.Actor;
import me.fit.model.rest.client.Country;
import me.fit.service.ActorService;
import me.fit.service.CountryService;

@Path("/api/actor")
public class ActorRest {

	@Inject
	private ActorService actorService;

	@Inject
	private CountryService countryService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createActor")
	@Operation(summary = "Web servis koji kreira novog glumca.", description = "Glumac mora biti unikatan.")
	public Response createActor(ActorRequest request) {

		try {
			Actor actor = request.getActor();
			Country country = countryService.getCountryByName(request.getCountryName());
			if (country == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Ta drzava ne postoji").build();
			}
			actor.setCountry(country);
			actor = actorService.createActor(actor);
			return Response.ok().entity(actor).build();
		} catch (ActorException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllActors")
	public Response getAllActors() {
		List<Actor> actors = actorService.getAllActors();
		return Response.ok().entity(actors).build();
	}

	@DELETE
	@Path("/deleteActor/{actorId}")
	@Operation(summary = "Izbrisi glumca sa tim ID-jem ", description = "Brise glumca na osnovu njegovod ID-a")
	public Response deleteActor(@PathParam("actorId") Long actorId) {
		try {
			actorService.deleteActorById(actorId);
			return Response.status(Status.OK).build();
		} catch (ActorException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateRole/{actorId}/{newRole}")
	public Response updateRole(@PathParam("actorId") Long actorId, @PathParam("newRole") String newRole) {
		try {
			actorService.updateRole(actorId, newRole);
			return Response.status(Status.OK).build();
		} catch (ActorException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
}
