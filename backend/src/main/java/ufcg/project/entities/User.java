package ufcg.project.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "User")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String creditCard;
    private String password;

    @JsonCreator
    public User(String firstName, String lastName, String email, String creditCard, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.creditCard = creditCard;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getCreditCard() {
        return this.creditCard;
    }
    
    public boolean setCreditCard(creditCard) {
        this.creditCard = creditCard;
        return true;
    }

    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }
}
