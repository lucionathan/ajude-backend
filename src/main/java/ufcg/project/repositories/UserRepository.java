package ufcg.project.repositories;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import ufcg.project.entities.User;

import java.util.Optional;

@Document("User")
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
}
