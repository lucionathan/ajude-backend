package ufcg.project.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ufcg.project.DTOs.CampaignDTO;
import ufcg.project.DTOs.CommentaryDTO;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.Commentary;
import ufcg.project.repositories.CampaignRepository;
import ufcg.project.repositories.CommentaryRepository;
import util.PossibleState;

@Service
public class CampaignService {
	
	@Autowired
	private CampaignRepository campaignRepository;
	@Autowired
	private CommentaryRepository commentaryRepository;

	
	public Campaign addCampaign(CampaignDTO campaing, String owner) {
		String[] data = campaing.getDate().split("/");
        LocalDate expiresAt = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		if(!this.campaignRepository.findByShortName(campaing.getShortName()).isEmpty()) {
			return null;
		}
		
		Campaign c = new Campaign(this.getID(), campaing.getShortName(), campaing.getDescription(), expiresAt, campaing.getShortUrl(), PossibleState.ONGOING, campaing.getGoal(), 0, owner, 0);
		
		this.campaignRepository.save(c);
		return c;
	}
	
	
	public Commentary addCommentary(CommentaryDTO comment) {
		if(this.campaignRepository.findById(comment.getCampaingID()).isPresent()) {
			Commentary c = new Commentary(comment.getText(), comment.getCampaingID(), getID());
			this.commentaryRepository.save(c);
			return c;
		}else {
			return null;
		}
	}
	
	@Scheduled(fixedDelay =20 * 60 * 1000)
	public void updateCampaigns() {
		List<Campaign> campaigns = this.campaignRepository.findAll();
		List<Campaign> toUpdate = new ArrayList<>();
		for(Campaign c: campaigns) {
			if(c.isOver()) {
				c.update();
				toUpdate.add(c);
				
			}
		}
		this.campaignRepository.saveAll(toUpdate);
	}
	
	
	private static final long LIMIT = 10000000000L;
	private static long last = 0;

	public static long getID() {
	 
	  long id = System.currentTimeMillis() % LIMIT;
	  if ( id <= last ) {
	    id = (last + 1) % LIMIT;
	  }
	  return last = id;
	}
}
