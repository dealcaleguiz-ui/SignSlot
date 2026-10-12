package com.mycompany.signslot.model;

public class Student extends User {

    // ===== STUDENT ATTRIBUTES =====
    private String studentId;
    private String course;
    private int yearLevel;

    // ===== CONSTRUCTOR =====
    public Student(int userId, String name, String username, String password,
                   String studentId, String course, int yearLevel) {

        super(userId, name, username, password);

        this.studentId = studentId;
        this.course = course;
        this.yearLevel = yearLevel;
    }

    // ===== GETTERS =====
    public String getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    // ===== SETTERS =====
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    // ===== STUDENT ROLE =====
    @Override
    public String getRole() {
        return "Student";
    }

    // ===== STUDENT DASHBOARD =====
    @Override
    public void displayDashboard() {
        System.out.println("Welcome to the Student Dashboard!");
    }

    // ===== STUDENT FUNCTIONS =====
    public void bookAppointment() {
        System.out.println("Student booked an appointment.");
    }

    public void viewAppointment() {
        System.out.println("Viewing student appointment.");
    }

    public void cancelAppointment() {
        System.out.println("Student cancelled an appointment.");
    }
}