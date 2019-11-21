package ufcg.project.DTOs;

public class AnswerDTO {
	private String text;
	private String email;
	private long commentaryID;
	private String shortUrl;

	public AnswerDTO(String text, String email, long commentaryID, String shortUrl) {
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

	public long getCommentaryID() {
		return commentaryID;
	}

	public void setCommentaryID(long commentaryID) {
		this.commentaryID = commentaryID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
