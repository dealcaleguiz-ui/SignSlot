package com.mycompany.signslot.model;

// One booking made by a student for a schedule.
// Encapsulation: fields are private and accessed only through getters/setters.
public class Appointment {

    // ===== ATTRIBUTES =====
    private int appointmentId;     // appointments.appointment_id
    private int studentId;         // students.id of the student who booked
    private int scheduleId;        // schedules.schedule_id that was booked
    private String instructorName; // display only (from the instructors table)
    private String date;           // display only (from the schedules table)
    private String time;           // display only (from the schedules table)
    private String status;         // Pending, Confirmed, Completed or Cancelled

    // ===== CONSTRUCTOR =====
    // Argument order must match Database.getStudentAppointments(...)
    public Appointment(int appointmentId, int studentId, int scheduleId,
                       String instructorName, String date, String time,
                       String status) {
        this.appointmentId = appointmentId;
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.instructorName = instructorName;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    // ===== GETTERS =====
    // The table columns read these through PropertyValueFactory
    public int getAppointmentId()    { return appointmentId; }
    public int getStudentId()        { return studentId; }
    public int getScheduleId()       { return scheduleId; }
    public String getInstructorName() { return instructorName; }
    public String getDate()          { return date; }
    public String getTime()          { return time; }
    public String getStatus()        { return status; }

    // ===== SETTERS =====
    public void setAppointmentId(int appointmentId)       { this.appointmentId = appointmentId; }
    public void setStudentId(int studentId)               { this.studentId = studentId; }
    public void setScheduleId(int scheduleId)             { this.scheduleId = scheduleId; }
    public void setInstructorName(String instructorName)  { this.instructorName = instructorName; }
    public void setDate(String date)                      { this.date = date; }
    public void setTime(String time)                      { this.time = time; }
    public void setStatus(String status)                  { this.status = status; }

    // ===== BOOK =====
    // A new booking always starts as Pending
    public void book() {
        status = "Pending";
        System.out.println("Appointment booked successfully.");
    }

    // ===== CANCEL =====
    public void cancel() {
        status = "Cancelled";
        System.out.println("Appointment cancelled.");
    }

    // ===== UPDATE STATUS =====
    // Used by the Admin: Pending, Confirmed, Completed or Cancelled
    public void updateStatus(String newStatus) {
        status = newStatus;
        System.out.println("Appointment status updated to: " + status);
    }
}