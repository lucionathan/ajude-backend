package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import ufcg.project.entities.User;

public class DonationDTO {

    private double donatedValue;
    private String user;

    @JsonCreator
    public DonationDTO(double donatedValue, String user) {
            this.donatedValue = donatedValue;
            this.user = user;
    }

    public double getDonatedValue() {
        return donatedValue;
    }

    public void setDonatedValue(double donatedValue) {
        this.donatedValue = donatedValue;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
