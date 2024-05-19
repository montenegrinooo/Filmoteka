package me.fit.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = Film.GET_ALL_FILMS, query = "Select f from Film f"),
		@NamedQuery(name = Film.GET_FILM_BY_NAME, query = "Select f from Film f where f.name = :name") })
public class Film {

	public static final String GET_ALL_FILMS = "getAllFilms";
	public static final String GET_FILM_BY_NAME = "getFilmByName";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_seq")

	private Long id;
	private String name;
	private double duration;
	private int quantity;
	private double pricePerDay;

	@ManyToOne
	@JoinColumn(name = "director_id")
	private Director director;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "Film_genre", joinColumns = { @JoinColumn(name = "film_id") }, inverseJoinColumns = {
			@JoinColumn(name = "genre_id") })
	Set<Genre> genres = new HashSet<>();

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<LoanFilms> loanFilms = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Set<LoanFilms> getLoanFilms() {
		return loanFilms;
	}

	public void setLoanFilms(Set<LoanFilms> loanFilms) {
		this.loanFilms = loanFilms;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", duration=" + duration + ", quantity=" + quantity + ", director="
				+ director + ", genres=" + genres + ", loanFilms=" + loanFilms + "]";
	}

}
