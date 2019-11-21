package ufcg.project.DTOs;

public class DeleteCommentDTO {

	private Long id;
	private String shortUrl;
	private Long father;

	public DeleteCommentDTO(Long id, String shortUrl, Long father) {
		this.id = id;
		this.shortUrl = shortUrl;
		this.father = father;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public Long getFather() {
		return father;
	}

	public void setFather(Long father) {
		this.father = father;
	}
}
