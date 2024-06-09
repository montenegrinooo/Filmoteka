package me.fit.rest.server;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse.Status;

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
import me.fit.exception.DirectorException;
import me.fit.exception.FilmException;
import me.fit.exception.GenreException;
import me.fit.model.Film;
import me.fit.service.FilmService;

@Path("/api/film")
public class FilmRest {

	@Inject
	private FilmService filmService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createFilm")
	@Operation(summary = "Web servis koji kreira novi film", description = "Film mora biti unikatan")
	public Response createFilm(CreateFilmRequest filmRequest) {

		try {
			Film f = filmRequest.getFilm();
			Long directorId = filmRequest.getDirectorId();
			Long actorId = filmRequest.getActorId();

			List<Long> genreIDs = filmRequest.getGenreIDs();
			Film createdFilm = filmService.createFilm(f, directorId, genreIDs, actorId);

			return Response.ok().entity(createdFilm).build();

		} catch (FilmException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		} catch (DirectorException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		} catch (GenreException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllFilms")
	public Response getAllFilms() {
		List<Film> films = filmService.getAllFilms();
		return Response.ok().entity(films).build();
	}

	@DELETE
	@Path("/deleteFilm/{filmId}")
	@Operation(summary = "Izbrisi film sa tim ID-jem", description = "Brise film na osnovu njegovo ID-a")
	public Response deleteFilm(@PathParam("filmId") Long filmId) {
		try {
			filmService.deleteFilmById(filmId);
			return Response.status(Status.OK).build();
		} catch (FilmException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}

	}

	@PUT
	@Path("/updateFilmQuantityByName/{name}/{quantityToAdd}")
	@Operation(summary = "Azuriraj kolicinu filmova po imenu", description = "Dodaj kolicinu filmova na vec postojecu kolicinu")
	public Response updateFilmQuantityByName(@PathParam("name") String name,
			@PathParam("quantityToAdd") int quantityToAdd) {
		try {
			filmService.updateFilmQuantityByName(name, quantityToAdd);
			return Response.status(Status.OK).build();
		} catch (FilmException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Greska prilikom azuriranja kolicine filmova: " + e.getMessage()).build();
		}
	}

}
