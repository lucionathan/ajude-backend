package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Email {
	private String email;

	@JsonCreator
	public Email(String email){
		this.email = email;
	}

	public String getEmail(){ return this.email; }
}
