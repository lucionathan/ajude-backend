package ufcg.project.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Donation {

    private double valueDonated;
    private Campaign campaign;

    @JsonCreator
    public Donation(double valueDonated, Campaign campaign) {
        this.valueDonated = valueDonated;
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
