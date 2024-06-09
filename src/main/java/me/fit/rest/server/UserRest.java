package me.fit.rest.server;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.multipart.FileUpload;

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
import me.fit.exception.UserException;
import me.fit.model.IpLog;
import me.fit.model.Phone;
import me.fit.model.Users;
import me.fit.multipart.MultipartRequest;
import me.fit.rest.client.IpClient;
import me.fit.service.UserService;

@Path("/api/user")
public class UserRest {

	@Inject
	private UserService userService;

	@Inject
	@RestClient
	private IpClient ipClient;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/createUser")
	@Operation(summary = "Web servis koji kreira novog usera.", description = "User mora biti unikatan.")
	public Response createUser(MultipartRequest request) {
		Users u = new Users();
		u.setName(request.getName());
		u.setLastName(request.getLastName());
		u.setEmail(request.getEmail());
		u.setJmbg(request.getJmbg());
		FileUpload fileUpload = request.getFile();
		if (fileUpload == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Nedostaje fajl").build();
		}

		Set<Phone> phones = new HashSet<>();
		for (String phone : request.getPhones()) {
			Phone p = new Phone();
			p.setNumber(phone);
			phones.add(p);
		}
		u.setPhones(phones);

		try {
			byte[] image = Files.readAllBytes(fileUpload.uploadedFile().toAbsolutePath());
			IpLog ipLog = ipClient.getIp();
			ipLog.setCreatedDate(new Date());
			u = userService.createUser(u, ipLog, image);
			return Response.status(Status.CREATED).entity(u).build();
		} catch (UserException | IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Greska prilikom postavljanja fajla").build();
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
