package me.fit.rest.server;

import java.util.List;

import me.fit.model.Film;

public class CreateFilmRequest {

	private Film film;
	private Long directorId;
	private Long actorId;
	private List<Long> genreIDs;

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public List<Long> getGenreIDs() {
		return genreIDs;
	}

	public void setGenreIDs(List<Long> genreIDs) {
		this.genreIDs = genreIDs;
	}

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	@Override
	public String toString() {
		return "CreateFilmRequest [film=" + film + ", directorId=" + directorId + ", genreIDs=" + genreIDs + "]";
	}

}
