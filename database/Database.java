package com.mycompany.signslot.database;

import com.mycompany.signslot.model.Appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    // SQLite file, created in the project folder on first run
    private static final String URL = "jdbc:sqlite:signslot.db";

    // ===== CONNECT =====
    // Opens a new connection; callers close it with try-with-resources
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // ===== HELPER =====
    // Runs one or more SQL statements and prints a success/error message
    private static void run(String successMessage, String... statements) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            for (String sql : statements) {
                statement.executeUpdate(sql);
            }
            System.out.println(successMessage);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // ===== CREATE TABLES =====
    public static void createTables() {

        // Login accounts for both Student and Admin
        String users = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                )""";

        // Student details, linked to users.id
        String students = """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id TEXT UNIQUE NOT NULL,
                    user_id INTEGER NOT NULL,
                    course TEXT NOT NULL,
                    year_level INTEGER NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )""";

        // Instructors who sign clearances
        String instructors = """
                CREATE TABLE IF NOT EXISTS instructors (
                    instructor_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    department TEXT NOT NULL
                )""";

        // Available dates/times per instructor
        String schedules = """
                CREATE TABLE IF NOT EXISTS schedules (
                    schedule_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    instructor_id INTEGER NOT NULL,
                    date TEXT NOT NULL,
                    time TEXT NOT NULL,
                    available_slots INTEGER NOT NULL
                )""";

        // Bookings; student_id refers to students.id
        String appointments = """
                CREATE TABLE IF NOT EXISTS appointments (
                    appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id INTEGER NOT NULL,
                    schedule_id INTEGER NOT NULL,
                    status TEXT NOT NULL
                )""";

        run("Database tables created successfully.",
                users, students, instructors, schedules, appointments);
    }

    // ===== SAMPLE ADMIN =====
    public static void insertSampleUsers() {
        run("Sample admin added successfully.",
                """
                INSERT OR IGNORE INTO users (name, username, password, role)
                VALUES ('Admin User', 'admin1', 'admin123', 'Admin')""");
    }

    // ===== SAMPLE INSTRUCTORS =====
    public static void insertSampleInstructors() {
        run("Sample instructors added successfully.",
                "INSERT OR IGNORE INTO instructors (instructor_id, name, department) "
                        + "VALUES (1, 'Mr. Santos', 'Registrar')",
                "INSERT OR IGNORE INTO instructors (instructor_id, name, department) "
                        + "VALUES (2, 'Ms. Cruz', 'Student Affairs')");
    }

    // ===== SAMPLE SCHEDULES =====
    // (id, instructor_id, date, time, available_slots)
    public static void insertSampleSchedules() {
        String insert = "INSERT OR IGNORE INTO schedules "
                + "(schedule_id, instructor_id, date, time, available_slots) VALUES ";

        run("Sample schedules added successfully.",
                insert + "(1, 1, '2026-10-07', '9:00 AM', 5)",
                insert + "(2, 1, '2026-10-07', '1:00 PM', 3)",
                insert + "(3, 2, '2026-10-08', '10:00 AM', 4)",
                insert + "(4, 2, '2026-10-08', '2:00 PM', 6)");
    }

    // ===== GET STUDENT APPOINTMENTS =====
    // All appointments of a student (history included)
    public static List<Appointment> getStudentAppointments(int studentDbId)
            throws SQLException {
        return getStudentAppointments(studentDbId, false);
    }

    // activeOnly = true -> only Pending/Confirmed (used by the Cancel page)
    public static List<Appointment> getStudentAppointments(
            int studentDbId, boolean activeOnly) throws SQLException {

        List<Appointment> list = new ArrayList<>();

        String sql =
                "SELECT a.appointment_id, a.student_id, a.schedule_id, a.status, "
                + "i.name AS instructor_name, sc.date, sc.time "
                + "FROM appointments a "
                + "INNER JOIN schedules sc ON a.schedule_id = sc.schedule_id "
                + "INNER JOIN instructors i ON sc.instructor_id = i.instructor_id "
                + "WHERE a.student_id = ? "
                + (activeOnly ? "AND a.status IN ('Pending','Confirmed') " : "")
                + "ORDER BY sc.date, sc.time";

        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentDbId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Argument order must match the Appointment constructor
                    list.add(new Appointment(
                            rs.getInt("appointment_id"),
                            rs.getInt("student_id"),
                            rs.getInt("schedule_id"),
                            rs.getString("instructor_name"),
                            rs.getString("date"),
                            rs.getString("time"),
                            rs.getString("status")));
                }
            }
        }
        return list;
    }
}