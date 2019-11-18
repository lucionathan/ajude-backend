package ufcg.project.services;

import org.springframework.stereotype.Service;
import ufcg.project.DTOs.DonationDTOValue;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.Donation;
import ufcg.project.entities.User;

@Service
public class DonationService {

    private UserService userService;
    private CampaignService campaignService;

    public DonationService(UserService userService, CampaignService campaignService) {
        super();
        this.userService = userService;
        this.campaignService = campaignService;
    }

    public Campaign doDonation(DonationDTOValue valueDonation, String shortUrl, User user) {
        Campaign campaign = campaignService.getCampaignByShorturl(shortUrl).get();
        if(campaign != null) {
            Donation donation = new Donation(valueDonation.getDonatedValue(), campaign);
            userService.doDonation(user, donation);
            return campaign;
        }
        return null;
    }
}
