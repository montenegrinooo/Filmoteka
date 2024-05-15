package me.fit.rest.server;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse.Status;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
import me.fit.exception.UserException;
import me.fit.model.Users;
import me.fit.service.UserService;

@Path("/api/user")
public class UserRest {

	@Inject
	private UserService userService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createUser")
	@Operation(summary = "Web servis koji kreira novog usera.", description = "User mora biti unikatan.")
	public Response createUser(Users user) {
		Users u = null;

		try {
			u = userService.createUser(user);
			return Response.status(Status.CREATED).entity(u).build();
		} catch (UserException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllUsers")
	public Response getAllUsers() {
		List<Users> users = userService.getAllUsers();
		return Response.ok().entity(users).build();
	}

	@DELETE
	@Path("/deleteUser/{userId}")
	@Operation(summary = "Izbrisi korisnika sa tim ID-jem", description = "Brise korisnika na osnovu njegovog ID-a")
	public Response deleteUser(@PathParam("userId") Long userId) {
		try {
			userService.deleteUserById(userId);
			return Response.status(Status.OK).build();
		} catch (UserException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUsersByName/{name}")
	public Response getUserByName(@PathParam("name") String name) {
		if (name == null || name.isEmpty()) {
			return Response.status(Status.BAD_REQUEST).entity("Ime je obavezzno").build();
		}

		List<Users> users = userService.getUsersByName(name);
		if (users.isEmpty()) {
			return Response.status(Status.NOT_FOUND).entity("Nije pronadjen korisnik sa tim imenom").build();
		}
		return Response.ok().entity(users).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateUserEmail/{userId}/{newEmail}")
	public Response updateUserEmail(@PathParam("userId") Long userId, @PathParam("newEmail") String newEmail) {
		try {
			userService.updateUserEmail(userId, newEmail);
			return Response.status(Status.OK).build();
		} catch (UserException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}
