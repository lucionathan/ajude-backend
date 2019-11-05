package ufcg.project.entities;
import java.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Campaign")
public class Campaign {

	private long id;
    private String shortName;
    private String description;
    private String date;
    private String shortUrl;
    private boolean status;
    private double goal;
    private double donated;
    private String owner;
    private int likes;


    public Campaign(long id, String shortName, String description, String date, String shortUrl,
                    boolean status, double goal, double donated, String owner, int likes){
        this.date = date;
        this.id = id;
        this.shortName = shortName;
        this.description = description;
        this.shortUrl = shortUrl;
        this.status = status;
        this.goal = goal;
        this.donated = donated;
        this.owner=owner;
        this.likes=likes;
    }

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public double getGoal() {
		return goal;
	}
	
	public void setGoal(double goal) {
		this.goal = goal;
	}
	
	public double getDonated() {
		return donated;
	}
	
	public void setDonated(double donated) {
		this.donated = donated;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
}