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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import me.fit.model.rest.client.Country;

@Entity
@NamedQueries({ @NamedQuery(name = Actor.GET_ALL_ACTORS, query = "Select a from Actor a") })
public class Actor {

	public static final String GET_ALL_ACTORS = "getAllActors";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_seq")
	private Long id;
	private String name;
	private String lastName;
	private String role;
	@OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Film> films = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	public static String getGetAllActors() {
		return GET_ALL_ACTORS;
	}

	@Override
	public int hashCode() {
		return Objects.hash(films, id, lastName, name, role);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(films, other.films) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(name, other.name)
				&& Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", lastName=" + lastName + ", role=" + role + ", films=" + films
				+ "]";
	}

}
