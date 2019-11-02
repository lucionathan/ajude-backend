package ufcg.project.DTOs;

public class UserDTOpass {
	private String email;
	private String password;
	private String newPassword;

	public UserDTOpass(String email, String password, String newPassword) {
		this.email = email;
		this.password = password;
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}
	public String getPassword() { return password; }
	public String getNewPassword() {
		return newPassword;
	}

}
