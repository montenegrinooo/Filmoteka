package me.fit.multipart;

import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.core.MediaType;
import me.fit.multipart.MultipartResource.UploadSchema;

public class MultipartRequest {
	@RestForm("file")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	@Schema(implementation = UploadSchema.class)
	private FileUpload file;

	@RestForm("name")
	@PartType(MediaType.TEXT_PLAIN)
	private String name;

	@RestForm("lastName")
	@PartType(MediaType.TEXT_PLAIN)
	private String lastName;

	@RestForm("email")
	@PartType(MediaType.TEXT_PLAIN)
	private String email;

	@RestForm("birthDate")
	@PartType(MediaType.TEXT_PLAIN)
	private Date birthDate;

	@RestForm("jmbg")
	@PartType(MediaType.TEXT_PLAIN)
	private String jmbg;

	@RestForm("phones")
	@PartType(MediaType.TEXT_PLAIN)
	private List<String> phones;

	public FileUpload getFile() {
		return file;
	}

	public void setFile(FileUpload file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

}
