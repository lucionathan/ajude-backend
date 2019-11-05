package ufcg.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufcg.project.DTOs.CampaignDTO;
import ufcg.project.DTOs.CommentaryDTO;
import ufcg.project.entities.Campaign;
import ufcg.project.entities.Commentary;
import ufcg.project.repositories.CampaignRepository;
import ufcg.project.repositories.CommentaryRepository;

@Service
public class CampaignService {
	
	@Autowired
	private CampaignRepository campaignRepository;
	@Autowired
	private CommentaryRepository commentaryRepository;

	
	public Campaign addCampaign(CampaignDTO campaing, String owner) {
		
		if(!this.campaignRepository.findByShortName(campaing.getShortName()).isEmpty()) {
			return null;
		}
		
		Campaign c = new Campaign(this.getID(), campaing.getShortName(), campaing.getDescription(), campaing.getDate(), campaing.getShortUrl(), true, campaing.getGoal(), 0, owner, 0);
		
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
