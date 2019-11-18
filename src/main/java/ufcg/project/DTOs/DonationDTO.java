package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import ufcg.project.entities.User;

public class DonationDTO {

    private double donatedValue;
    private User user;

    @JsonCreator
    public DonationDTO(double donatedValue, User user) {
            this.donatedValue = donatedValue;
            this.user = user;
    }

    public double getDonatedValue() {
        return donatedValue;
    }

    public void setDonatedValue(double donatedValue) {
        this.donatedValue = donatedValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
