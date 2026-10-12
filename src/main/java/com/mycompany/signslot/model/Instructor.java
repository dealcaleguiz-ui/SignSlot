package com.mycompany.signslot.model;

public class Instructor {

    // ===== ATTRIBUTES =====
    private int instructorId;
    private String name;
    private String department;

    // ===== CONSTRUCTOR =====
    public Instructor(int instructorId, String name, String department) {
        this.instructorId = instructorId;
        this.name = name;
        this.department = department;
    }

    // ===== GETTERS =====
    public int getInstructorId() {
        return instructorId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    // ===== SETTERS =====
    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // ===== DISPLAY INFORMATION =====
    public void displayInfo() {
        System.out.println("Instructor: " + name);
        System.out.println("Department: " + department);
    }

    // ===== CHECK AVAILABILITY =====
    public boolean checkAvailability() {
        return true;
    }
}