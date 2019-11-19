package ufcg.project.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class Donation {

    private double valueDonated;
    private LocalDate date;
    private Campaign campaign;

    @JsonCreator
    public Donation(double valueDonated, LocalDate date, Campaign campaign) {
        this.valueDonated = valueDonated;
        this.date = date;
        this.campaign = campaign;
    }

    public double getValueDonated() {
        return valueDonated;
    }

    public void setValueDonated(double valueDonated) {
        this.valueDonated = valueDonated;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
