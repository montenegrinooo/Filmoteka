package me.fit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = Phone.GET_ALL_PHONES_FOR_USER, query = "SELECT p from Phone p WHERE p.user.id = :id") })
public class Phone {

	public static final String GET_ALL_PHONES_FOR_USER = "getAllPhonesForUser";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_seq")
	private Long id;
	private String number;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Users user;

	public Phone() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", user=" + user + "]";
	}

}
