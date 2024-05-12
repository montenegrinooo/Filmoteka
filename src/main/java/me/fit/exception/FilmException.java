package me.fit.exception;

public class FilmException extends Exception{
	
	private String message;
	
	public FilmException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
