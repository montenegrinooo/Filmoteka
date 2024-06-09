package me.fit.enums;

public enum ActorStatus {

	EXISTS("Glumac vec postoji");

	private String label;

	private ActorStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
