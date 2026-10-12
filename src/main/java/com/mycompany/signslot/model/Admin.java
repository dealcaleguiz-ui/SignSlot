package com.mycompany.signslot.model;

public class Admin extends User {

    // ===== ADMIN ATTRIBUTE =====
    private String adminId;
    private String position;

    // ===== CONSTRUCTOR =====
    public Admin(int userId, String name, String username, String password,
                 String adminId, String position) {

        super(userId, name, username, password);

        this.adminId = adminId;
        this.position = position;
    }

    // ===== GETTERS =====
    public String getAdminId() {
        return adminId;
    }

    public String getPosition() {
        return position;
    }

    // ===== SETTERS =====
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // ===== ADMIN ROLE =====
    @Override
    public String getRole() {
        return "Admin";
    }

    // ===== ADMIN DASHBOARD =====
    @Override
    public void displayDashboard() {
        System.out.println("Welcome to the Admin Dashboard!");
    }

    // ===== ADMIN FUNCTIONS =====
    public void manageSchedule() {
        System.out.println("Admin is managing instructor schedules.");
    }

    public void viewAppointments() {
        System.out.println("Admin is viewing appointments.");
    }

    public void updateAppointment() {
        System.out.println("Admin is updating an appointment.");
    }
}