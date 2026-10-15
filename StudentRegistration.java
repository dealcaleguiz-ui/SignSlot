package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
import java.sql.Statement;

public class StudentRegistration {

    private static final String BLUE = "#1565C0";
    private static final String DARK_BLUE = "#0D47A1";

    public void show(Stage stage) {

        // ===== HEADER: LOGO, TITLE, SUBTITLE =====
        Label title = new Label("Student Registration");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setTextFill(Color.web(DARK_BLUE));

        Label subtitle = new Label("Create your SignSlot student account");
        subtitle.setFont(Font.font("Arial", 14));
        subtitle.setTextFill(Color.web("#555555"));

        ImageView logo = new ImageView(new Image(
                getClass().getResource("/registration-logo.png").toExternalForm()));
        logo.setFitWidth(70);
        logo.setFitHeight(70);
        logo.setPreserveRatio(true);

        VBox registrationText = new VBox(15, title, subtitle);
        registrationText.setAlignment(Pos.CENTER_LEFT);

        HBox registrationHeader = new HBox(15, logo, registrationText);
        registrationHeader.setAlignment(Pos.CENTER);

        // ===== MESSAGE LABEL (shown next to "Full Name") =====
        Label message = new Label();
        message.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        message.setTextFill(Color.RED);
        message.setAlignment(Pos.CENTER_RIGHT);

        HBox nameHeader = new HBox(label("Full Name"), message);
        nameHeader.setAlignment(Pos.BASELINE_LEFT);
        HBox.setHgrow(message, Priority.ALWAYS);

        // ===== INPUT FIELDS =====
        TextField name = textField("Enter Full Name");
        TextField studentId = textField("Enter Student ID");
        TextField username = textField("Create Username");

        ComboBox<String> course = comboBox("Select Course",
                "BSIT", "BSHM", "BSTM", "BSED", "BEED");
        ComboBox<String> yearLevel = comboBox("Select Year Level",
                "1", "2", "3", "4");

        PasswordField password = new PasswordField();
        password.setPromptText("Create Password");
        password.setPrefHeight(40);

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Re-enter Password");
        confirmPassword.setPrefHeight(40);

        // ===== BUTTONS =====
        Button registerButton = new Button("✓  CREATE ACCOUNT");
        registerButton.setPrefHeight(47);
        registerButton.setMaxWidth(Double.MAX_VALUE);
        registerButton.setTextFill(Color.WHITE);
        registerButton.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;");

        Button backButton = new Button("‹  BACK TO LOGIN");
        backButton.setPrefHeight(47);
        backButton.setMaxWidth(Double.MAX_VALUE);
        backButton.setTextFill(Color.web(BLUE));
        backButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BLUE + ";" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;");

        // ===== FORM GRID: two equal columns =====
        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(10);
        form.setPrefWidth(700);

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column1.setPercentWidth(50);
        column2.setPercentWidth(50);
        form.getColumnConstraints().addAll(column1, column2);

        // add(node, column, row, columnSpan, rowSpan)
        form.add(nameHeader, 0, 0, 2, 1);
        form.add(name, 0, 1, 2, 1);

        form.add(label("Student ID"), 0, 2);
        form.add(label("Course"), 1, 2);
        form.add(studentId, 0, 3);
        form.add(course, 1, 3);

        form.add(label("Year Level"), 0, 4);
        form.add(label("Username"), 1, 4);
        form.add(yearLevel, 0, 5);
        form.add(username, 1, 5);

        form.add(label("Password"), 0, 6);
        form.add(label("Confirm Password"), 1, 6);
        form.add(password, 0, 7);
        form.add(confirmPassword, 1, 7);

        form.add(registerButton, 0, 8, 2, 1);
        GridPane.setMargin(registerButton, new Insets(10, 0, 0, 0));
        form.add(backButton, 0, 9, 2, 1);

        // ===== REGISTER BUTTON ACTION =====
        registerButton.setOnAction(e -> {

            String enteredStudentId = studentId.getText().trim();
            String enteredName = name.getText().trim();
            String enteredUsername = username.getText().trim();
            String enteredPassword = password.getText();

            // 1. All fields must be filled
            if (enteredStudentId.isEmpty() || enteredName.isEmpty()
                    || course.getValue() == null || yearLevel.getValue() == null
                    || enteredUsername.isEmpty() || enteredPassword.isEmpty()
                    || confirmPassword.getText().isEmpty()) {
                showError(message, "Please complete all fields.");
                return;
            }

            // 2. Both passwords must be the same
            if (!enteredPassword.equals(confirmPassword.getText())) {
                showError(message, "Passwords do not match.");
                return;
            }

            // 3. Minimum password length
            if (enteredPassword.length() < 6) {
                showError(message, "Password must be at least 6 characters.");
                return;
            }

            // 4. Save to the database; clear the form only if it worked
            if (register(message, enteredName, enteredUsername, enteredPassword,
                    enteredStudentId, course.getValue(),
                    Integer.parseInt(yearLevel.getValue()))) {

                message.setText("Registration successful!");
                message.setTextFill(Color.GREEN);

                name.clear();
                studentId.clear();
                username.clear();
                password.clear();
                confirmPassword.clear();
                course.setValue(null);
                yearLevel.setValue(null);
            }
        });

        // Back to the login page
        backButton.setOnAction(e -> new SignSlot().showLogin(stage));

        // ===== CARD CONTAINING HEADER + FORM =====
        VBox content = new VBox(14, registrationHeader, form);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20, 22, 20, 22));
        content.setPrefWidth(850);
        content.setMaxWidth(850);
        content.setStyle(
                "-fx-background-color: rgba(255,255,255,0.70);" +
                "-fx-background-radius: 20;" +
                "-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.20),25,0.18,0,8);");

        // ===== BACKGROUND IMAGE =====
        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image(
                getClass().getResource("/Picture.jpg").toExternalForm()));
        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());

        StackPane.setMargin(content, new Insets(50, 0, 50, 0));  // top, right, bottom, left
        root.getChildren().addAll(background, content);

        stage.setTitle("SignSlot - Student Registration");
        stage.setScene(new Scene(root, 900, 700));
        stage.setResizable(true);
        stage.show();
    }

    // ===== SAVE ACCOUNT =====
    // Inserts into users, then students, in ONE transaction.
    // Returns true only if both inserts were committed.
    private boolean register(Label message, String name, String username,
                             String password, String studentId,
                             String course, int year) {

        String userSql = "INSERT INTO users (name, username, password, role) " +
                         "VALUES (?, ?, ?, 'Student')";
        String studentSql = "INSERT INTO students (student_id, user_id, course, year_level) " +
                            "VALUES (?, ?, ?, ?)";

        try (Connection connection = Database.connect();
             PreparedStatement userStatement = connection.prepareStatement(
                     userSql, Statement.RETURN_GENERATED_KEYS)) {

            // Nothing is saved until commit(); an error discards both inserts
            connection.setAutoCommit(false);

            userStatement.setString(1, name);
            userStatement.setString(2, username);
            userStatement.setString(3, password);
            userStatement.executeUpdate();

            // Get the new users.id to link the student row
            int userId;
            try (ResultSet keys = userStatement.getGeneratedKeys()) {
                if (!keys.next()) {
                    showError(message, "Registration failed.");
                    return false;
                }
                userId = keys.getInt(1);
            }

            try (PreparedStatement studentStatement =
                         connection.prepareStatement(studentSql)) {
                studentStatement.setString(1, studentId);
                studentStatement.setInt(2, userId);
                studentStatement.setString(3, course);
                studentStatement.setInt(4, year);
                studentStatement.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException ex) {

            // SQLite reports duplicates as "UNIQUE constraint failed"
            String msg = ex.getMessage() == null ? "" : ex.getMessage().toLowerCase();

            showError(message, msg.contains("unique")
                    ? "Student ID or username already exists."
                    : "Registration failed.");

            System.out.println("Registration error: " + ex.getMessage());
            return false;
        }
    }

    // ===== HELPERS =====

    // Bold 13px Arial label for form fields
    private static Label label(String text) {
        Label l = new Label(text);
        l.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        return l;
    }

    // Standard text field
    private static TextField textField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefHeight(40);
        return field;
    }

    // Standard drop-down filled with the given options
    private static ComboBox<String> comboBox(String prompt, String... items) {
        ComboBox<String> box = new ComboBox<>();
        box.getItems().addAll(items);
        box.setPromptText(prompt);
        box.setPrefHeight(40);
        box.setMaxWidth(Double.MAX_VALUE);
        return box;
    }

    // Red error message
    private static void showError(Label message, String text) {
        message.setText(text);
        message.setTextFill(Color.RED);
    }
}