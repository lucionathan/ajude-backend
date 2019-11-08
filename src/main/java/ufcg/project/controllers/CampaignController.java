package ufcg.project.controllers;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufcg.project.DTOs.CampaignDTO;
import ufcg.project.DTOs.CommentaryDTO;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.Commentary;
import ufcg.project.services.CampaignService;
import ufcg.project.services.JWTService;

@CrossOrigin
@RestController
public class CampaignController {


    private JWTService jwtService;
    private CampaignService campaignService;
    public CampaignController(JWTService jwtService, CampaignService campaignService) {
        super();
        this.jwtService = jwtService;
        this.campaignService = campaignService;
    }

    @PostMapping("/campaign")
    public ResponseEntity<Campaign> addCampaign(@RequestBody CampaignDTO campaign, @RequestHeader("Authorization") String header) throws ServletException {

        if(jwtService.userExists(header)){
        	Campaign c = this.campaignService.addCampaign(campaign, jwtService.getTokenSubject(header));
        	if(c == null) {
        		return new ResponseEntity(HttpStatus.BAD_REQUEST);
        	}else {
        		return new ResponseEntity<Campaign>(c, HttpStatus.OK);
        	}
        }else {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

    }
    
    @PostMapping("/campaign/{idCampanha}/commentary")
    public ResponseEntity<Commentary> addCommentary(@RequestBody CommentaryDTO comment, @RequestHeader("Authorization") String header) throws ServletException {

        if(jwtService.userExists(header)){
        	Commentary c = this.campaignService.addCommentary(comment);
        	if(c == null) {
        		return new ResponseEntity(HttpStatus.BAD_REQUEST);
        	}else {
        		return new ResponseEntity<Commentary>(c, HttpStatus.OK);
        	}
        }else {
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

    }
    
    @GetMapping("/campaign/substring")
    public ResponseEntity<List<Campaign>> getCampaignsBySubString(@RequestParam(name="substring") String substring,@RequestParam(name="status")boolean status ){
		
    	return new ResponseEntity<List<Campaign>>(this.campaignService.getCampaignsBySubstring(substring, status), HttpStatus.OK);
    }
   
}
