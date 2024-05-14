package me.fit.service;

import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.enums.GenreStatus;
import me.fit.exception.GenreException;
import me.fit.model.Genre;

@Dependent
public class GenreService {

	@Inject
	private EntityManager eManager;
	
	@Transactional
	public Genre createGenre(Genre g) throws GenreException{
		List<Genre> genres = getAllGenres();
		
		if(genres.contains(g)) {
			throw new GenreException(GenreStatus.EXISTS.getLabel());
		}
		return eManager.merge(g);
	}
	
	@Transactional
	public List<Genre> getAllGenres(){
		List<Genre> genres = eManager.createNamedQuery(Genre.GET_ALL_GENRES, Genre.class).getResultList();
		return genres;
	}
	
	@Transactional
	public void deleteGenreById(Long genreId) throws GenreException{
		Genre genre = eManager.find(Genre.class, genreId);
		if(genre == null) {
			throw new GenreException("Zanr sa ID-jem: " + genreId + " nije pronadjen");
		}
		eManager.remove(genre);
	}
	
}
