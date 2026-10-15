package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;
import com.mycompany.signslot.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class CancelAppointment {

    private static final String DARK_BLUE = "#0D47A1";

    // ===== SIDEBAR BUTTON STYLES =====
    private static final String BASE =
            "-fx-font-family: Arial;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER;" +
            "-fx-padding: 0 15 0 15;" +
            "-fx-background-radius: 8;";

    // Normal = transparent, Active = gradient (current page), Logout = red text
    private static final String NORMAL =
            "-fx-background-color: transparent;-fx-text-fill: #17324D;" + BASE;
    private static final String ACTIVE =
            "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
            "-fx-text-fill: white;" + BASE;
    private static final String LOGOUT =
            "-fx-background-color: transparent;-fx-text-fill: #D32F2F;" + BASE;

    public void show(Stage stage, String studentName) {

        // ===== SIDEBAR: LOGO AND TITLE =====
        ImageView logo = new ImageView(new Image(
                getClass().getResource("/blue-logo.png").toExternalForm()));
        logo.setFitWidth(90);
        logo.setFitHeight(90);
        logo.setPreserveRatio(true);

        Label signSlot = label("SignSlot", FontWeight.BOLD, 26, "#FFFFFF");
        Label pageText = label("Cancel Appointment", FontWeight.NORMAL, 13, "#FFFFFF");

        VBox logoText = new VBox(2, signSlot, pageText);
        logoText.setAlignment(Pos.CENTER);

        VBox brand = new VBox(12, logo, logoText);
        brand.setAlignment(Pos.CENTER);

        // ===== SIDEBAR: NAVIGATION BUTTONS =====
        Button dashboardButton   = navButton(FontAwesomeIcon.HOME, "Dashboard", NORMAL);
        Button scheduleButton    = navButton(FontAwesomeIcon.CALENDAR, "View Instructor Schedules", NORMAL);
        Button appointmentButton = navButton(FontAwesomeIcon.FOLDER, "My Appointments", NORMAL);
        Button cancelButton      = navButton(FontAwesomeIcon.TIMES_CIRCLE, "Cancel Appointment", ACTIVE);
        Button profileButton     = navButton(FontAwesomeIcon.USER, "Profile", NORMAL);
        Button logoutButton      = navButton(FontAwesomeIcon.SIGN_OUT, "Logout", LOGOUT);

        // Main buttons at the top
        VBox navigation = new VBox(12, dashboardButton, scheduleButton,
                appointmentButton, cancelButton);
        navigation.setAlignment(Pos.TOP_CENTER);
        navigation.setPadding(new Insets(25, 0, 25, 0));
        VBox.setVgrow(navigation, Priority.ALWAYS);   // pushes the rest to the bottom

        // Profile and logout at the bottom
        VBox bottomNavigation = new VBox(10, profileButton, logoutButton);
        bottomNavigation.setAlignment(Pos.CENTER);

        VBox sidebar = new VBox(20, brand, navigation, bottomNavigation);
        sidebar.setAlignment(Pos.TOP_LEFT);
        sidebar.setPadding(new Insets(25, 15, 25, 15));
        sidebar.setPrefWidth(270);
        sidebar.setStyle("-fx-background-color: #4E9CE8;");

        // ===== SIDEBAR: BUTTON ACTIONS =====
        dashboardButton.setOnAction(e -> new StudentDashboard().show(stage, studentName));
        scheduleButton.setOnAction(e -> new ScheduleView().show(stage, studentName));
        appointmentButton.setOnAction(e -> new MyAppointment().show(stage, studentName));
        cancelButton.setOnAction(e -> new CancelAppointment().show(stage, studentName));

        // showLogin() also clears the session
        logoutButton.setOnAction(e -> new SignSlot().showLogin(stage));

        // ===== PAGE TITLE =====
        Label title = label("Cancel Appointment", FontWeight.BOLD, 35, DARK_BLUE);

        // ===== APPOINTMENT TABLE =====
        TableView<Appointment> table = new TableView<>();
        table.setPrefWidth(800);
        table.setMaxWidth(800);
        table.setPrefHeight(430);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #E0E8F5;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(35,65,120,0.10), 12, 0.1, 0, 3);");

        // Shared table look (header gradient, etc.)
        table.getStylesheets().add(
                getClass().getResource("/css/table-style.css").toExternalForm());

        // Column title + the Appointment getter it reads (e.g. "instructorName" -> getInstructorName())
        table.getColumns().addAll(
                column("Instructor", "instructorName"),
                column("Date", "date"),
                column("Time", "time"),
                column("Status", "status"));

        loadAppointments(table);

        // ===== BUTTONS UNDER THE TABLE =====
        Button cancelAppointmentButton = new Button("Cancel Appointment");
        cancelAppointmentButton.setPrefWidth(200);
        cancelAppointmentButton.setPrefHeight(45);
        cancelAppointmentButton.setStyle(
                "-fx-background-color: #D32F2F;" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;");

        Button backButton = new Button("Back");
        backButton.setPrefWidth(120);
        backButton.setPrefHeight(45);
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;");

        // Cancel the selected row (a row must be selected first)
        cancelAppointmentButton.setOnAction(e -> {
            Appointment selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert(Alert.AlertType.WARNING, "No Appointment Selected",
                        "Please select an appointment first.");
                return;
            }
            cancelAppointment(selected, table);
        });

        backButton.setOnAction(e -> new StudentDashboard().show(stage, studentName));

        HBox buttonBox = new HBox(15, cancelAppointmentButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        // ===== CARD HOLDING TABLE + BUTTONS =====
        VBox tableCard = new VBox(20, table, buttonBox);
        tableCard.setAlignment(Pos.CENTER);
        tableCard.setPrefWidth(850);
        tableCard.setMaxWidth(850);
        tableCard.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #FFFFFF, #F4F8FF, #EAF7F3);" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: rgba(78,156,232,0.35);" +
                "-fx-border-width: 1;" +
                "-fx-padding: 25;" +
                "-fx-effect: dropshadow(gaussian, rgba(35,65,120,0.18), 22, 0.15, 0, 6);");

        VBox mainContent = new VBox(25, title, tableCard);
        mainContent.setPadding(new Insets(35, 40, 30, 40));
        mainContent.setAlignment(Pos.TOP_CENTER);

        // ===== BACKGROUND IMAGE =====
        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image(
                getClass().getResource("/Picture.jpg").toExternalForm()));
        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());

        // ===== PAGE LAYOUT: sidebar left, content center =====
        BorderPane appointmentPage = new BorderPane();
        appointmentPage.setLeft(sidebar);
        appointmentPage.setCenter(mainContent);
        appointmentPage.setStyle("-fx-background-color: rgba(245,248,252,0.78);");

        root.getChildren().addAll(background, appointmentPage);

        // ===== SCENE =====
        stage.setTitle("SignSlot - Cancel Appointment");
        stage.setScene(new Scene(root, 1200, 750));
        stage.setResizable(true);
        stage.show();
    }

    // ===== LOAD APPOINTMENTS =====
    // Only Pending/Confirmed ones (true), since only those can be cancelled
    private void loadAppointments(TableView<Appointment> table) {
        try {
            table.setItems(FXCollections.observableArrayList(
                    Database.getStudentAppointments(Session.getStudentDbId(), true)));

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // ===== CANCEL APPOINTMENT =====
    // One transaction: mark the appointment Cancelled -> give the slot back.
    // The status condition stops a double-cancel from adding a phantom slot.
    private void cancelAppointment(Appointment appointment, TableView<Appointment> table) {

        String updateAppointment = """
                UPDATE appointments
                SET status = 'Cancelled'
                WHERE appointment_id = ?
                AND status IN ('Pending', 'Confirmed')
                """;

        String updateSchedule = """
                UPDATE schedules
                SET available_slots = available_slots + 1
                WHERE schedule_id = ?
                """;

        try (Connection connection = Database.connect()) {

            connection.setAutoCommit(false);

            try (PreparedStatement appointmentStatement =
                         connection.prepareStatement(updateAppointment);
                 PreparedStatement scheduleStatement =
                         connection.prepareStatement(updateSchedule)) {

                // 1. Cancel the appointment
                appointmentStatement.setInt(1, appointment.getAppointmentId());
                int changed = appointmentStatement.executeUpdate();

                // 0 rows changed = already cancelled/completed, so do nothing
                if (changed == 0) {
                    connection.rollback();
                    showAlert(Alert.AlertType.INFORMATION, "Cannot Cancel",
                            "This appointment can no longer be cancelled.");
                    return;
                }

                // 2. Give the slot back to the schedule
                scheduleStatement.setInt(1, appointment.getScheduleId());
                scheduleStatement.executeUpdate();

                connection.commit();

                // The cancelled row leaves this page (it stays in My Appointments)
                table.getItems().remove(appointment);

                showAlert(Alert.AlertType.INFORMATION, "Cancellation Successful",
                        "Appointment cancelled successfully!");

            } catch (SQLException e) {
                connection.rollback();   // undo both updates if anything failed
                throw e;
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Cancellation Failed",
                    "Unable to cancel the appointment.");
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // ===== HELPERS =====

    // Popup message
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Centered table column bound to an Appointment property
    private static TableColumn<Appointment, String> column(String title, String property) {
        TableColumn<Appointment, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setStyle("-fx-alignment: CENTER;");
        return column;
    }

    // Colored Arial label
    private static Label label(String text, FontWeight weight, int size, String color) {
        Label l = new Label(text);
        l.setFont(Font.font("Arial", weight, size));
        l.setTextFill(Color.web(color));
        return l;
    }

    // Sidebar button with an icon
    private static Button navButton(FontAwesomeIcon icon, String text, String style) {
        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setGlyphSize(18);

        Button button = new Button(text, iconView);
        button.setPrefHeight(48);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle(style);
        return button;
    }
}