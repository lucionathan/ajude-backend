package ufcg.project.DTOs;
import java.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

public class CampaignDTO {
    private String shortName;
    private String description;
    private String date;
    private String shortUrl;
    private double goal;
    private double donated;


    public CampaignDTO(String id, String shortName, String description, String date, 
    		String shortUrl, double goal, double donated){
        this.date = date;
        this.shortName = shortName;
        this.description = description;
        this.shortUrl = shortUrl;
        this.goal = goal;
        this.donated = donated;
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

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
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

	public void setDate(String date) {
		this.date = date;
	}

    public String getDate() {
        return date;
    }

}
