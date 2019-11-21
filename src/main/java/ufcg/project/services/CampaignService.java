package ufcg.project.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ufcg.project.DTOs.CampaignDTO;
import ufcg.project.DTOs.CommentaryDTO;
import ufcg.project.DTOs.DeleteCommentDTO;
import ufcg.project.DTOs.DonationDTO;
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
		if(this.campaignRepository.findByShortUrl(campaing.getShortUrl()).isPresent()) {
			return null;
		}
		
		Campaign c = new Campaign(this.getID(), campaing.getShortName(), campaing.getDescription(), expiresAt, campaing.getShortUrl(), PossibleState.ONGOING, campaing.getGoal(), 0, owner, 0, 0);
		
		this.campaignRepository.save(c);
		return c;
	}
	
	public List<Campaign> getCampaignsBySubstring(String substring, boolean status){
		if(status) {			
			return this.campaignRepository.findActiveBySubstring(substring);
		}else {
			return this.campaignRepository.findAnyBySubstring(substring);
		}
	}
	
	public Commentary addCommentary(CommentaryDTO comment) {

		Optional<Campaign> ca = this.campaignRepository.findByShortUrl(comment.getShortUrl());
		if(ca.isPresent()) {
			Commentary c = new Commentary(comment.getText(), comment.getShortUrl(), getID(), comment.getEmail(), comment.getFather());
			Campaign caa = ca.get();
			caa.addCommentary(c);
			this.campaignRepository.save(caa);
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

	public Campaign updateLikeDeslike(Campaign campaign, String choice, String email){
		
		if(choice.equals("like")){
			campaign.updateLike(email);
			this.campaignRepository.save(campaign);
			return campaign;
		}else if(choice.equals("deslike")){
			campaign.updateDeslike(email);
			this.campaignRepository.save(campaign);
			return campaign;
		}
		return campaign;
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

	public Optional<Campaign> getCampaignByShorturl(String shortUrl) {
		return this.campaignRepository.findByShortUrl(shortUrl);
	}

	public Campaign updateCampaign(Campaign campaign) {
		return this.campaignRepository.save(campaign);
	}

	public void deleteCampaign(String shortUrl) {
		Optional<Campaign> c = this.campaignRepository.findByShortUrl(shortUrl);
		if(c.isPresent()) {
			this.campaignRepository.deleteById(c.get().getId());
		}
	}
	
	public List<Campaign> getCampaignByOwner(String owner){
		return this.campaignRepository.findByOwner(owner);
	}

    public Campaign doDonation(Campaign campaign, DonationDTO donation) {
		campaign.addDonation(donation);
		this.campaignRepository.save(campaign);
		return campaign;
    }

	public Campaign endCampaign(String shortUrl) {
		Campaign c = this.campaignRepository.findByShortUrl(shortUrl).get();
		c.setStatus(PossibleState.TERMINATED);
		this.campaignRepository.save(c);
		return c;
	}

	public List<Campaign> getActiveCampaigns() {
		return this.campaignRepository.getActive();
	}

	public Commentary addAnswer(CommentaryDTO answer) {
		Campaign c = this.campaignRepository.findByShortUrl(answer.getShortUrl()).get();
		Commentary co = c.getCommentary(answer.getFather());
		if(co != null){
			Commentary a = new Commentary(answer.getText(), answer.getShortUrl(), getID(), answer.getEmail(), answer.getFather());
			co.addAnswer(a);
			c.updateCommentary(co);
			this.campaignRepository.save(c);
			return a;
		}
		return null;
	}

	public Boolean deleteCommentary(DeleteCommentDTO delComment, String emailToken) {
		Campaign c = this.campaignRepository.findByShortUrl(delComment.getShortUrl()).get();
		if(c.getOwner().equals(emailToken)){
			Boolean response = c.deleteCommentary(delComment);
			this.campaignRepository.save(c);
			return response;
		}else{
			return false;
		}
	}
}
