package me.fit.exception;

public class DirectorException extends Exception {

	private String message;

	public DirectorException(String message) {
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
