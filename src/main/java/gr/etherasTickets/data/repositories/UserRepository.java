package gr.etherasTickets.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import gr.etherasTickets.logic.models.User;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {

    User findByUsername(String username);
}