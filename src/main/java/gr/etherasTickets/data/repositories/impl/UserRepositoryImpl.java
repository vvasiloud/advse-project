package gr.etherasTickets.data.repositories.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import gr.etherasTickets.data.repositories.CustomUserRepository;
import gr.etherasTickets.exceptions.NotFound;
import gr.etherasTickets.exceptions.RestException;
import gr.etherasTickets.logic.models.Reservation;
import gr.etherasTickets.logic.models.User;

import java.util.ArrayList;

public class UserRepositoryImpl implements CustomUserRepository {

	private final MongoOperations operations; //Class for communicating with mongoDB

	@Autowired
	public UserRepositoryImpl(MongoOperations operations) {
		Assert.notNull(operations, "MongoOperations must not be null!");
		this.operations = operations;
	}

	@Override
	public User getUserById(String id) throws RestException{
		User user = operations.findOne(new Query(Criteria.where("_id").is(id)), User.class, "users");
		if(user == null)
			throw new NotFound("User with id " + user.getId() + " does not exist!");
		return user;
	}

	@Override
	public User createUser(User user) {
		User newUser = new User(user.getFirstName(),user.getLastName(),user.getEmail(),user.getUsername(),user.getPassword(),0,new ArrayList<Reservation>());
		operations.insert(newUser, "users");

		return user;
	}

	@Override
	public String userLogin(String username, String password) {
		User user = operations.findOne(new Query(Criteria.where("username").is(username).and("password").is(password)), User.class, "users");
		String userId = user.getId();
		return userId;
	}

	@Override
	public void removeUser(String id) {
		operations.findAndRemove(new Query(Criteria.where("_id").is(id)), User.class, "users");

	}

	@Override
	public void updateUser(String id, User newUser) {
		User user = operations.findOne(new Query(Criteria.where("_id").is(id)), User.class, "users");
		user.changeUserData(newUser);
		operations.save(user);	
	}



}
