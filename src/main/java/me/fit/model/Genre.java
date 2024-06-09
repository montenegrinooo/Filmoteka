package me.fit.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = Genre.GET_ALL_GENRES, query = "SELECT g FROM Genre g") })
public class Genre {

	public static final String GET_ALL_GENRES = "getAllGenres";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")

	private Long id;
	private String name;

	@ManyToMany(mappedBy = "genres")
	@JsonIgnore
	private Set<Film> films = new HashSet<>();

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

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	@Override
	public int hashCode() {
		return Objects.hash(films, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return Objects.equals(films, other.films) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", films=" + films + "]";
	}

}
