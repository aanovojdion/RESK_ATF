package org.example.api.dtos.responses;

public class SuccessNewUserCreate {
    private String token;
    private CreateUserResponse user;

    public SuccessNewUserCreate() {
    }

    public SuccessNewUserCreate(String _id, String firstName, String lastName, String email, Integer __v) {
        this.user = new CreateUserResponse(_id, firstName, lastName, email, __v);
    }

    public SuccessNewUserCreate(String token, CreateUserResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CreateUserResponse getUser() {
        return user;
    }

    public void setUser(CreateUserResponse user) {
        this.user = user;
    }
}