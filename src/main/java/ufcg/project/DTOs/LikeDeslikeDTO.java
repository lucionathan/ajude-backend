package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LikeDeslikeDTO {
    String shortUrl;
    String choice;

    @JsonCreator
    public LikeDeslikeDTO(String shortUrl, String choice){
        this.shortUrl = shortUrl;
        this.choice = choice;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
