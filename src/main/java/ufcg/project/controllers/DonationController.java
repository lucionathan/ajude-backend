package ufcg.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import ufcg.project.DTOs.DonationDTOValue;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.User;
import ufcg.project.services.DonationService;
import ufcg.project.services.JWTService;

import javax.servlet.ServletException;

@Controller
public class DonationController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private DonationService donationService;

    @PostMapping("/campaign/{shortUrl}/donation")
    public ResponseEntity<Campaign> doDonation(@RequestBody DonationDTOValue donation, @PathVariable("shortUrl") String shortUrl, @RequestHeader("Authorization") String header) throws ServletException {
        if (this.jwtService.userExists(header)) {
            User user = jwtService.getUserByToken(header);
            Campaign campaign = donationService.doDonation(donation, shortUrl, user);
            if (campaign != null) {
                return new ResponseEntity<Campaign>(campaign, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}