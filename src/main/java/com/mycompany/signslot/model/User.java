package com.mycompany.signslot.model;

public abstract class User {

    // ===== ATTRIBUTES =====
    private int userId;
    private String name;
    private String username;
    private String password;

    // ===== CONSTRUCTOR =====
    public User(int userId, String name, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // ===== GETTERS =====
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // ===== SETTERS =====
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ===== LOGIN =====
    public boolean login(String username, String password) {
        return this.username.equals(username)
                && this.password.equals(password);
    }

    // ===== LOGOUT =====
    public void logout() {
        System.out.println(name + " has logged out.");
    }

    // ===== POLYMORPHISM =====
    public abstract String getRole();

    public abstract void displayDashboard();
}