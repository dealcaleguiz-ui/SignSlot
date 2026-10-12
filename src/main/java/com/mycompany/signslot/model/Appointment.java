package com.mycompany.signslot.model;

public class Appointment {

    // ===== ATTRIBUTES =====
    private int appointmentId;
    private int studentId;
    private int scheduleId;
    private String status;

    // ===== CONSTRUCTOR =====
    public Appointment(int appointmentId, int studentId,
                       int scheduleId, String status) {

        this.appointmentId = appointmentId;
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.status = status;
    }

    // ===== GETTERS =====
    public int getAppointmentId() {
        return appointmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public String getStatus() {
        return status;
    }

    // ===== SETTERS =====
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ===== BOOK APPOINTMENT =====
    public void book() {
        status = "Pending";
        System.out.println("Appointment booked successfully.");
    }

    // ===== CANCEL APPOINTMENT =====
    public void cancel() {
        status = "Cancelled";
        System.out.println("Appointment cancelled.");
    }

    // ===== UPDATE STATUS =====
    public void updateStatus(String newStatus) {
        status = newStatus;
        System.out.println("Appointment status updated to: " + status);
    }
}