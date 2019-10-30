package ufcg.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.project.entities.User;
import ufcg.project.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;


    public User addUser(User user) {
        if(this.repository.findById(user.getEmail()) != null) {
            this.repository.save(user);
            emailService.registrationMail(user.getFirstName(), user.getLastName(), user.getEmail());
            return user;
        }
        return null;
    }

    public Optional<User> getUser(String email) {
        return this.repository.findById(email);
    }

}
