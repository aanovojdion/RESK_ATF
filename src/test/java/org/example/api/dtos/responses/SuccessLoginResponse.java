package org.example.api.dtos.responses;

public class SuccessLoginResponse {
    private String token;
    private UserLoginResponse user;

    public SuccessLoginResponse() {
    }

    public SuccessLoginResponse(String token, String _id, String firstName, String lastName, String email, Integer __v) {
        this.token = token;
        this.user = new UserLoginResponse(_id, firstName, lastName, email, __v);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserLoginResponse getUser() {
        return user;
    }

    public void setUser(UserLoginResponse user) {
        this.user = user;
    }
}