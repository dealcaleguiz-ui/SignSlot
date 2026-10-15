package com.mycompany.signslot.model;

// One signing time slot of an instructor (date, time, and how many students fit).
// Encapsulation: fields are private and accessed only through getters/setters.
public class Schedule {

    // ===== ATTRIBUTES =====
    private int scheduleId;        // schedules.schedule_id
    private int instructorId;      // instructors.instructor_id
    private String instructorName; // display only (from the instructors table)
    private String department;     // display only (from the instructors table)
    private String date;           // e.g. 2026-10-20
    private String time;           // e.g. 9:00 AM
    private int availableSlots;    // how many students can still book

    // ===== CONSTRUCTOR =====
    // Argument order must match the call in ScheduleView.loadSchedules()
    public Schedule(int scheduleId, int instructorId, String instructorName,
                    String department, String date, String time,
                    int availableSlots) {
        this.scheduleId = scheduleId;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.department = department;
        this.date = date;
        this.time = time;
        this.availableSlots = availableSlots;
    }

    // ===== GETTERS =====
    // The table columns read these through PropertyValueFactory
    public int getScheduleId()        { return scheduleId; }
    public int getInstructorId()      { return instructorId; }
    public String getInstructorName() { return instructorName; }
    public String getDepartment()     { return department; }
    public String getDate()           { return date; }
    public String getTime()           { return time; }
    public int getAvailableSlots()    { return availableSlots; }

    // ===== SETTERS =====
    public void setScheduleId(int scheduleId)             { this.scheduleId = scheduleId; }
    public void setInstructorId(int instructorId)         { this.instructorId = instructorId; }
    public void setInstructorName(String instructorName)  { this.instructorName = instructorName; }
    public void setDepartment(String department)          { this.department = department; }
    public void setDate(String date)                      { this.date = date; }
    public void setTime(String time)                      { this.time = time; }
    public void setAvailableSlots(int availableSlots)     { this.availableSlots = availableSlots; }

    // ===== CHECK AVAILABILITY =====
    // True while at least one slot is left
    public boolean checkAvailability() {
        return availableSlots > 0;
    }

    // ===== RESERVE SLOT =====
    // Takes one slot; returns false if the schedule is already full
    public boolean reserveSlot() {
        if (availableSlots > 0) {
            availableSlots--;
            return true;
        }
        return false;
    }

    // ===== RELEASE SLOT =====
    // Gives one slot back (used when an appointment is cancelled)
    public void releaseSlot() {
        availableSlots++;
    }
}