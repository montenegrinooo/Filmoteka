package me.fit.enums;

public enum LoanFilmStatus {

	EXISTS("Loan film already exists");

	private String label;

	private LoanFilmStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
