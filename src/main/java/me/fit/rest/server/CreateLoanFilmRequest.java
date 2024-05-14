package me.fit.rest.server;

import me.fit.model.LoanFilms;

public class CreateLoanFilmRequest {

	private LoanFilms loanFilm;
	private Long userId;
	private Long filmId;

	public LoanFilms getLoanFilm() {
		return loanFilm;
	}

	public void setLoanFilm(LoanFilms loanFilm) {
		this.loanFilm = loanFilm;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFilmId() {
		return filmId;
	}

	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}

	@Override
	public String toString() {
		return "LoanFilmRest [loanFilm=" + loanFilm + ", userId=" + userId + ", filmId=" + filmId + "]";
	}

}
