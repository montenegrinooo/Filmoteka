package me.fit.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.enums.FilmStatus;
import me.fit.exception.DirectorException;
import me.fit.exception.FilmException;
import me.fit.exception.GenreException;
import me.fit.model.Director;
import me.fit.model.Film;
import me.fit.model.Genre;

@Dependent
public class FilmService {

	@Inject
	private EntityManager eManager;

	@Transactional
	public Film createFilm(Film f, Long directorId, List<Long> genreIDs)
			throws FilmException, DirectorException, GenreException {

		Director director = eManager.find(Director.class, directorId);
		if (director == null) {
			throw new DirectorException("Direktor sa tim id-jem " + directorId + " nije pronadjen");
		}

		Set<Genre> genres = new HashSet<>();
		for (Long genreId : genreIDs) {
			Genre genre = eManager.find(Genre.class, genreId);
			if (genre == null) {
				throw new GenreException("Zanr sa tim id-jem " + genreId + " nije pronadjen");
			}
			genres.add(genre);
		}
		f.setDirector(director);
		f.setGenres(genres);

		List<Film> films = getAllFilms();

		if (films.contains(f)) {
			throw new FilmException(FilmStatus.EXISTS.getLabel());
		}
		return eManager.merge(f);
	}

	@Transactional
	public List<Film> getAllFilms() {
		List<Film> films = eManager.createNamedQuery(Film.GET_ALL_FILMS, Film.class).getResultList();
		return films;
	}

	@Transactional
	public void deleteFilmById(Long filmId) throws FilmException {
		Film film = eManager.find(Film.class, filmId);
		if (film == null) {
			throw new FilmException("Film sa ID-jem: " + filmId + " nije pronadjen");
		}
		eManager.remove(film);
	}

	@Transactional
	public void updateFilmQuantityByName(String filmName, int quantityToAdd) throws FilmException {
		try {
			Film film = eManager.createNamedQuery(Film.GET_FILM_BY_NAME, Film.class).setParameter("name", filmName)
					.getSingleResult();
			if (film != null) {
				film.setQuantity(film.getQuantity() + quantityToAdd);
				eManager.merge(film);
			} else {
				throw new FilmException("Nema filma sa imenom: " + filmName);
			}
		} catch (Exception e) {
			throw new FilmException("Greska prilikom azuriranja broja filmova: " + e.getMessage());
		}

	}

}
