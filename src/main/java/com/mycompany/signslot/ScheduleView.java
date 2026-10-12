package com.mycompany.signslot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import com.mycompany.signslot.database.Database;
import com.mycompany.signslot.model.Schedule;

public class ScheduleView {

    private static final String BLUE = "#1565C0";
    private static final String DARK_BLUE = "#0D47A1";

    public void show(Stage stage, String studentName) {

        // VIEW INSTRUCTOR SCHEDULES

        Image logoImage = new Image(
                getClass()
                        .getResource("/blue-logo.png")
                        .toExternalForm()
        );

        ImageView logo =
                new ImageView(logoImage);

        logo.setFitWidth(90);
        logo.setFitHeight(90);
        logo.setPreserveRatio(true);

        Label signSlot =
                new Label("SignSlot");

        signSlot.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        26
                )
        );

        signSlot.setTextFill(
                Color.WHITE
        );

        Label pageText =
                new Label("View Instructor Schedules");

        pageText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        pageText.setTextFill(
                Color.WHITE
        );

        VBox logoText =
                new VBox(
                        2,
                        signSlot,
                        pageText
                );

        logoText.setAlignment(
                Pos.CENTER
        );

        VBox brand =
                new VBox(
                        12,
                        logo,
                        logoText
                );

        brand.setAlignment(
                Pos.CENTER
        );

        FontAwesomeIconView dashboardIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.HOME
                );

        dashboardIcon.setGlyphSize(18);

        Button dashboardButton =
                new Button(
                        "Dashboard",
                        dashboardIcon
                );

        FontAwesomeIconView scheduleIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.CALENDAR
                );

        scheduleIcon.setGlyphSize(18);

        Button scheduleButton =
                new Button(
                        "View Instructor Schedules",
                        scheduleIcon
                );

        FontAwesomeIconView appointmentIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.FOLDER
                );

        appointmentIcon.setGlyphSize(18);

        Button appointmentButton =
                new Button(
                        "My Appointments",
                        appointmentIcon
                );

        FontAwesomeIconView cancelIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.TIMES_CIRCLE
                );

        cancelIcon.setGlyphSize(18);

        Button cancelButton =
                new Button(
                        "Cancel Appointment",
                        cancelIcon
                );

        FontAwesomeIconView profileIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.USER
                );

        profileIcon.setGlyphSize(18);

        Button profileButton =
                new Button(
                        "Profile",
                        profileIcon
                );

        FontAwesomeIconView logoutIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.SIGN_OUT
                );

        logoutIcon.setGlyphSize(18);

        Button logoutButton =
                new Button(
                        "Logout",
                        logoutIcon
                );

        dashboardButton.setPrefHeight(48);
        scheduleButton.setPrefHeight(48);
        appointmentButton.setPrefHeight(48);
        cancelButton.setPrefHeight(48);
        profileButton.setPrefHeight(48);
        logoutButton.setPrefHeight(48);

        dashboardButton.setMaxWidth(
                Double.MAX_VALUE
        );

        scheduleButton.setMaxWidth(
                Double.MAX_VALUE
        );

        appointmentButton.setMaxWidth(
                Double.MAX_VALUE
        );

        cancelButton.setMaxWidth(
                Double.MAX_VALUE
        );

        profileButton.setMaxWidth(
                Double.MAX_VALUE
        );

        logoutButton.setMaxWidth(
                Double.MAX_VALUE
        );

        String normalButtonStyle =
                "-fx-background-color: transparent;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #17324D;" +
                "-fx-alignment: CENTER;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-background-radius: 8;";

        String activeButtonStyle =
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8); "+
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-alignment: CENTER;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-background-radius: 8;";

        String logoutStyle =
                "-fx-background-color: transparent;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #D32F2F;" +
                "-fx-alignment: CENTER;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-background-radius: 8;";

        dashboardButton.setStyle(
                normalButtonStyle
        );

        scheduleButton.setStyle(
                activeButtonStyle
        );

        appointmentButton.setStyle(
                normalButtonStyle
        );

        cancelButton.setStyle(
                normalButtonStyle
        );

        profileButton.setStyle(
                normalButtonStyle
        );

        logoutButton.setStyle(
                logoutStyle
        );

        VBox navigation =
                new VBox(
                        12,
                        dashboardButton,
                        scheduleButton,
                        appointmentButton,
                        cancelButton
                );

        navigation.setAlignment(
                Pos.TOP_CENTER
        );

        navigation.setPadding(
                new Insets(
                        25,
                        0,
                        25,
                        0
                )
        );

        VBox bottomNavigation =
                new VBox(
                        10,
                        profileButton,
                        logoutButton
                );

        bottomNavigation.setAlignment(
                Pos.CENTER
        );

        VBox sidebar =
                new VBox(
                        20,
                        brand,
                        navigation,
                        bottomNavigation
                );

        sidebar.setAlignment(
                Pos.TOP_LEFT
        );

        sidebar.setPadding(
                new Insets(
                        25,
                        15,
                        25,
                        15
                )
        );

        VBox.setVgrow(
                navigation,
                Priority.ALWAYS
        );

        sidebar.setPrefWidth(
                270
        );

        sidebar.setStyle(
                 "-fx-background-color: #4E9CE8; "
        );

        dashboardButton.setOnAction(e -> {

            StudentDashboard dashboard =
                    new StudentDashboard();

            dashboard.show(
                    stage,
                    studentName
            );
        });

        scheduleButton.setOnAction(e -> {

            ScheduleView scheduleView =
                    new ScheduleView();

            scheduleView.show(
                    stage,
                    studentName
            );
        });

        appointmentButton.setOnAction(e -> {

            MyAppointment appointment =
                    new MyAppointment();

            appointment.show(
                    stage,
                    studentName
            );
        });

        cancelButton.setOnAction(e -> {

            CancelAppointment cancelAppointment =
                    new CancelAppointment();

            cancelAppointment.show(
                    stage,
                    studentName
            );
        });

        logoutButton.setOnAction(e -> {

            SignSlot login =
                    new SignSlot();

            login.showLogin(stage);
        });

        Label title =
                new Label(
                        "View Instructor Schedules"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        35
                )
        );

        title.setTextFill(
                Color.web(DARK_BLUE)
        );

        TableView<Schedule> table =
        new TableView<>();

            table.setPrefWidth(800);
            table.setMaxWidth(800);
            table.setPrefHeight(430);
            
        table.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #E0E8F5;" +
            "-fx-border-radius: 12;" +
            "-fx-border-width: 1;" +
            "-fx-effect: dropshadow(gaussian, rgba(35,65,120,0.10), 12, 0.1, 0, 3);"
);

            // APPLY TABLE CSS
            table.getStylesheets().add(
                    getClass()
                .getResource("/css/table-style.css")
                .toExternalForm()
            );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );
        TableColumn<Schedule, String> instructorColumn =
                new TableColumn<>("Instructorr");
        
        TableColumn<Schedule, String> departmentColumn =
                new TableColumn<>("Department");
     
        TableColumn<Schedule, String> dateColumn =
                new TableColumn<>("Date");

        TableColumn<Schedule, String> timeColumn =
                new TableColumn<>("Time");

        TableColumn<Schedule, Integer> slotsColumn =
                new TableColumn<>(
                        "Available Slots"
                );
        instructorColumn.setStyle("-fx-alignment: CENTER;");
        departmentColumn.setStyle("-fx-alignment: CENTER;");
        dateColumn.setStyle("-fx-alignment: CENTER;");
        timeColumn.setStyle("-fx-alignment: CENTER;");
        slotsColumn.setStyle("-fx-alignment: CENTER;");
        table.getColumns().addAll(
                instructorColumn,
                departmentColumn,
                dateColumn,
                timeColumn,
                slotsColumn
        );

        VBox tableCard =
                new VBox(
                        20,
                        table
                );

        tableCard.setAlignment(
                Pos.CENTER
        );

        tableCard.setPadding(
                new Insets(25)
        );

        tableCard.setPrefWidth(850);
        tableCard.setMaxWidth(850);

        tableCard.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #FFFFFF, #F4F8FF, #EAF7F3);" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: rgba(78,156,232,0.35);" +
                "-fx-border-width: 1;" +
                "-fx-padding: 25;" +
                "-fx-effect: dropshadow(gaussian, rgba(35,65,120,0.18), 22, 0.15, 0, 6);"
        );

        Button bookButton =
                new Button(
                        "Book Appointment"
                );

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
        "-fx-cursor: hand;"
    );

        Button backButton =
                new Button("Back");

        backButton.setPrefWidth(120);
        backButton.setPrefHeight(45);

        backButton.setStyle(
            "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
            "-fx-background-radius: 10;" +
            "-fx-font-family: Arial;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;" +
            "-fx-cursor: hand;"
        );

        HBox buttonBox =
                new HBox(
                        15,
                        bookButton,
                        backButton
                );

        buttonBox.setAlignment(
                Pos.CENTER
        );

        tableCard.getChildren().add(
                buttonBox
        );

        backButton.setOnAction(e -> {

            StudentDashboard dashboard =
                    new StudentDashboard();

            dashboard.show(
                    stage,
                    studentName
            );
        });

        VBox mainContent =
                new VBox(
                        25,
                        title,
                        tableCard
                );

        mainContent.setPadding(
                new Insets(
                        35,
                        40,
                        30,
                        40
                )
        );

        mainContent.setAlignment(
                Pos.TOP_CENTER
        );

        StackPane root =
                new StackPane();

        Image backgroundImage =
                new Image(
                        getClass()
                                .getResource(
                                        "/Picture.jpg"
                                )
                                .toExternalForm()
                );

        ImageView background =
                new ImageView(
                        backgroundImage
                );

        background.setPreserveRatio(
                false
        );

        background.fitWidthProperty().bind(
                root.widthProperty()
        );

        background.fitHeightProperty().bind(
                root.heightProperty()
        );

        root.getChildren().add(
                background
        );

        BorderPane schedulePage =
                new BorderPane();

        schedulePage.setLeft(
                sidebar
        );

        schedulePage.setCenter(
                mainContent
        );

        schedulePage.setStyle(
                "-fx-background-color: rgba(245,248,252,0.78);"
        );

        root.getChildren().add(
                schedulePage
        );

        Scene scene =
                new Scene(
                        root,
                        1200,
                        750
                );

        stage.setTitle(
                "SignSlot - View Instructor Schedules"
        );

        stage.setScene(
                scene
        );

        stage.setResizable(
                true
        );

        stage.show();
    }
}