package ufcg.project.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
//no usuario
@Document(collection = "Donation")
public class Donation {
    private double donatedValue;
//    private Calendar date;
    private Campaign campaign;

    @JsonCreator
    public Donation(double donatedValue, Campaign campaign) {
        this.donatedValue = donatedValue;
//        this.date = date;
        this.campaign = campaign;
    }

    public double getDonatedValue() {
        return donatedValue;
    }

//    public Calendar getDate() {
//        return date;
//    }

    public Campaign getCampaign() {
        return campaign;
    }
}
