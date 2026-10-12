package com.mycompany.signslot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL =
            "jdbc:sqlite:signslot.db";


    // =========================
    // CONNECT TO DATABASE
    // =========================

    public static Connection connect()
            throws SQLException {

        return DriverManager.getConnection(URL);
    }


    // =========================
    // CREATE TABLES
    // =========================

    public static void createTables() {

        String usersTable =
                """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                )
                """;


        // NEW STUDENTS TABLE

        String studentsTable =
                """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id TEXT UNIQUE NOT NULL,
                    user_id INTEGER NOT NULL,
                    course TEXT NOT NULL,
                    year_level INTEGER NOT NULL,
                    FOREIGN KEY (user_id)
                    REFERENCES users(id)
                )
                """;


        String instructorsTable =
                """
                CREATE TABLE IF NOT EXISTS instructors (
                    instructor_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    department TEXT NOT NULL
                )
                """;


        String schedulesTable =
                """
                CREATE TABLE IF NOT EXISTS schedules (
                    schedule_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    instructor_id INTEGER NOT NULL,
                    date TEXT NOT NULL,
                    time TEXT NOT NULL,
                    available_slots INTEGER NOT NULL
                )
                """;


        String appointmentsTable =
                """
                CREATE TABLE IF NOT EXISTS appointments (
                    appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id INTEGER NOT NULL,
                    schedule_id INTEGER NOT NULL,
                    status TEXT NOT NULL
                )
                """;


        try (
                Connection connection =
                        connect();

                Statement statement =
                        connection.createStatement()
        ) {

            statement.execute(usersTable);

            statement.execute(studentsTable);

            statement.execute(instructorsTable);

            statement.execute(schedulesTable);

            statement.execute(appointmentsTable);


            System.out.println(
                    "Database tables created successfully."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                    + e.getMessage()
            );
        }
    }


    // =========================
    // SAMPLE ADMIN
    // =========================

    public static void insertSampleUsers() {

        String sql =
                """
                INSERT OR IGNORE INTO users
                (name, username, password, role)
                VALUES
                ('Admin User', 'admin1', 'admin123', 'Admin')
                """;


        try (
                Connection connection =
                        connect();

                Statement statement =
                        connection.createStatement()
        ) {

            statement.executeUpdate(sql);

            System.out.println(
                    "Sample admin added successfully."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                    + e.getMessage()
            );
        }
    }


    // =========================
    // SAMPLE INSTRUCTORS
    // =========================

    public static void insertSampleInstructors() {

        String instructor1 =
                """
                INSERT OR IGNORE INTO instructors
                (instructor_id, name, department)
                VALUES
                (1, 'Mr. Santos', 'Registrar')
                """;


        String instructor2 =
                """
                INSERT OR IGNORE INTO instructors
                (instructor_id, name, department)
                VALUES
                (2, 'Ms. Cruz', 'Student Affairs')
                """;


        try (
                Connection connection =
                        connect();

                Statement statement =
                        connection.createStatement()
        ) {

            statement.executeUpdate(
                    instructor1
            );

            statement.executeUpdate(
                    instructor2
            );

            System.out.println(
                    "Sample instructors added successfully."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                    + e.getMessage()
            );
        }
    }


    // =========================
    // SAMPLE SCHEDULES
    // =========================

    public static void insertSampleSchedules() {

        String schedule1 =
                """
                INSERT OR IGNORE INTO schedules
                (schedule_id, instructor_id, date, time, available_slots)
                VALUES
                (1, 1, '2026-10-07', '9:00 AM', 5)
                """;


        String schedule2 =
                """
                INSERT OR IGNORE INTO schedules
                (schedule_id, instructor_id, date, time, available_slots)
                VALUES
                (2, 1, '2026-10-07', '1:00 PM', 3)
                """;


        String schedule3 =
                """
                INSERT OR IGNORE INTO schedules
                (schedule_id, instructor_id, date, time, available_slots)
                VALUES
                (3, 2, '2026-10-08', '10:00 AM', 4)
                """;


        String schedule4 =
                """
                INSERT OR IGNORE INTO schedules
                (schedule_id, instructor_id, date, time, available_slots)
                VALUES
                (4, 2, '2026-10-08', '2:00 PM', 6)
                """;


        try (
                Connection connection =
                        connect();

                Statement statement =
                        connection.createStatement()
        ) {

            statement.executeUpdate(
                    schedule1
            );

            statement.executeUpdate(
                    schedule2
            );

            statement.executeUpdate(
                    schedule3
            );

            statement.executeUpdate(
                    schedule4
            );

            System.out.println(
                    "Sample schedules added successfully."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                    + e.getMessage()
            );
        }
    }
}