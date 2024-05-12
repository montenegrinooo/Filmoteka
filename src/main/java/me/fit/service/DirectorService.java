package me.fit.service;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.enums.DirectorStatus;
import me.fit.exception.DirectorException;
import me.fit.model.Director;

@Dependent
public class DirectorService {

	@Inject
	private EntityManager eManager;
	
	@Transactional
	public Director createDirector(Director d) throws DirectorException{
		List<Director> directors = getAllDirectors();
		
		if(directors.contains(d)) {
			throw new DirectorException(DirectorStatus.EXISTS.getLabel());
		}
		
		return eManager.merge(d);
	}
	
	@Transactional
	public List<Director> getAllDirectors(){
		List<Director> directors = eManager.createNamedQuery(Director.GET_ALL_DIRECTORS, Director.class).getResultList();
		return directors;
	}
}
