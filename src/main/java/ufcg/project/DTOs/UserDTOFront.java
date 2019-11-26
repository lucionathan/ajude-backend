package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.Donation;

import java.util.List;

public class UserDTOFront {

    private String firstName;
    private String lastName;
    private List<Campaign> campaigns;

    @JsonCreator
    public UserDTOFront(String firstName, String lastName, List<Campaign> campaigns) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.campaigns = campaigns;
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

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}