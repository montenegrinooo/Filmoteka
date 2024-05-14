package me.fit.enums;

public enum DirectorStatus {

	EXISTS("Reziser vec postoji");

	private String label;

	private DirectorStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
