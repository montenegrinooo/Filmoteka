package me.fit.exception;

public class LoanFilmException extends Exception {

	private String message;

	public LoanFilmException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
