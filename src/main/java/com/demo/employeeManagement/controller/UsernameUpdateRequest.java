package com.demo.employeeManagement.controller;

public class UsernameUpdateRequest {
   
    private String newUsername;
    private String currentPassword; // Added for verification

    // Getters and Setters
   

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}