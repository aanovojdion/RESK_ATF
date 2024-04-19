package org.example.configurations.api_configs;

public enum EndPoints {
    CREATE_USER("/users"),
    LOGIN_USER("/users/login"),
    DELETE_USER("/users/me");

    private final String value;

    EndPoints(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
