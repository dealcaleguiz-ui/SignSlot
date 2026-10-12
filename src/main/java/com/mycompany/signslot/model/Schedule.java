package com.mycompany.signslot.model;

public class Schedule {

    // ===== ATTRIBUTES =====
    private int scheduleId;
    private int instructorId;
    private String date;
    private String time;
    private int availableSlots;

    // ===== CONSTRUCTOR =====
    public Schedule(int scheduleId, int instructorId, String date,
                    String time, int availableSlots) {

        this.scheduleId = scheduleId;
        this.instructorId = instructorId;
        this.date = date;
        this.time = time;
        this.availableSlots = availableSlots;
    }

    // ===== GETTERS =====
    public int getScheduleId() {
        return scheduleId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    // ===== SETTERS =====
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    // ===== CHECK AVAILABILITY =====
    public boolean checkAvailability() {
        return availableSlots > 0;
    }

    // ===== RESERVE SLOT =====
    public boolean reserveSlot() {

        if (availableSlots > 0) {
            availableSlots--;
            return true;
        }

        return false;
    }
}