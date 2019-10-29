package ufcg.project.repositories;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import ufcg.project.entities.User;

@Document("/user")
public interface UserRepository extends MongoRepository<User, String> {
    public User findByEmail(String email);
}
