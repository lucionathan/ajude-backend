package ufcg.project.DTOs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class CommentaryDTO {
	private String text;
	private String shortUrl;
	private String email;

	
	public CommentaryDTO(String text, String shortUrl, String email) {
		this.text = text;
		this.shortUrl = shortUrl;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
