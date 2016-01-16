package gr.etherasTickets.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import java.util.ArrayList;

public class UserRepositoryImpl implements CustomUserRepository {

    private final MongoOperations operations; //Class for communicating with mongoDB

    @Autowired
    public UserRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }

    @Override
    public User getUserById(String id) {
         User user = operations.findOne(new Query(Criteria.where("_id").is(id)), User.class, "users");
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
