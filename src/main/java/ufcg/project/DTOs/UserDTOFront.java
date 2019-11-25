package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import ufcg.project.entities.Donation;

import java.util.List;

public class UserDTOFront {

    private String firstName;
    private String lastName;
    private List<Donation> donations;

    @JsonCreator
    public UserDTOFront(String firstName, String lastName, List<Donation> donations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.donations = donations;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
}
