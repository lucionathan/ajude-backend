package ufcg.project.services;

import org.springframework.stereotype.Service;
import ufcg.project.DTOs.DonationDTO;
import ufcg.project.DTOs.DonationDTOValue;
import ufcg.project.DTOs.UserDTOFront;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.Donation;
import ufcg.project.entities.User;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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
            LocalDate date = LocalDate.now();
            Donation donationUser = new Donation(valueDonation.getDonatedValue(), date, shortUrl);
            DonationDTO donationCampaign = new DonationDTO(valueDonation.getDonatedValue(), user.getEmail());

            userService.doDonation(user, donationUser);
            campaign = campaignService.doDonation(campaign, donationCampaign);

            return campaign;
        }
        return null;
    }


    public UserDTOFront getCampaignByDonator(String email) {
        User user = userService.getUser(email).get();
        List<String> campaignsDonation = new LinkedList<>();
        user.getDonations().forEach(donation -> campaignsDonation.add(donation.getCampaign()));
        List<Campaign> campaigns = campaignService.getCampaignsUser(campaignsDonation, email);
        return new UserDTOFront(user.getFirstName(), user.getLastName(), campaigns);
    }
}
