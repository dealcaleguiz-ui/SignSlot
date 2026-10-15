package com.mycompany.signslot.model;

// Abstraction: User is abstract, so it cannot be created directly.
// It holds what every account has; Student and Admin extend it (inheritance).
public abstract class User {

    // ===== ATTRIBUTES =====
    // Encapsulation: private fields, accessed only through getters/setters
    private int userId;        // users.id
    private String name;       // full name
    private String username;   // login name
    private String password;   // plain text (a real system would hash it)

    // ===== CONSTRUCTOR =====
    // Called by Student and Admin through super(...)
    public User(int userId, String name, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // ===== GETTERS =====
    public int getUserId()      { return userId; }
    public String getName()     { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // ===== SETTERS =====
    public void setUserId(int userId)       { this.userId = userId; }
    public void setName(String name)        { this.name = name; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    // ===== LOGIN =====
    // True if the given credentials match this account
    public boolean login(String username, String password) {
        return this.username.equals(username)
                && this.password.equals(password);
    }

    // ===== LOGOUT =====
    public void logout() {
        System.out.println(name + " has logged out.");
    }

    // ===== POLYMORPHISM =====
    // Each subclass must implement these in its own way

    // "Student" or "Admin"
    public abstract String getRole();

    // Prints a different welcome message depending on the subclass
    public abstract void displayDashboard();
}