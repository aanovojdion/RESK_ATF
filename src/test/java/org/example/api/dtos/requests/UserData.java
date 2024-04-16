package org.example.api.dtos.requests;

import java.util.Map;

public class UserData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public UserData(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserData(Map<String, String> newUserData) {
        this.firstName = newUserData.get("firstName");
        this.lastName = newUserData.get("lastName");
        this.email = newUserData.get("email");
        this.password = newUserData.get("password");
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
