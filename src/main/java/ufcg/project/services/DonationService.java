package ufcg.project.services;

import org.springframework.stereotype.Service;
import ufcg.project.DTOs.DonationDTO;
import ufcg.project.DTOs.DonationDTOValue;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.Donation;
import ufcg.project.entities.User;

import java.time.LocalDate;
import java.util.Arrays;
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


    public List<Campaign> getCampaignByDonator(String donator) {
        List<Donation> donationsUser = userService.getUser(donator).get().getDonations();
        List<String> campaignsDonation= new LinkedList<>();
        donationsUser.forEach(donation -> campaignsDonation.add(donation.getCampaign()));
        return campaignService.getCampaignsDonated(campaignsDonation);

    }
}
