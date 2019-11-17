package ufcg.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.User;

@Service
public class DonationService {

    @Autowired
    private UserService userService;
    @Autowired
    private CampaignService campaignService;

    public Campaign doDonation(double donationValue, String tokenUser, String urlCampaign) {
        Campaign campaign = campaignService.getCampaignByShorturl(urlCampaign).get();

        if (campaign == null) {
            return null;
        } else {
            User user = userService.getUserByToken(tokenUser).get();
            userService.doDonation(donationValue, user, campaign);
            return campaignService.doDonation(donationValue, user, campaign);
        }
    }
}
