package com.mycompany.signslot;

import com.mycompany.signslot.model.User;

// Remembers who is logged in while the app is running.
// Everything is static, so any page can read it without passing it around.
public class Session {

    private static User currentUser;   // Student or Admin object (polymorphism)
    private static int studentDbId;    // students.id of the student (0 for Admin)

    // Prevents creating Session objects; it is only used statically
    private Session() { }

    // Called at login (see SignSlot.openStudent / openAdmin)
    public static void start(User user, int studentDbId) {
        currentUser = user;
        Session.studentDbId = studentDbId;
    }

    public static User getCurrentUser() { return currentUser; }

    // Used by booking, cancelling and My Appointments
    public static int getStudentDbId() { return studentDbId; }

    // True while someone is logged in
    public static boolean isLoggedIn() { return currentUser != null; }

    // Called by SignSlot.showLogin(), so logging out clears everything
    public static void end() {
        currentUser = null;
        studentDbId = 0;
    }
}