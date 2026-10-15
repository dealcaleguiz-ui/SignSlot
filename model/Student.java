package com.mycompany.signslot.model;

// A student account. Inheritance: Student extends the abstract User class,
// so it already has userId, name, username, password, login() and logout().
public class Student extends User {

    // ===== STUDENT ATTRIBUTES =====
    private String studentId;  // school ID number (students.student_id)
    private String course;     // e.g. BSIT
    private int yearLevel;     // 1 to 4

    // ===== CONSTRUCTOR =====
    public Student(int userId, String name, String username, String password,
                   String studentId, String course, int yearLevel) {

        super(userId, name, username, password);   // fields shared with Admin

        this.studentId = studentId;
        this.course = course;
        this.yearLevel = yearLevel;
    }

    // ===== GETTERS =====
    public String getStudentId() { return studentId; }
    public String getCourse()    { return course; }
    public int getYearLevel()    { return yearLevel; }

    // ===== SETTERS =====
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setCourse(String course)       { this.course = course; }
    public void setYearLevel(int yearLevel)    { this.yearLevel = yearLevel; }

    // ===== POLYMORPHISM =====
    // Same method names as Admin, but each class behaves differently

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public void displayDashboard() {
        System.out.println("Welcome to the Student Dashboard!");
    }

    // ===== STUDENT FUNCTIONS =====
    // Placeholders: the real work is done in ScheduleView (book),
    // MyAppointment (view) and CancelAppointment (cancel)

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