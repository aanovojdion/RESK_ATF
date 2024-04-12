package org.example.api.dtos.requests;

import java.util.Map;

public class LoginUserData {
    private String email;
    private String password;

    public LoginUserData() {
    }

    public LoginUserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginUserData(Map<String, String> userData) {
        this.email = userData.get("email");
        this.password = userData.get("password");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
