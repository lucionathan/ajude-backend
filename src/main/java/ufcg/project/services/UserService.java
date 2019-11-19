package ufcg.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.project.entities.Donation;
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
        if(this.repository.findByEmail(user.getEmail()).isEmpty()) {
            this.repository.save(user);
            emailService.registrationMail(user.getFirstName(), user.getLastName(), user.getEmail());
            return user;
        }
        return null;
    }

    public void updateUser(User user){
        this.repository.save(user);
    }

    public User updatePassword(String email, String newPassword){
        User u = this.repository.findByEmail(email).get();
        u.setPassword(newPassword);
        this.repository.save(u);
        return this.repository.findByEmail(email).get();
    }

    public Optional<User> getUser(String email) {
        return this.repository.findByEmail(email);
    }

    public Optional<User> getUserByToken(String token){
        return this.repository.findByToken(token);
    }

    public void doDonation(User u, Donation donation) {
        u.addDonation(donation);
        this.repository.save(u);
    }
}
