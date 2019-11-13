package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LikeDeslikeDTO {
    String shortURL;
    String choice;
    String email;

    @JsonCreator
    public LikeDeslikeDTO(String shortURL, String choice, String email){
        this.shortURL = shortURL;
        this.choice = choice;
        this.email = email;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
