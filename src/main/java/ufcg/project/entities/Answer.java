package ufcg.project.entities;

import org.springframework.data.annotation.Id;

public class Answer {

	@Id
	private long id;
	private String email;
	private String text;
	private long commentaryID;
	private String shortUrl;

	public Answer(String text, long commentaryID, long id, String email, String shortUrl) {
		this.text = text;
		this.email = email;
		this.commentaryID = commentaryID;
		this.shortUrl = shortUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getCommentaryID() {
		return commentaryID;
	}

	public void setCommentaryID(long commentaryID) {
		this.commentaryID = commentaryID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
