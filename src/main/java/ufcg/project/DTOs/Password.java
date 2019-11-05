package ufcg.project.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Password {
	private String password;

	@JsonCreator
	public Password(String password){
		this.password = password;
	}

	public String getPassword(){ return this.password; }
}
