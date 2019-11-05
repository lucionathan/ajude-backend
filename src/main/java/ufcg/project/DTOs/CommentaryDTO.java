package ufcg.project.DTOs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class CommentaryDTO {
	private String text;
	private long campaingID;

	
	public CommentaryDTO(String text, long campaingID) {
		this.text = text;
		this.campaingID = campaingID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getCampaingID() {
		return campaingID;
	}

	public void setCampaingID(long campaingID) {
		this.campaingID = campaingID;
	}
	
	
}
