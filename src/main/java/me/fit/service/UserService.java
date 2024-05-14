package me.fit.service;

import java.util.HashSet;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.enums.UserStatus;
import me.fit.exception.UserException;
import me.fit.model.Phone;
import me.fit.model.Users;

@Dependent
public class UserService {

	@Inject
	private EntityManager eManager;

	@Transactional
	public Users createUser(Users u) throws UserException {
		List<Users> users = getAllUsers();

		if (users.contains(u)) {
			throw new UserException(UserStatus.EXISTS.getLabel());
		}
		return eManager.merge(u);
	}

	@Transactional
	public List<Users> getAllUsers() {

		List<Users> users = eManager.createNamedQuery(Users.GET_ALL_USERS, Users.class).getResultList();

		for (Users user : users) {
			List<Phone> phones = getAllForUsers(user);
			user.setPhones(new HashSet<>(phones));
		}
		return users;
	}

	@Transactional
	public List<Phone> getAllForUsers(Users u) {
		return eManager.createNamedQuery(Phone.GET_ALL_PHONES_FOR_USER, Phone.class).setParameter("id", u.getId())
				.getResultList();
	}

	@Transactional
	public List<Users> getUsersByName(String name) {
		List<Users> users = eManager.createNamedQuery(Users.GET_USER_BY_NAME, Users.class).setParameter("name", name)
				.getResultList();

		for (Users user : users) {
			List<Phone> phones = getAllForUsers(user);
			user.setPhones(new HashSet<>(phones));
		}

		return users;
	}

	@Transactional
	public void deleteUserById(Long userId) throws UserException{
		Users user = eManager.find(Users.class, userId);
		if(user==null) {
			throw new UserException("User sa tim id-jem " + userId + " nije pronadjen");
		}
		eManager.remove(user);
	}

}
