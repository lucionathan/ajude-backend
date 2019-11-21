package ufcg.project.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
public class Commentary {
	private String text;
	private String shortUrl;
	@Id
	private long id;
	private Answer answer;
	private String email;
	
	public Commentary(String text, String shortUrl, long id, String email) {
		this.text = text;
		this.shortUrl = shortUrl;
		this.id = id;
		this.email = email;
		this.answer = null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public Boolean setAnswer(Answer answer) {
		if(this.answer == null) {
			this.answer = answer;
			return true;
		} return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
