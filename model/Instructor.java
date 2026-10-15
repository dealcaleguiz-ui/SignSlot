package com.mycompany.signslot.model;

// An instructor who signs student clearances.
// Encapsulation: fields are private and accessed only through getters/setters.
public class Instructor {

    // ===== ATTRIBUTES =====
    private int instructorId;    // instructors.instructor_id
    private String name;         // e.g. Mr. Santos
    private String department;   // e.g. Registrar

    // ===== CONSTRUCTOR =====
    public Instructor(int instructorId, String name, String department) {
        this.instructorId = instructorId;
        this.name = name;
        this.department = department;
    }

    // ===== GETTERS =====
    public int getInstructorId()  { return instructorId; }
    public String getName()       { return name; }
    public String getDepartment() { return department; }

    // ===== SETTERS =====
    public void setInstructorId(int instructorId) { this.instructorId = instructorId; }
    public void setName(String name)              { this.name = name; }
    public void setDepartment(String department)  { this.department = department; }

    // ===== DISPLAY INFORMATION =====
    // Prints the instructor's name and department
    public void displayInfo() {
        System.out.println("Instructor: " + name);
        System.out.println("Department: " + department);
    }

    // ===== CHECK AVAILABILITY =====
    // Placeholder: always available. The real check is whether any of this
    // instructor's schedules still has open slots (see Schedule.checkAvailability()).
    public boolean checkAvailability() {
        return true;
    }
}