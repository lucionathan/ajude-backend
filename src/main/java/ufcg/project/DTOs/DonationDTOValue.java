package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DonationDTOValue {
    private double donatedValue;

    @JsonCreator
    public DonationDTOValue(double donatedValue) {
        this.donatedValue = donatedValue;
    }

    public double getDonatedValue() {
        return donatedValue;
    }
}
