package me.fit.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.enums.LoanFilmStatus;
import me.fit.exception.FilmException;
import me.fit.exception.LoanFilmException;
import me.fit.model.Film;
import me.fit.model.LoanFilms;
import me.fit.model.Users;

@Dependent
public class LoanFilmService {

	@Inject
	private EntityManager eManager;

	@Transactional
	public LoanFilms createLoanFilm(LoanFilms l, Long userId, Long filmId) throws LoanFilmException, FilmException {

		Users user = eManager.find(Users.class, userId);

		if (user == null) {
			throw new LoanFilmException("User sa id-jem " + userId + " nije pronadjen.");
		}

		Film film = eManager.find(Film.class, filmId);
		if (film == null) {
			throw new FilmException("Film sa id-jem " + filmId + " nije pronadjen");
		}

		if (film.getQuantity() <= 0) {
			throw new LoanFilmException("Nemamo vise filmova sa ID-jem " + filmId + " u ponudi");
		}
		film.setQuantity(film.getQuantity() - 1);

		l.setFilm(film);
		l.setUser(user);

		l.setLoanDate(Date.valueOf(LocalDate.now()));
		l.calculatorTotalPrice();

		List<LoanFilms> loanFilms = getAllLoanFilms();

		if (loanFilms.contains(l)) {
			throw new LoanFilmException(LoanFilmStatus.EXISTS.getLabel());
		}
		return eManager.merge(l);
	}

	@Transactional
	public List<LoanFilms> getAllLoanFilms() {
		List<LoanFilms> loanFilms = eManager.createNamedQuery(LoanFilms.GET_ALL_LOAN_FILMS, LoanFilms.class)
				.getResultList();
		return loanFilms;
	}

	@Transactional
	public void deleteLoanFilmById(Long loanFilmId) throws LoanFilmException {
		LoanFilms loanFilm = eManager.find(LoanFilms.class, loanFilmId);
		if (loanFilm == null) {
			throw new LoanFilmException("Iznajmljeni film sa ID-jem " + loanFilmId + " nije pronadjen");
		}
		eManager.remove(loanFilm);
	}

	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
	public void checkAndUpdateReturnStatus() {
		List<LoanFilms> loanFilms = getAllLoanFilms();
		LocalDate today = LocalDate.now();

		for (LoanFilms loanFilm : loanFilms) {
			if (loanFilm.getReturnDate() != null && loanFilm.getReturnDate().toLocalDate().isBefore(today)
					&& !loanFilm.isReturned()) {
				loanFilm.setReturned(true);
				loanFilm.getFilm().setQuantity(loanFilm.getFilm().getQuantity()+1);
				eManager.merge(loanFilm);
			}
		}
	}

}
