package com.oth.secuw.dto;

public class LoginDto {
    private String usernameOremail;
    private String password ;

    public String getUsernameOremail() {
        return usernameOremail;
    }

    public void setUsernameOremail(String usernameOremail) {
        this.usernameOremail = usernameOremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
