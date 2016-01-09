package gr.etherasTickets.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import java.util.List;

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
    public User createUser(String id, String firstname, String lastname, String email, String username, String password,double balance,List<Reservation> reservations) {
        User user = new User(id,firstname,lastname,email,username,password,balance,reservations);
        operations.insert(user, "users");

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
    
  
}
