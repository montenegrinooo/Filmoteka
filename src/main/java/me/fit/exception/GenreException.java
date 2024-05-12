package me.fit.exception;

public class GenreException extends Exception {
	
	private String message;
	
	public GenreException(String message) {
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
