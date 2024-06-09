package me.fit.rest.server;

import me.fit.model.Actor;

public class ActorRequest {

	private Actor actor;
	private String countryName;

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "ActorRequest [actor=" + actor + ", countryName=" + countryName + "]";
	}

}
