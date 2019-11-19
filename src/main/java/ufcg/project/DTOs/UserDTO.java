package ufcg.project.DTOs;

public class UserDTO {

    private String email;
    private String password;
    private Boolean savePassword;

    public UserDTO(String email, String password, Boolean savePassword) {
        this.email = email;
        this.password = password;
        this.savePassword = savePassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getSavePassword(){
        return this.savePassword;
    }
}
