package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;
import com.mycompany.signslot.model.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ScheduleView {

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
        Label pageText = label("View Instructor Schedules", FontWeight.NORMAL, 13, "#FFFFFF");

        VBox logoText = new VBox(2, signSlot, pageText);
        logoText.setAlignment(Pos.CENTER);

        VBox brand = new VBox(12, logo, logoText);
        brand.setAlignment(Pos.CENTER);

        // ===== SIDEBAR: NAVIGATION BUTTONS =====
        Button dashboardButton   = navButton(FontAwesomeIcon.HOME, "Dashboard", NORMAL);
        Button scheduleButton    = navButton(FontAwesomeIcon.CALENDAR, "View Instructor Schedules", ACTIVE);
        Button appointmentButton = navButton(FontAwesomeIcon.FOLDER, "My Appointments", NORMAL);
        Button cancelButton      = navButton(FontAwesomeIcon.TIMES_CIRCLE, "Cancel Appointment", NORMAL);
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
        Label title = label("View Instructor Schedules", FontWeight.BOLD, 35, DARK_BLUE);

        // ===== SCHEDULE TABLE =====
        TableView<Schedule> table = new TableView<>();
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

        // Column title + the Schedule getter it reads (e.g. "instructorName" -> getInstructorName())
        table.getColumns().addAll(
                column("Instructor", "instructorName"),
                column("Department", "department"),
                column("Date", "date"),
                column("Time", "time"),
                column("Available Slots", "availableSlots"));

        loadSchedules(table);

        // ===== BUTTONS UNDER THE TABLE =====
        Button bookButton = new Button("Book Appointment");
        bookButton.setPrefWidth(180);
        bookButton.setPrefHeight(45);
        bookButton.setStyle(
                "-fx-background-color: #E3F2FD;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #4D52B4;" +
                "-fx-border-width: 1.3;" +
                "-fx-border-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4D52B4;" +
                "-fx-cursor: hand;");

        Button backButton = new Button("Back");
        backButton.setPrefWidth(120);
        backButton.setPrefHeight(45);
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
                "-fx-background-radius: 10;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;");

        // Book the selected schedule, then refresh the slot counts
        bookButton.setOnAction(e -> {
            Schedule selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert(Alert.AlertType.WARNING, "No Schedule Selected",
                        "Please select a schedule first.");
                return;
            }
            if (bookAppointment(selected)) {
                loadSchedules(table);
            }
        });

        backButton.setOnAction(e -> new StudentDashboard().show(stage, studentName));

        HBox buttonBox = new HBox(15, bookButton, backButton);
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
        BorderPane schedulePage = new BorderPane();
        schedulePage.setLeft(sidebar);
        schedulePage.setCenter(mainContent);
        schedulePage.setStyle("-fx-background-color: rgba(245,248,252,0.78);");

        root.getChildren().addAll(background, schedulePage);

        // ===== SCENE =====
        stage.setTitle("SignSlot - View Instructor Schedules");
        stage.setScene(new Scene(root, 1200, 750));
        stage.setResizable(true);
        stage.show();
    }

    // ===== LOAD SCHEDULES =====
    // Fills the table with every schedule, joined with its instructor
    private void loadSchedules(TableView<Schedule> table) {

        ObservableList<Schedule> list = FXCollections.observableArrayList();

        String sql = """
                SELECT s.schedule_id, s.instructor_id, s.date, s.time,
                       s.available_slots, i.name, i.department
                FROM schedules s
                INNER JOIN instructors i ON s.instructor_id = i.instructor_id
                ORDER BY s.date, s.time
                """;

        try (Connection c = Database.connect();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Argument order must match the Schedule constructor
                list.add(new Schedule(
                        rs.getInt("schedule_id"),
                        rs.getInt("instructor_id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getInt("available_slots")));
            }
            table.setItems(list);

        } catch (SQLException e) {
            System.out.println("Error loading schedules: " + e.getMessage());
        }
    }

    // ===== BOOK APPOINTMENT =====
    // One transaction: check duplicate -> take a slot -> save appointment.
    // Returns true only if the booking was committed.
    private boolean bookAppointment(Schedule schedule) {

        int studentDbId = Session.getStudentDbId();   // students.id of the logged-in student

        // Block booking the same schedule twice while it is still active
        String checkSql = "SELECT COUNT(*) FROM appointments " +
                "WHERE student_id = ? AND schedule_id = ? " +
                "AND status IN ('Pending','Confirmed')";

        // Only decreases if a slot is left, so slots never go below 0
        String reserveSql = "UPDATE schedules SET available_slots = available_slots - 1 " +
                "WHERE schedule_id = ? AND available_slots > 0";

        String insertSql = "INSERT INTO appointments (student_id, schedule_id, status) " +
                "VALUES (?, ?, 'Pending')";

        try (Connection c = Database.connect()) {

            c.setAutoCommit(false);

            try (PreparedStatement check = c.prepareStatement(checkSql);
                 PreparedStatement reserve = c.prepareStatement(reserveSql);
                 PreparedStatement insert = c.prepareStatement(insertSql)) {

                // 1. Already booked?
                check.setInt(1, studentDbId);
                check.setInt(2, schedule.getScheduleId());
                try (ResultSet rs = check.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        c.rollback();
                        showAlert(Alert.AlertType.WARNING, "Already Booked",
                                "You already booked this schedule.");
                        return false;
                    }
                }

                // 2. Any slot left?
                reserve.setInt(1, schedule.getScheduleId());
                if (reserve.executeUpdate() == 0) {
                    c.rollback();
                    showAlert(Alert.AlertType.WARNING, "Slot Unavailable",
                            "Sorry, this schedule is already full.");
                    return false;
                }

                // 3. Save the appointment as Pending
                insert.setInt(1, studentDbId);
                insert.setInt(2, schedule.getScheduleId());
                insert.executeUpdate();

                c.commit();
                showAlert(Alert.AlertType.INFORMATION, "Booked",
                        "Appointment booked successfully!");
                return true;

            } catch (SQLException ex) {
                c.rollback();   // undo the slot change if anything failed
                throw ex;
            }

        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Booking Failed",
                    "Unable to book the appointment.");
            System.out.println("Database error: " + ex.getMessage());
            return false;
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

    // Centered table column bound to a Schedule property
    private static <T> TableColumn<Schedule, T> column(String title, String property) {
        TableColumn<Schedule, T> column = new TableColumn<>(title);
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