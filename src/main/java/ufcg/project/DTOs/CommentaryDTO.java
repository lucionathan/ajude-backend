package ufcg.project.DTOs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class CommentaryDTO {
	private String text;
	private String shortUrl;
	private String email;
	private Long father;

	
	public CommentaryDTO(String text, String shortUrl, String email, Long father) {
		this.text = text;
		this.shortUrl = shortUrl;
		this.email = email;
		this.father = father;
	}

	public Long getFather() {
		return father;
	}

	public void setFather(Long father) {
		this.father = father;
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
