package me.fit.service;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.enums.ActorStatus;
import me.fit.exception.ActorException;
import me.fit.model.Actor;

@Dependent
public class ActorService {

	@Inject
	private EntityManager eManager;

	@Transactional
	public Actor createActor(Actor a) throws ActorException {
		List<Actor> actors = getAllActors();
		if (actors.contains(a)) {
			throw new ActorException(ActorStatus.EXISTS.getLabel());
		}
		return eManager.merge(a);
	}

	@Transactional
	public List<Actor> getAllActors() {
		List<Actor> actors = eManager.createNamedQuery(Actor.GET_ALL_ACTORS, Actor.class).getResultList();
		return actors;
	}

	@Transactional
	public void deleteActorById(Long actorId) throws ActorException {
		Actor actor = eManager.find(Actor.class, actorId);
		if (actor == null) {
			throw new ActorException("Glumac sa ID-jem  " + actorId + " nije pronadjen");
		}
		eManager.remove(actor);
	}

	@Transactional
	public Actor updateRole(Long actorId, String newRole) throws ActorException {
		Actor actor = eManager.find(Actor.class, actorId);
		if (actor == null) {
			throw new ActorException("Glumac sa ID-jem " + actorId + " nije pronadjen");
		}
		actor.setRole(newRole);
		return eManager.merge(actor);
	}

}
