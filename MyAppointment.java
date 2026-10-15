package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;
import com.mycompany.signslot.model.Appointment;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class MyAppointment {

    // ===== SIDEBAR BUTTON STYLE (left-aligned, with hand cursor) =====
    private static final String BASE =
            "-fx-font-family: Arial;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 15 0 15;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;";

    private static final String ACTIVE_BG = "linear-gradient(to right, #4D52B4, #4E9CE8)";
    private static final String HOVER_BG = "rgba(255,255,255,0.22)";

    public void show(Stage stage, String studentName) {

        // ===== SIDEBAR: LOGO AND TITLE =====
        ImageView logo = new ImageView(new Image(
                getClass().getResource("/blue-logo.png").toExternalForm()));
        logo.setFitWidth(90);
        logo.setFitHeight(90);
        logo.setPreserveRatio(true);

        Label signSlot = new Label("SignSlot");
        signSlot.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        signSlot.setTextFill(Color.WHITE);

        Label pageText = new Label("My Appointments");
        pageText.setFont(Font.font("Arial", 13));
        pageText.setTextFill(Color.WHITE);

        VBox logoText = new VBox(2, signSlot, pageText);
        logoText.setAlignment(Pos.CENTER);

        VBox brand = new VBox(12, logo, logoText);
        brand.setAlignment(Pos.CENTER);

        // ===== SIDEBAR: NAVIGATION BUTTONS =====
        Button dashboardButton   = createButton(FontAwesomeIcon.HOME, "Dashboard", false);
        Button scheduleButton    = createButton(FontAwesomeIcon.CALENDAR, "View Instructor Schedules", false);
        Button appointmentButton = createButton(FontAwesomeIcon.FOLDER, "My Appointments", true);   // current page
        Button cancelButton      = createButton(FontAwesomeIcon.TIMES_CIRCLE, "Cancel Appointment", false);
        Button profileButton     = createButton(FontAwesomeIcon.USER, "Profile", false);
        Button logoutButton      = createButton(FontAwesomeIcon.SIGN_OUT, "Logout", false);

        // Main buttons at the top
        VBox navigation = new VBox(10, dashboardButton, scheduleButton,
                appointmentButton, cancelButton);
        VBox.setVgrow(navigation, Priority.ALWAYS);   // pushes the rest to the bottom

        // Profile and logout at the bottom (same as the other pages)
        VBox bottomNavigation = new VBox(10, profileButton, logoutButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_CENTER);

        VBox sidebar = new VBox(10, brand, navigation, bottomNavigation);
        sidebar.setPrefWidth(270);
        sidebar.setPadding(new Insets(25, 15, 20, 15));
        sidebar.setStyle("-fx-background-color: #4E9CE8;");

        // ===== SIDEBAR: BUTTON ACTIONS =====
        dashboardButton.setOnAction(e -> new StudentDashboard().show(stage, studentName));
        scheduleButton.setOnAction(e -> new ScheduleView().show(stage, studentName));
        appointmentButton.setOnAction(e -> new MyAppointment().show(stage, studentName));
        cancelButton.setOnAction(e -> new CancelAppointment().show(stage, studentName));

        // showLogin() also clears the session
        logoutButton.setOnAction(e -> new SignSlot().showLogin(stage));

        // ===== PAGE TITLE =====
        Label title = new Label("My Appointments");
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setStyle(
                "-fx-font-family: Arial;" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0D47A1;");

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

        // Same table look as the schedule page
        table.getStylesheets().add(
                getClass().getResource("/css/table-style.css").toExternalForm());

        // Column title + the Appointment getter it reads (e.g. "instructorName" -> getInstructorName())
        table.getColumns().addAll(
                column("Instructor", "instructorName"),
                column("Date", "date"),
                column("Time", "time"),
                column("Status", "status"));

        loadAppointments(table);

        // ===== BACK BUTTON =====
        Button backButton = new Button("Back");
        backButton.setPrefWidth(120);
        backButton.setPrefHeight(40);
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
                "-fx-background-radius: 10;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;");
        backButton.setOnAction(e -> new StudentDashboard().show(stage, studentName));

        HBox buttons = new HBox(15, backButton);
        buttons.setAlignment(Pos.CENTER);

        // ===== CARD HOLDING TABLE + BUTTON =====
        VBox tableCard = new VBox(20, table, buttons);
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

        // ===== MAIN CONTENT AREA =====
        VBox content = new VBox(20, title, tableCard);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(35));
        content.setStyle("-fx-background-color: rgba(245,248,252,0.78);");

        // ===== BACKGROUND IMAGE =====
        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image(
                getClass().getResource("/Picture.jpg").toExternalForm()));
        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());

        // ===== PAGE LAYOUT: sidebar left, content center =====
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(content);

        root.getChildren().addAll(background, mainLayout);

        // ===== SCENE =====
        stage.setTitle("SignSlot - My Appointments");
        stage.setScene(new Scene(root, 1200, 750));
        stage.setResizable(true);
        stage.show();
    }

    // ===== LOAD APPOINTMENTS =====
    // Shows ALL of the logged-in student's appointments (history included)
    private void loadAppointments(TableView<Appointment> table) {
        try {
            table.setItems(FXCollections.observableArrayList(
                    Database.getStudentAppointments(Session.getStudentDbId())));

        } catch (SQLException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
    }

    // ===== HELPERS =====

    // Centered table column bound to an Appointment property
    private static TableColumn<Appointment, String> column(String title, String property) {
        TableColumn<Appointment, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setStyle("-fx-alignment: CENTER;");
        return column;
    }

    // Sidebar button: active = gradient with white text, inactive = transparent with hover
    private static Button createButton(FontAwesomeIcon icon, String text, boolean active) {

        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setGlyphSize(18);

        Button button = new Button(text, iconView);
        button.setPrefWidth(240);
        button.setPrefHeight(48);
        button.setMaxWidth(Double.MAX_VALUE);

        if (active) {
            button.setStyle("-fx-background-color: " + ACTIVE_BG + ";-fx-text-fill: white;" + BASE);
            iconView.setFill(Color.WHITE);

        } else {
            String normal = "-fx-background-color: transparent;-fx-text-fill: #17324D;" + BASE;
            String hover = "-fx-background-color: " + HOVER_BG + ";-fx-text-fill: #17324D;" + BASE;

            button.setStyle(normal);
            iconView.setFill(Color.web("#17324D"));

            button.setOnMouseEntered(e -> button.setStyle(hover));
            button.setOnMouseExited(e -> button.setStyle(normal));
        }
        return button;
    }
}