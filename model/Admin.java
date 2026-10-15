package com.mycompany.signslot.model;

// An administrator account. Inheritance: Admin extends the abstract User class,
// so it already has userId, name, username, password, login() and logout().
public class Admin extends User {

    // ===== ADMIN ATTRIBUTES =====
    private String adminId;    // e.g. ADM-1 (created at login, not stored in the database)
    private String position;   // e.g. Administrator

    // ===== CONSTRUCTOR =====
    public Admin(int userId, String name, String username, String password,
                 String adminId, String position) {

        super(userId, name, username, password);   // fields shared with Student

        this.adminId = adminId;
        this.position = position;
    }

    // ===== GETTERS =====
    public String getAdminId()  { return adminId; }
    public String getPosition() { return position; }

    // ===== SETTERS =====
    public void setAdminId(String adminId)    { this.adminId = adminId; }
    public void setPosition(String position)  { this.position = position; }

    // ===== POLYMORPHISM =====
    // Same method names as Student, but each class behaves differently

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void displayDashboard() {
        System.out.println("Welcome to the Admin Dashboard!");
    }

    // ===== ADMIN FUNCTIONS =====
    // Placeholders: the real work will be done in the admin screens
    // (manage schedules, view appointments, update status)

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