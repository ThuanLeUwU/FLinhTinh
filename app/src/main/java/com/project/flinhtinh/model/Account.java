package com.project.flinhtinh.model;

import java.util.UUID;

public class Account {
    private UUID accountId;
    private String email;
    private String password;
    private String role;

    public Account(UUID accountId, String email, String password, String role) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setId(UUID accountId) {
        this.accountId = accountId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
