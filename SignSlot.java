package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;
import com.mycompany.signslot.model.Admin;
import com.mycompany.signslot.model.Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignSlot extends Application {

    private static final String DARK_BLUE = "#0D47A1";

    @Override
    public void start(Stage stage) {
        showLogin(stage);
    }

    // ===== SMALL HELPERS =====

    // Creates a label with Arial font (used for all form labels)
    private static Label label(String text, FontWeight weight, int size) {
        Label l = new Label(text);
        l.setFont(Font.font("Arial", weight, size));
        return l;
    }

    // Shows a red error message
    private static void showError(Label message, String text) {
        message.setText(text);
        message.setTextFill(Color.RED);
    }

    // ===== LOGIN PAGE =====
    public void showLogin(Stage stage) {

        // Every time the login page opens (including logout), clear the old session
        Session.end();

        // --- Logo and titles ---
        ImageView logoImage = new ImageView(new Image(
                getClass().getResource("/school-logo.jpg").toExternalForm()));
        logoImage.setFitWidth(110);
        logoImage.setFitHeight(110);
        logoImage.setPreserveRatio(true);

        Label logo = label("SignSlot", FontWeight.BOLD, 38);
        logo.setTextFill(Color.web(DARK_BLUE));

        Label subtitle = label("Clearance Signing Appointment System", FontWeight.NORMAL, 14);
        subtitle.setTextFill(Color.web("#444444"));

        // --- Role selector ---
        Label roleLabel = label("Login as", FontWeight.BOLD, 13);

        ComboBox<String> role = new ComboBox<>();
        role.getItems().addAll("Student", "Admin");
        role.setPromptText("Select Role");
        role.setPrefHeight(42);
        role.setPrefWidth(700);
        role.setStyle(
                "-fx-background-color: rgba(255,255,255,0.90);" +
                "-fx-border-color: #90A4AE;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;");

        // --- Username and password ---
        Label usernameLabel = label("Username", FontWeight.BOLD, 13);
        TextField username = new TextField();
        username.setPromptText("Enter your username");
        username.setPrefHeight(42);
        username.setMaxWidth(Double.MAX_VALUE);

        Label passwordLabel = label("Password", FontWeight.BOLD, 13);
        PasswordField password = new PasswordField();
        password.setPromptText("Enter your password");
        password.setPrefHeight(42);
        password.setMaxWidth(Double.MAX_VALUE);

        // --- Message label (right side of the "Login as" row) ---
        Label message = new Label();
        message.setWrapText(true);
        message.setMaxWidth(Double.MAX_VALUE);
        message.setAlignment(Pos.CENTER_RIGHT);

        HBox roleHeader = new HBox(10, roleLabel, message);
        roleHeader.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(message, Priority.ALWAYS);

        // --- Login button ---
        Button loginButton = new Button("→  LOGIN");
        loginButton.setPrefHeight(44);
        loginButton.setPrefWidth(700);
        loginButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
                "-fx-background-radius: 10;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;");

        // --- Register link ---
        Label dontHave = new Label("Don't have an account?");
        dontHave.setStyle("-fx-font-size: 13px");

        Label registerLink = new Label("Register as a Student here");
        registerLink.setStyle(
                "-fx-text-fill: #1565C0;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-underline: true");

        HBox registerRow = new HBox(5, dontHave, registerLink);
        registerRow.setAlignment(Pos.CENTER);

        // --- Button actions ---
        loginButton.setOnAction(e -> {

            // Validate empty fields first
            if (role.getValue() == null
                    || username.getText().trim().isEmpty()
                    || password.getText().trim().isEmpty()) {
                showError(message, "Please complete all fields.");
                return;
            }

            handleLogin(stage, role.getValue(),
                    username.getText().trim(), password.getText(), message);
        });

        registerLink.setOnMouseClicked(e -> new StudentRegistration().show(stage));

        // --- Layout: form, actions, card ---
        VBox form = new VBox(7, roleHeader, role, usernameLabel,
                username, passwordLabel, password);
        form.setAlignment(Pos.CENTER_LEFT);

        VBox actions = new VBox(10, loginButton, registerRow);
        actions.setAlignment(Pos.CENTER);
        actions.setPrefWidth(700);
        actions.setMaxWidth(700);

        VBox content = new VBox(14, logoImage, logo, subtitle, form, actions);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20, 22, 20, 22));
        content.setPrefWidth(650);
        content.setMaxWidth(650);
        content.setStyle(
                "-fx-background-color: rgba(255,255,255,0.70);" +
                "-fx-background-radius: 20;" +
                "-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.20),25,0.18,0,8);");

        // --- Background image behind the card ---
        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image(
                getClass().getResource("/Picture.jpg").toExternalForm()));
        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());

        StackPane.setMargin(content, new Insets(30, 0, 30, 0));
        root.getChildren().addAll(background, content);

        stage.setTitle("SignSlot - Login");
        stage.setScene(new Scene(root, 900, 600));
        stage.setResizable(true);
        stage.show();
    }

    // ===== LOGIN LOGIC =====
    // Checks the users table, then opens the correct dashboard
    private void handleLogin(Stage stage, String selectedRole,
                             String enteredUsername, String enteredPassword,
                             Label message) {

        String sql = "SELECT id, name FROM users " +
                     "WHERE username = ? AND password = ? AND role = ?";

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);
            statement.setString(3, selectedRole);

            try (ResultSet result = statement.executeQuery()) {

                // No matching account
                if (!result.next()) {
                    showError(message, selectedRole.equals("Student")
                            ? "Student account not found. Please register first."
                            : "Invalid admin username or password.");
                    return;
                }

                int userId = result.getInt("id");
                String name = result.getString("name");

                if (selectedRole.equals("Student")) {
                    openStudent(connection, stage, message, userId, name,
                            enteredUsername, enteredPassword);
                } else {
                    openAdmin(stage, userId, name, enteredUsername, enteredPassword);
                }
            }

        } catch (SQLException ex) {
            showError(message, "Database error.");
            System.out.println("Login error: " + ex.getMessage());
        }
    }

    // Loads the student's profile, starts the session, opens the dashboard
    private void openStudent(Connection connection, Stage stage, Label message,
                             int userId, String name,
                             String username, String password) throws SQLException {

        String sql = "SELECT id, student_id, course, year_level " +
                     "FROM students WHERE user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet sr = ps.executeQuery()) {

                if (!sr.next()) {
                    showError(message, "Student profile not found. " +
                            "Please contact the administrator.");
                    return;
                }

                // Student extends User (inheritance)
                Student student = new Student(userId, name, username, password,
                        sr.getString("student_id"),
                        sr.getString("course"),
                        sr.getInt("year_level"));

                // sr.getInt("id") is students.id, used when booking
                Session.start(student, sr.getInt("id"));

                student.displayDashboard();   // polymorphism
                new StudentDashboard().show(stage, name);
            }
        }
    }

    // Creates the Admin object, starts the session, opens the dashboard
    private void openAdmin(Stage stage, int userId, String name,
                           String username, String password) {

        Admin admin = new Admin(userId, name, username, password,
                "ADM-" + userId, "Administrator");

        Session.start(admin, 0);

        admin.displayDashboard();             // polymorphism
        new AdminDashboard().show(stage);
    }

    // ===== SHARED BUTTON STYLE =====
    public static Button button(String text, String background, Color textColor) {

        Button button = new Button(text);
        button.setPrefHeight(45);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle(
                "-fx-background-color: " + background + ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;");
        button.setTextFill(textColor);
        return button;
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        // Create tables and sample data before the window opens
        Database.createTables();
        Database.insertSampleUsers();
        Database.insertSampleInstructors();
        Database.insertSampleSchedules();

        launch(args);
    }
}