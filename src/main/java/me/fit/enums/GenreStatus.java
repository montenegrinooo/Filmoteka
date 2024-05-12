package me.fit.enums;

public enum GenreStatus {
	
	EXISTS("Genre already exists");
	
	private String label;

	private GenreStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}


}
