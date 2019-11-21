package ufcg.project.entities;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import ufcg.project.DTOs.DonationDTO;
import util.PossibleState;

@Document(collection = "Campaign")
public class Campaign {
	
	private long id;
    private String shortName;
    private String description;
    private LocalDate date;
    private String shortUrl;
    private PossibleState status;
    private double goal;
    private double donated;
    private HashMap<Long, Commentary> commentaries;
    private String owner;
    private int likes;
    private int deslikes;
	private Set<String> pessoasLike;
	private Set<String> pessoasDeslike;
	private List<DonationDTO> donations;

	public Campaign(){}

	@JsonCreator
    public Campaign(long id, String shortName, String description, LocalDate date, String shortUrl,
                    PossibleState status, double goal, double donated, String owner, int likes, int deslikes){
        this.date = date;
        this.id = id;
        this.shortName = shortName;
        this.description = description;
        this.shortUrl = shortUrl;
        this.status = status;
        this.goal = goal;
        this.donated = donated;
        this.owner = owner;
        this.likes = likes;
        this.deslikes = deslikes;
        this.pessoasDeslike = new HashSet();
		this.pessoasLike = new HashSet();
		this.commentaries = new HashMap<>();
		this.donations = new LinkedList<>();
    }
    
    public boolean isOver() {
    	return this.date.compareTo(LocalDate.now()) < 0;
    }

    public void update() {
    	if(this.isOver()) {
    		if(donated >= goal) {
    			this.status = PossibleState.FINISHED;
    		}else {
    			this.status = PossibleState.EXPIRED;
    		}
    	}
    }

    public void updateLike(String email){
    	
    	if(this.pessoasDeslike.contains(email)) {
    		this.pessoasDeslike.remove(email);
    		this.pessoasLike.add(email);
    		this.deslikes--;
    		this.likes++;
    	}else if(this.pessoasLike.contains(email)) {
    		this.pessoasLike.remove(email);
    		this.likes--;
    	}else {
    		this.pessoasLike.add(email);
    		this.likes++;
    	}
	}

	public Boolean updateDeslike(String email){
		if(!this.pessoasDeslike.contains(email) && !this.pessoasLike.contains(email)){
			this.pessoasDeslike.add(email);
			this.deslikes += 1;
			return true;
		}else if (this.pessoasDeslike.contains(email)){
			this.pessoasDeslike.remove(email);
			this.deslikes -= 1;
			return true;
		}else if(this.pessoasLike.contains(email)){
			this.pessoasLike.remove(email);
			this.pessoasDeslike.add(email);
			this.likes -= 1;
			this.deslikes += 1;
			return true;
		}
		return false;
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
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	public PossibleState getStatus() {
		return status;
	}
	
	public void setStatus(PossibleState status) {
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

	public int getDeslikes() {
		return deslikes;
	}

	public void setDeslikes(int deslikes) {
		this.deslikes = deslikes;
	}

	public Set getPessoasLike() {
		return pessoasLike;
	}

	public void setPessoasLike(Set pessoasLike) {
		this.pessoasLike = pessoasLike;
	}

	public Set getPessoasDeslike() {
		return pessoasDeslike;
	}

	public void setPessoasDeslike(Set pessoasDeslike) {
		this.pessoasDeslike = pessoasDeslike;
	}

	public void addCommentary(Commentary commentary){
		System.out.println(this.commentaries.values());
		this.commentaries.put(commentary.getId(), commentary);
	}

	public Commentary getCommentary(Long id){
		System.out.println(this.commentaries.get(id));
		System.out.println(this.commentaries.keySet());
		System.out.println(this.commentaries.values());
		return this.commentaries.get(id);
	}

    public void addDonation(DonationDTO donation) {
		this.donated += donation.getDonatedValue();
		this.donations.add(donation);
		update();
    }
}