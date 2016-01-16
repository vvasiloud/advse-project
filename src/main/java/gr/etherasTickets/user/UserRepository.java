package gr.etherasTickets.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

    User findByUsername(String username);
}