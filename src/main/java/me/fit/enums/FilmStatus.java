package me.fit.enums;

public enum FilmStatus {
	
	EXISTS("Film already EXISTS");
	
	private String label;

	private FilmStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
