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
import javafx.stage.Stage;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class StudentDashboard {

    private static final String BLUE = "#1565C0";
    private static final String DARK_BLUE = "#0D47A1";

    public void show(Stage stage) {
        show(stage, "Student");
    }

    public void show(Stage stage, String studentName) {

        // LOGO
        Image logoImage = new Image(
                getClass()
                        .getResource("/blue-logo.png")
                        .toExternalForm()
        );

        ImageView logo = new ImageView(logoImage);

        logo.setFitWidth(90);
        logo.setFitHeight(90);
        logo.setPreserveRatio(true);

        // HEADER
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

        Label dashboardText =
                new Label("Student Dashboard");

        dashboardText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        dashboardText.setTextFill(
                Color.WHITE
        );

        VBox logoText =
                new VBox(
                        2,
                        signSlot,
                        dashboardText
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

        // NAVIGATION

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

        // BUTTON SIZE

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

        // NAVIGATION STYLE

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
                activeButtonStyle
        );

        scheduleButton.setStyle(
                normalButtonStyle
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

        // SIDEBAR

        VBox navigation =
                new VBox(
                        12,
                        dashboardButton
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

        // DASHBOARD BUTTON

        dashboardButton.setOnAction(e -> {

            navigation.getChildren().setAll(
                    dashboardButton,
                    scheduleButton,
                    appointmentButton,
                    cancelButton
            );

            bottomNavigation.getChildren().setAll(
                    profileButton,
                    logoutButton
            );

            dashboardButton.setStyle(
                    activeButtonStyle
            );

            scheduleButton.setStyle(
                    normalButtonStyle
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
        });

        // NAVIGATION ACTIONS

        scheduleButton.setOnAction(e -> {

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

        ScheduleView scheduleView =
            new ScheduleView();

        scheduleView.show(stage, studentName);
    });

    appointmentButton.setOnAction(e -> {

        dashboardButton.setStyle(
            normalButtonStyle
        );

        scheduleButton.setStyle(
            normalButtonStyle
        );

        appointmentButton.setStyle(
            activeButtonStyle
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

        MyAppointment appointment =
            new MyAppointment();

        appointment.show(stage, studentName);
    });

    cancelButton.setOnAction(e -> {

        dashboardButton.setStyle(
            normalButtonStyle
        );

        scheduleButton.setStyle(
            normalButtonStyle
        );

        appointmentButton.setStyle(
            normalButtonStyle
        );

        cancelButton.setStyle(
            activeButtonStyle
         );

        profileButton.setStyle(
            normalButtonStyle
        );

        logoutButton.setStyle(
            logoutStyle
        );

         CancelAppointment cancelAppointment =
            new CancelAppointment();

            cancelAppointment.show(stage, studentName);
    });

    logoutButton.setOnAction(e -> {

        SignSlot login =
            new SignSlot();

        login.showLogin(stage);
    });


        // WELCOME

        Label welcome =
                new Label(
                        "Welcome, " +
                        studentName +
                        "!"
                );

        welcome.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        45
                )
        );

        welcome.setTextFill(
                Color.web("#0D47A1")
        );

        Label welcomeText =
                new Label(
                        "Manage your clearance appointments with ease. " 
                 );

        welcomeText.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );

        welcomeText.setTextFill(
                Color.web("#0D47A1")
        );

        VBox welcomeBox =
                new VBox(
                        7,
                        welcome,
                        welcomeText
                );

        welcomeBox.setAlignment(
                Pos.CENTER_LEFT
        );

// QUICK ACTIONS

Label quickActions =
        new Label(
                "Quick Actions"
        );

quickActions.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                25
        )
);

quickActions.setTextFill(
        Color.web("#172B4D")
);

// VIEW INSTRUCTOR SCHEDULES

FontAwesomeIconView scheduleCardIcon =
        new FontAwesomeIconView(
                FontAwesomeIcon.CALENDAR
        );

scheduleCardIcon.setGlyphSize(32);

scheduleCardIcon.setFill(
        Color.web("#1565C0")
);
//+ View Sched. Title
Label scheduleTitle =
        new Label(
                "View Instructor\n" +
                "   Schedules"
        );

scheduleTitle.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                20
        )
);

scheduleTitle.setTextFill(
        Color.web("#1565C0")
);

scheduleTitle.setAlignment(
        Pos.TOP_CENTER
);
// + View Sced. Caption
Label scheduleCaption =
        new Label(
                "Browse and view available\n" +
                "   instructor schedules."
        );

scheduleCaption.setFont(
        Font.font(
                "Arial",
                 20
        )
);

scheduleCaption.setTextFill(
        Color.web("#172B4D")
);

scheduleCaption.setAlignment(
        Pos.CENTER
);
// +View Sched. Button
Button scheduleAction =
        new Button(
                "View Schedules  →"
        );

scheduleAction.setPrefWidth(
        300
);

scheduleAction.setPrefHeight(
        45
);

scheduleAction.setStyle(
        "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
        "-fx-background-radius: 10;" +
        "-fx-font-family: Arial;" +
        "-fx-font-size: 15px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;" +
        "-fx-cursor: hand;"
     );

scheduleAction.setOnAction(e -> {

    ScheduleView scheduleView =
            new ScheduleView();

    scheduleView.show(stage, studentName);
});

VBox scheduleCard =
        new VBox(
                5,
                scheduleCardIcon,
                scheduleTitle,
                scheduleCaption,
                scheduleAction
        );
VBox.setMargin(
        scheduleCaption,
        new Insets(80, 0, 0, 0)
);
VBox.setMargin(
        scheduleAction,
        new Insets(100, 0, 0, 0)
);

scheduleCard.setAlignment(
        Pos.TOP_CENTER
);

scheduleCard.setPrefHeight(
        250
);

scheduleCard.setMaxWidth(
        Double.MAX_VALUE
);

scheduleCard.setStyle(
"-fx-background-color: linear-gradient(to bottom right, #CAE5BC, #E1EDD4, #70D6C5);" +
"-fx-background-radius: 18;" +
"-fx-border-radius: 18;" +
"-fx-border-color: #70D6C5;" +
"-fx-border-width: 1.2;" +
"-fx-padding: 25;"
);


// MY APPOINTMENTS

FontAwesomeIconView appointmentCardIcon =
        new FontAwesomeIconView(
                FontAwesomeIcon.FOLDER
        );

appointmentCardIcon.setGlyphSize(32);

appointmentCardIcon.setFill(
        Color.web("#1B5E20")
);
// +Appointment Title
Label appointmentTitle =
        new Label(
                "My\n" +
     " Appointments"
        );

appointmentTitle.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                20
        )
);

appointmentTitle.setTextFill(
        Color.web("#1B5E20")
);

appointmentTitle.setAlignment(
        Pos.TOP_CENTER
);
appointmentTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

Label appointmentCaption =
        new Label(
                "View your booked\n" +
                "  appointments."
        );
// + Appointment Caption
appointmentCaption.setFont(
        Font.font(
                "Arial",
                20
        )
);

appointmentCaption.setTextFill(
        Color.web("#172B4D")
);

appointmentCaption.setAlignment(
        Pos.CENTER
);
// + Appointment Button
Button appointmentAction =
        new Button(
                "Go to My Appointments  →"
        );

appointmentAction.setPrefWidth(
        300
);

appointmentAction.setPrefHeight(
        45
);

appointmentAction.setStyle(
        "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
        "-fx-background-radius: 10;" +
        "-fx-font-family: Arial;" +
        "-fx-font-size: 14px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;" +
        "-fx-cursor: hand;"
);

appointmentAction.setOnAction(e -> {

    MyAppointment appointment =
            new MyAppointment();

    appointment.show(stage, studentName);
});

VBox appointmentCard =
        new VBox(
                5,
                appointmentCardIcon,
                appointmentTitle,
                appointmentCaption,
                appointmentAction
        );
VBox.setMargin(appointmentCaption,new Insets(80, 0,0,0));
VBox.setMargin(appointmentAction,new Insets(100, 0,0,0));

appointmentCard.setAlignment(
        Pos.TOP_CENTER
);

appointmentCard.setPrefHeight(
        300
);

appointmentCard.setMaxWidth(
        Double.MAX_VALUE
);


    appointmentCard.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #E1EDD4, #CAE5BC, #70D6C5);" +
        "-fx-background-radius: 18;" +
        "-fx-border-radius: 18;" +
        "-fx-border-color: #CAE5BC;" +
        "-fx-border-width: 1.2;" +
        "-fx-padding: 25;"
    );




// CANCEL APPOINTMENT

FontAwesomeIconView cancelCardIcon =
        new FontAwesomeIconView(
                FontAwesomeIcon.TIMES_CIRCLE
        );

cancelCardIcon.setGlyphSize(32);

cancelCardIcon.setFill(
        Color.web("#C62828")
);

Label cancelTitle =
        new Label(
                "Cancel \n" +
           "   Appointment"
        );

cancelTitle.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                20
        )
);

cancelTitle.setTextFill(
        Color.web("#C62828")
);

cancelTitle.setAlignment(
        Pos.CENTER
);
cancelTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

Label cancelCaption =
        new Label(
                "Cancel your existing\n" +
                "     appointment."
        );

cancelCaption.setFont(
        Font.font(
                "Arial",
                20
        )
);

cancelCaption.setTextFill(
        Color.web("#172B4D")
);

cancelCaption.setAlignment(
        Pos.CENTER
);

Button cancelAction =
        new Button(
                "Cancel Appointment  →"
        );

cancelAction.setPrefWidth(
        300
);

cancelAction.setPrefHeight(
        45
);

cancelAction.setStyle(
        "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
        "-fx-background-radius: 10;" +
        "-fx-font-family: Arial;" +
        "-fx-font-size: 15px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;" +
        "-fx-cursor: hand;"
    );

cancelAction.setOnAction(e -> {

    CancelAppointment cancel =
            new CancelAppointment();

    cancel.show(stage, studentName);
});

VBox cancelCard =
        new VBox(
                5,
                cancelCardIcon,
                cancelTitle,
                cancelCaption,
                cancelAction
        );
VBox.setMargin(cancelCaption, new Insets(80, 0, 0, 0));
VBox.setMargin(cancelAction, new Insets(100, 0, 0, 0));

cancelCard.setAlignment(
        Pos.TOP_CENTER
);

cancelCard.setPrefHeight(
        700
);

cancelCard.setMaxWidth(
        Double.MAX_VALUE
);

cancelCard.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #E1EDD4, #CAE5BC, #70D6C5);" +
        "-fx-background-radius: 18;" +
        "-fx-border-radius: 18;" +
        "-fx-border-color: #CAE5BC;" +
        "-fx-border-width: 1.2;" +
        "-fx-padding: 25;"
);


// QUICK CARDS

HBox quickCards =
        new HBox(
                20,
                scheduleCard,
                appointmentCard,
                cancelCard
        );

HBox.setHgrow(
        scheduleCard,
        Priority.ALWAYS
);

HBox.setHgrow(
        appointmentCard,
        Priority.ALWAYS
);

HBox.setHgrow(
        cancelCard,
        Priority.ALWAYS
);
        // MAIN CONTENT

        VBox mainContent =
                new VBox(
                        35,
                        welcomeBox,
                        quickActions,
                        quickCards
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
                Pos.TOP_LEFT
        );

        // BACKGROUND

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

        // LAYOUT

        BorderPane dashboard =
                new BorderPane();

        dashboard.setLeft(
                sidebar
        );

        dashboard.setCenter(
                mainContent
        );

        dashboard.setStyle(
                "-fx-background-color: rgba(245,248,252,0.78);"
        );

        root.getChildren().add(
                dashboard
        );

        // SCENE

        Scene scene =
                new Scene(
                        root,
                        1200,
                        750
                );

        stage.setTitle(
                "SignSlot - Student Dashboard"
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