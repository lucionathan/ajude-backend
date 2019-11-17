package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import ufcg.project.entities.User;

public class DonationDTO {
    private double donatedValue;
    private User donator;

    @JsonCreator
    public DonationDTO(double donatedValue, User donator) {
        this.donatedValue = donatedValue;
        this.donator = donator;
    }

    public double getDonatedValue() {
        return donatedValue;
    }

    public User getDonator() {
        return donator;
    }
}
