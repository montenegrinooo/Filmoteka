package me.fit.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import me.fit.model.IpLog;

@Path("/data")
@RegisterRestClient
public interface IpClient {

	@GET
	@Path("/client-ip")
	IpLog getIp();

}
