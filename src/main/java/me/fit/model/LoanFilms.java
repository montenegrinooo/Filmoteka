package me.fit.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class LoanFilms {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanFilms_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;


	private Date loanDate;
	private Date returnDate;
	private boolean returned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	@Override
	public String toString() {
		return "LoanFilms [id=" + id + ", film=" + film + ", user=" + user + ", loanDate=" + loanDate + ", returnDate="
				+ returnDate + ", returned=" + returned + "]";
	}

}
