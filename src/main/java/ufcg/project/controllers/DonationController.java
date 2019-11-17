package ufcg.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufcg.project.DTOs.CommentaryDTO;
import ufcg.project.DTOs.DonationDTOValue;
import ufcg.project.entities.Campaign;
import ufcg.project.services.DonationService;
import ufcg.project.services.JWTService;

import javax.servlet.ServletException;

@Controller
public class DonationController {
    private JWTService jwtService;
    private DonationService donationService;

    @PostMapping("/campaign/{shortUrl}/donation")
    public ResponseEntity<Campaign> doDonation(@RequestBody DonationDTOValue donation, @RequestHeader("Authorization") String header, @PathVariable("shortUrl") String idCampaign) throws ServletException {

        if(jwtService.userExists(header)){
            Campaign campaign = donationService.doDonation(donation.getDonatedValue(), header, idCampaign);
            if(campaign == null) {
                System.out.println("aaa");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                System.out.println("bbb");
                return new ResponseEntity<Campaign>(campaign, HttpStatus.OK);
            }
        }else {
            System.out.println("cccc");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
