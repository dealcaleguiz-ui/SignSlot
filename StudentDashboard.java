package com.mycompany.signslot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class StudentDashboard {

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

    // Gradient button used inside the quick-action cards
    private static final String ACTION_BUTTON =
            "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
            "-fx-background-radius: 10;" +
            "-fx-font-family: Arial;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;" +
            "-fx-cursor: hand;";

    public void show(Stage stage) {
        show(stage, "Student");
    }

    public void show(Stage stage, String studentName) {

        // ===== SIDEBAR: LOGO AND TITLE =====
        ImageView logo = new ImageView(new Image(
                getClass().getResource("/blue-logo.png").toExternalForm()));
        logo.setFitWidth(90);
        logo.setFitHeight(90);
        logo.setPreserveRatio(true);

        Label signSlot = label("SignSlot", FontWeight.BOLD, 26, "#FFFFFF");
        Label dashboardText = label("Student Dashboard", FontWeight.NORMAL, 13, "#FFFFFF");

        VBox logoText = new VBox(2, signSlot, dashboardText);
        logoText.setAlignment(Pos.CENTER);

        VBox brand = new VBox(12, logo, logoText);
        brand.setAlignment(Pos.CENTER);

        // ===== SIDEBAR: NAVIGATION BUTTONS =====
        Button dashboardButton   = navButton(FontAwesomeIcon.HOME, "Dashboard", ACTIVE);
        Button scheduleButton    = navButton(FontAwesomeIcon.CALENDAR, "View Instructor Schedules", NORMAL);
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
        // Each action opens a new page, so no style resetting is needed here
        dashboardButton.setOnAction(e -> new StudentDashboard().show(stage, studentName));
        scheduleButton.setOnAction(e -> new ScheduleView().show(stage, studentName));
        appointmentButton.setOnAction(e -> new MyAppointment().show(stage, studentName));
        cancelButton.setOnAction(e -> new CancelAppointment().show(stage, studentName));

        // showLogin() also clears the session
        logoutButton.setOnAction(e -> new SignSlot().showLogin(stage));

        // ===== WELCOME SECTION =====
        Label welcome = label("Welcome, " + studentName + "!", FontWeight.BOLD, 45, DARK_BLUE);
        Label welcomeText = label("Manage your clearance appointments with ease.",
                FontWeight.NORMAL, 18, DARK_BLUE);

        VBox welcomeBox = new VBox(7, welcome, welcomeText);
        welcomeBox.setAlignment(Pos.CENTER_LEFT);

        // ===== QUICK ACTION CARDS =====
        Label quickActions = label("Quick Actions", FontWeight.BOLD, 25, "#172B4D");

        VBox scheduleCard = actionCard(
                FontAwesomeIcon.CALENDAR, "#1565C0",
                "View Instructor\nSchedules",
                "Browse and view available\ninstructor schedules.",
                "View Schedules  →",
                "#CAE5BC, #E1EDD4, #70D6C5", "#70D6C5", 250,
                () -> new ScheduleView().show(stage, studentName));

        VBox appointmentCard = actionCard(
                FontAwesomeIcon.FOLDER, "#1B5E20",
                "My\nAppointments",
                "View your booked\nappointments.",
                "Go to My Appointments  →",
                "#E1EDD4, #CAE5BC, #70D6C5", "#CAE5BC", 300,
                () -> new MyAppointment().show(stage, studentName));

        VBox cancelCard = actionCard(
                FontAwesomeIcon.TIMES_CIRCLE, "#C62828",
                "Cancel\nAppointment",
                "Cancel your existing\nappointment.",
                "Cancel Appointment  →",
                "#E1EDD4, #CAE5BC, #70D6C5", "#CAE5BC", 500,
                () -> new CancelAppointment().show(stage, studentName));

        // Three cards share the row equally
        HBox quickCards = new HBox(20, scheduleCard, appointmentCard, cancelCard);
        HBox.setHgrow(scheduleCard, Priority.ALWAYS);
        HBox.setHgrow(appointmentCard, Priority.ALWAYS);
        HBox.setHgrow(cancelCard, Priority.ALWAYS);

        // ===== MAIN CONTENT =====
        VBox mainContent = new VBox(35, welcomeBox, quickActions, quickCards);
        mainContent.setPadding(new Insets(35, 40, 30, 40));
        mainContent.setAlignment(Pos.TOP_LEFT);

        // ===== BACKGROUND IMAGE =====
        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image(
                getClass().getResource("/Picture.jpg").toExternalForm()));
        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());

        // ===== PAGE LAYOUT: sidebar on the left, content in the center =====
        BorderPane dashboard = new BorderPane();
        dashboard.setLeft(sidebar);
        dashboard.setCenter(mainContent);
        dashboard.setStyle("-fx-background-color: rgba(245,248,252,0.78);");

        root.getChildren().addAll(background, dashboard);

        // ===== SCENE =====
        stage.setTitle("SignSlot - Student Dashboard");
        stage.setScene(new Scene(root, 1200, 750));
        stage.setResizable(true);
        stage.show();
    }

    // ===== HELPER: colored Arial label =====
    private static Label label(String text, FontWeight weight, int size, String color) {
        Label l = new Label(text);
        l.setFont(Font.font("Arial", weight, size));
        l.setTextFill(Color.web(color));
        return l;
    }

    // ===== HELPER: sidebar button with an icon =====
    private static Button navButton(FontAwesomeIcon icon, String text, String style) {
        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setGlyphSize(18);

        Button button = new Button(text, iconView);
        button.setPrefHeight(48);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle(style);
        return button;
    }

    // ===== HELPER: quick-action card (icon, title, caption, button) =====
    private static VBox actionCard(FontAwesomeIcon icon, String color,
                                   String title, String caption, String buttonText,
                                   String gradient, String border, double height,
                                   Runnable action) {

        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setGlyphSize(32);
        iconView.setFill(Color.web(color));

        Label titleLabel = label(title, FontWeight.BOLD, 20, color);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);

        Label captionLabel = label(caption, FontWeight.NORMAL, 20, "#172B4D");
        captionLabel.setTextAlignment(TextAlignment.CENTER);
        captionLabel.setAlignment(Pos.CENTER);

        Button button = new Button(buttonText);
        button.setPrefWidth(300);
        button.setPrefHeight(45);
        button.setStyle(ACTION_BUTTON);
        button.setOnAction(e -> action.run());

        VBox card = new VBox(5, iconView, titleLabel, captionLabel, button);
        VBox.setMargin(captionLabel, new Insets(80, 0, 0, 0));
        VBox.setMargin(button, new Insets(100, 0, 0, 0));

        card.setAlignment(Pos.TOP_CENTER);
        card.setPrefHeight(height);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, " + gradient + ");" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-width: 1.2;" +
                "-fx-padding: 25;");
        return card;
    }
}