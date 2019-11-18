package ufcg.project.services;

import org.springframework.stereotype.Service;
import ufcg.project.DTOs.DonationDTO;
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
        if (campaign != null) {
            Donation donationUser = new Donation(valueDonation.getDonatedValue(), campaign);
            DonationDTO donationCampaign = new DonationDTO(valueDonation.getDonatedValue(), user);

            userService.doDonation(user, donationUser);
            campaign = campaignService.doDonation(campaign, donationCampaign);

            return campaign;
        }
        return null;
    }
}
