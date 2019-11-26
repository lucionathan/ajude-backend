package ufcg.project.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Comment")
public class Commentary {
	private String text;
	private String shortUrl;
	@Id
	private long id;
	private long father;
	private ArrayList<Commentary> answers;
	private String email;
	private Boolean isActive;
	
	public Commentary(String text, String shortUrl, long id, String email, Long father) {
		this.text = text;
		this.shortUrl = shortUrl;
		this.id = id;
		this.email = email;
		this.answers = new ArrayList<>();
		this.isActive = true;
		this.father = father;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setDisable() {
		this.isActive = false;
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

	public ArrayList<Commentary> getAnswer() {
		return answers;
	}

	public void addAnswer(Commentary answer) {
		this.answers.add(answer);
	}

	public long getFather() {
		return father;
	}

	public void setFather(long father) {
		this.father = father;
	}

	public Boolean deleteCommentary(Long id){
		for (Commentary c : this.answers){
			if(c.getId() == id){
				Commentary newComment = c;
				newComment.setDisable();
				this.answers.remove(c);
				this.answers.add(newComment);
				return true;
			}
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
