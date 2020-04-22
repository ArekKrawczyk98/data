package com.example.data.user;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum UserRole implements Serializable {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }
}
