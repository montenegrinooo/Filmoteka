package me.fit.rest.server;

import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse.Status;

import me.fit.exception.ActorException;
import me.fit.exception.GenreException;
import me.fit.model.Genre;
import me.fit.service.GenreService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/genre")
public class GenreRest {

	@Inject
	private GenreService genreService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createGenre")
	@Operation(summary = "Web servis koji kreira novi zanr.", description = "Zanr mora biti jedinstven.")
	public Response createGenre(Genre genre) {
		Genre g = null;

		try {
			g = genreService.createGenre(genre);
		} catch (GenreException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
		return Response.ok().entity(g).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllGenres")
	public Response getAllGenres() {
		List<Genre> genres = genreService.getAllGenres();
		return Response.ok().entity(genres).build();
	}

	@DELETE
	@Path("/deleteGenre/{genreId}")
	@Operation(summary = "Izbrisi zanr sa tim ID-jem", description = "Brise zanr na osnovu njegovog ID-a")
	public Response deleteGenre(@PathParam("genreId") Long genreId) {
		try {
			genreService.deleteGenreById(genreId);
			return Response.status(Status.OK).build();
		} catch (GenreException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateGenre/{genreId}/{newGenre}")
	public Response updateGenre(@PathParam("genreId") Long genreId, @PathParam("newGenre") String newGenre) {
		try {
			genreService.updateGenre(genreId, newGenre);
			return Response.status(Status.OK).build();
		} catch (GenreException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}
