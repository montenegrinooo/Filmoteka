package me.fit.rest.server;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse.Status;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.model.LoanFilms;
import me.fit.service.LoanFilmService;

@Path("/api/loanFilm")
public class LoanFilmRest {

	@Inject
	private LoanFilmService loanFilmService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createLoanFilm")
	@Operation(summary = "Web servis koji kreira novi iznajmljeni film.", description = "Iznajmljeni film")
	public Response createLoanFilm(CreateLoanFilmRequest loanFilmRequest) {

		try {
			LoanFilms loanFilm = loanFilmRequest.getLoanFilm();
			Long userId = loanFilmRequest.getUserId();
			Long filmId = loanFilmRequest.getFilmId();

			LoanFilms createdLoanFilm = loanFilmService.createLoanFilm(loanFilm, userId, filmId);

			return Response.ok().entity(createdLoanFilm).build();
		} catch (Exception e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllLoanFilms")
	public Response getAllLoanFilms() {
		List<LoanFilms> loanFilms = loanFilmService.getAllLoanFilms();
		return Response.ok().entity(loanFilms).build();
	}

	@DELETE
	@Path("/deleteLoanFilm/{loanFilmId}")
	@Operation(summary = "Izbrisi iznajmljeni film sa tim ID-jem ", description = "Brise iznajmljeni film na osnovu njegovog ID-a")
	public Response deleteLoanFilm(@PathParam("loanFilmId") Long loanFilmId) {
		try {
			loanFilmService.deleteLoanFilmById(loanFilmId);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}

	}

}
