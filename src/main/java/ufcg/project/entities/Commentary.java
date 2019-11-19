package ufcg.project.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
public class Commentary {
	private String text;
	private long campaingID;
	@Id
	private long id;
	
	public Commentary(String text, long campaingID, long id) {
		this.text = text;
		this.campaingID = campaingID;
		this.id = id;
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
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
