package me.fit.enums;

public enum UserStatus {

	EXISTS("User already exists");
	
	private String label;

	private UserStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
