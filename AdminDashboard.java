package com.mycompany.signslot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class AdminDashboard {

    private static final String DARK_BLUE = "#0D47A1";

    // ===== SIDEBAR BUTTON STYLES (same as StudentDashboard) =====
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
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;" +
            "-fx-cursor: hand;";

    public void show(Stage stage) {

        // Name of the logged-in admin (set by Session.start at login)
        String adminName = Session.isLoggedIn()
                ? Session.getCurrentUser().getName()
                : "Admin";

        // ===== SIDEBAR: LOGO AND TITLE =====
        ImageView logo = new ImageView(new Image(
                getClass().getResource("/blue-logo.png").toExternalForm()));
        logo.setFitWidth(90);
        logo.setFitHeight(90);
        logo.setPreserveRatio(true);

        Label signSlot = label("SignSlot", FontWeight.BOLD, 26, "#FFFFFF");
        Label dashboardText = label("Admin Dashboard", FontWeight.NORMAL, 13, "#FFFFFF");

        VBox logoText = new VBox(2, signSlot, dashboardText);
        logoText.setAlignment(Pos.CENTER);

        VBox brand = new VBox(12, logo, logoText);
        brand.setAlignment(Pos.CENTER);

        // ===== SIDEBAR: NAVIGATION BUTTONS =====
        Button dashboardButton = navButton(FontAwesomeIcon.HOME, "Dashboard", ACTIVE);
        Button schedulesButton = navButton(FontAwesomeIcon.CALENDAR, "Manage Schedules", NORMAL);
        Button slotsButton     = navButton(FontAwesomeIcon.CLOCK_ALT, "Manage Slots", NORMAL);
        Button viewButton      = navButton(FontAwesomeIcon.LIST, "View Appointments", NORMAL);
        Button statusButton    = navButton(FontAwesomeIcon.EDIT, "Update Status", NORMAL);
        Button profileButton   = navButton(FontAwesomeIcon.USER, "Profile", NORMAL);
        Button logoutButton    = navButton(FontAwesomeIcon.SIGN_OUT, "Logout", LOGOUT);

        // Main buttons at the top
        VBox navigation = new VBox(12, dashboardButton, schedulesButton,
                slotsButton, viewButton, statusButton);
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
        dashboardButton.setOnAction(e -> new AdminDashboard().show(stage));
        schedulesButton.setOnAction(e -> openFeature("Manage Instructor Schedules"));
        slotsButton.setOnAction(e -> openFeature("Manage Appointment Slots"));
        viewButton.setOnAction(e -> openFeature("View Appointments"));
        statusButton.setOnAction(e -> openFeature("Update Appointment Status"));

        // showLogin() also clears the session
        logoutButton.setOnAction(e -> new SignSlot().showLogin(stage));

        // ===== WELCOME SECTION =====
        Label welcome = label("Welcome, " + adminName + "!", FontWeight.BOLD, 45, DARK_BLUE);
        Label welcomeText = label("Manage instructor schedules and student appointments.",
                FontWeight.NORMAL, 18, DARK_BLUE);

        VBox welcomeBox = new VBox(7, welcome, welcomeText);
        welcomeBox.setAlignment(Pos.CENTER_LEFT);

        // ===== QUICK ACTION CARDS =====
        Label quickActions = label("Quick Actions", FontWeight.BOLD, 25, "#172B4D");

        VBox schedulesCard = actionCard(
                FontAwesomeIcon.CALENDAR, "#1565C0",
                "Manage Instructor\nSchedules",
                "Add, edit or remove\ninstructor schedules.",
                "Open  →",
                "#CAE5BC, #E1EDD4, #70D6C5", "#70D6C5",
                () -> openFeature("Manage Instructor Schedules"));

        VBox slotsCard = actionCard(
                FontAwesomeIcon.CLOCK_ALT, "#1B5E20",
                "Manage Appointment\nSlots",
                "Set how many students\neach schedule can take.",
                "Open  →",
                "#E1EDD4, #CAE5BC, #70D6C5", "#CAE5BC",
                () -> openFeature("Manage Appointment Slots"));

        VBox viewCard = actionCard(
                FontAwesomeIcon.LIST, "#1565C0",
                "View\nAppointments",
                "See every student\nwho booked a slot.",
                "Open  →",
                "#E1EDD4, #CAE5BC, #70D6C5", "#CAE5BC", 
                () -> openFeature("View Appointments"));

        VBox statusCard = actionCard(
                FontAwesomeIcon.EDIT, "#C62828",
                "Update Appointment\nStatus",
                "Mark as Pending, Confirmed,\nCompleted or Cancelled.",
                "Open  →",
                "#E1EDD4, #CAE5BC, #70D6C5", "#CAE5BC",
                () -> openFeature("Update Appointment Status"));

        // Four cards share the row equally
        HBox quickCards = new HBox(20, schedulesCard, slotsCard, viewCard, statusCard);
        for (VBox card : new VBox[]{schedulesCard, slotsCard, viewCard, statusCard}) {
            HBox.setHgrow(card, Priority.ALWAYS);
        }

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
        stage.setTitle("SignSlot - Admin Dashboard");
        stage.setScene(new Scene(root, 1200, 750));
        stage.setResizable(true);
        stage.show();
    }

    // ===== OPEN A FEATURE =====
    // Placeholder until each admin screen is built. Later, replace the body
    // with the real screen, e.g. new ManageSchedules().show(stage)
    private void openFeature(String feature) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(feature);
        alert.setHeaderText(null);
        alert.setContentText(feature + " is coming soon.");
        alert.showAndWait();
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
    // Slightly smaller text and spacing than the student cards, because
    // four cards must fit in one row
    private static VBox actionCard(FontAwesomeIcon icon, String color,
                                   String title, String caption, String buttonText,
                                   String gradient, String border, Runnable action) {

        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setGlyphSize(32);
        iconView.setFill(Color.web(color));

        Label titleLabel = label(title, FontWeight.BOLD, 17, color);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);

        Label captionLabel = label(caption, FontWeight.NORMAL, 14, "#172B4D");
        captionLabel.setTextAlignment(TextAlignment.CENTER);
        captionLabel.setAlignment(Pos.CENTER);

        Button button = new Button(buttonText);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(45);
        button.setStyle(ACTION_BUTTON);
        button.setOnAction(e -> action.run());

        VBox card = new VBox(5, iconView, titleLabel, captionLabel, button);
        VBox.setMargin(captionLabel, new Insets(40, 0, 0, 0));
        VBox.setMargin(button, new Insets(50, 0, 0, 0));

        card.setAlignment(Pos.TOP_CENTER);
        card.setPrefHeight(300);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, " + gradient + ");" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-width: 1.2;" +
                "-fx-padding: 20;");
        return card;
    }
}