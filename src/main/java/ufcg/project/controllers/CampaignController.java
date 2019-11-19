package ufcg.project.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufcg.project.DTOs.CampaignDTO;
import ufcg.project.DTOs.CommentaryDTO;
import ufcg.project.DTOs.LikeDeslikeDTO;
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
        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        	}else {
        		return new ResponseEntity<Campaign>(c, HttpStatus.OK);
        	}
        }else {
        	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
    
    @PostMapping("/campaign/{idCampanha}/commentary")
    public ResponseEntity<Commentary> addCommentary(@RequestBody CommentaryDTO comment, @RequestHeader("Authorization") String header) throws ServletException {

        if(jwtService.userExists(header)){
        	Commentary c = this.campaignService.addCommentary(comment);
        	if(c == null) {
        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        	}else {
        		return new ResponseEntity<Commentary>(c, HttpStatus.OK);
        	}
        }else {
        	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
    
    @GetMapping("/campaign/substring")
    public ResponseEntity<List<Campaign>> getCampaignsBySubString(@RequestParam(name="substring") String substring,@RequestParam(name="status", required=false, defaultValue="true")boolean status, @RequestHeader("Authorization") String header) throws ServletException{
    	if(jwtService.userExists(header)) {
    		return new ResponseEntity<List<Campaign>>(this.campaignService.getCampaignsBySubstring(substring, status), HttpStatus.OK);    		
    	}else {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    }
    
    @GetMapping("/campaign/{shortUrl}")
    public ResponseEntity<Campaign> getCampaignByShortUrl(@PathVariable("shortUrl") String shortUrl, @RequestHeader("Authorization") String header) throws ServletException{
    	if(this.jwtService.userExists(header)) {
    		Optional<Campaign> c = this.campaignService.getCampaignByShorturl(shortUrl);
    		if(c.isPresent()) {
    			return new ResponseEntity<Campaign>(c.get(), HttpStatus.OK);
    		}else {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    	}else {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    }
    
    @PutMapping("/campaign/{shortUrl}")
    public ResponseEntity<Campaign> updateCampaignByShortUrl(@RequestBody Campaign campaign,@PathVariable("shortUrl") String shortUrl, @RequestHeader("Authorization") String header) throws ServletException{
    	if(this.jwtService.userHasPermission(campaign.getOwner(), header)) {
    		Optional<Campaign> c = this.campaignService.getCampaignByShorturl(shortUrl);
    		if(c.isPresent()) {
    			return new ResponseEntity<Campaign>(this.campaignService.updateCampaign(campaign), HttpStatus.OK);
    		}else {
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    	}else {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    }
    
    @PutMapping("/campaign/{shortUrl}/end")
    public ResponseEntity<Campaign> endCampaignByShortUrl(@PathVariable("shortUrl") String shortUrl, @RequestHeader("Authorization") String header) throws ServletException{
    	Optional<Campaign> c = this.campaignService.getCampaignByShorturl(shortUrl);
    	if(c.isPresent()) {
    		if(this.jwtService.userHasPermission(header, c.get().getOwner())) {
    			Campaign toReturn = this.campaignService.endCampaign(shortUrl);
    			return new ResponseEntity<Campaign>(toReturn, HttpStatus.OK);
    		}else {
    			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    		}
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

    @PutMapping("/campaign/updateLikeDeslike")
	public ResponseEntity<Campaign> updateLikeDeslike(@RequestBody LikeDeslikeDTO dto, @RequestHeader("Authorization") String header) throws ServletException{
    	Optional<Campaign> c = this.campaignService.getCampaignByShorturl(dto.getShortUrl());
    	if(c.isPresent()){
    		if(this.jwtService.userExists(header)){
    			Campaign retorno = this.campaignService.updateLikeDeslike(c.get(), dto.getChoice().trim(), jwtService.getTokenSubject(header));
    			if(retorno != null){
    				return new ResponseEntity<>(this.campaignService.getCampaignByShorturl(dto.getShortUrl()).get(), HttpStatus.OK);
				}else{
    				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @GetMapping("/campaign/user/{owner}")
    public ResponseEntity<List<Campaign>> getCampaignByOwner(@PathVariable String owner, @RequestHeader("Authorization") String header) throws ServletException{
    	if(this.jwtService.userHasPermission(header, owner)){
    		return new ResponseEntity<List<Campaign>>(this.campaignService.getCampaignByOwner(owner), HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    	
    }

}
