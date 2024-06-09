package me.fit.multipart;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/multipart")
public class MultipartResource {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String hello(MultipartRequest request) {
		return "file:" + request.getFile() + " name:" + request.getName();
	}

	@Schema(type = SchemaType.STRING, format = "binary")
	public static class UploadSchema {
	};
}