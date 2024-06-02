package me.fit.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@NamedQueries({ @NamedQuery(name = Users.GET_ALL_USERS, query = "Select u from Users u"),
		@NamedQuery(name = Users.GET_USER_BY_NAME, query = "Select u from Users u where u.name = :name") })
public class Users {

	public static final String GET_ALL_USERS = "getAllUsers";
	public static final String GET_USER_BY_NAME = "getUserByName";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")

	private Long id;
	private String name;
	private String lastName;
	private String jmbg;
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<Phone> phones;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Set<LoanFilms> loanFilms;
	
	@OneToOne(cascade= {CascadeType.ALL})
	@JsonIgnore
	private IpLog ipLog;
	

	public IpLog getIpLog() {
		return ipLog;
	}

	public void setIpLog(IpLog ipLog) {
		this.ipLog = ipLog;
	}

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

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Set<LoanFilms> getLoanFilms() {
		return loanFilms;
	}

	public void setLoanFilms(Set<LoanFilms> loanFilms) {
		this.loanFilms = loanFilms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [idUser=" + id + ", name=" + name + ", lastName=" + lastName + ", jmbg=" + jmbg + ", email="
				+ email + ", phones=" + phones + ", loanFilms=" + loanFilms + "]";
	}

}