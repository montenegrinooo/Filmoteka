package me.fit.service;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.enums.FilmStatus;
import me.fit.exception.FilmException;
import me.fit.model.Film;

@Dependent
public class FilmService {

	@Inject
	private EntityManager eManager;
	
	@Transactional
	public Film createFilm(Film f) throws FilmException{
		List<Film> films = getAllFilms();
		
		if(films.contains(f)) {
			throw new FilmException(FilmStatus.EXISTS.getLabel());
		}
		return eManager.merge(f);
	}
	
	@Transactional
	public List<Film> getAllFilms(){
		List<Film> films = eManager.createNamedQuery(Film.GET_ALL_FILMS,Film.class).getResultList();
		return films;
	}
}
