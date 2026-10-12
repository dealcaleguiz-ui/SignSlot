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

public class AdminDashboard {

    private static final String BLUE = "#1565C0";
    private static final String DARK_BLUE = "#0D47A1";

    public void show(Stage stage) {
        show(stage, "Admin");
    }

    public void show(Stage stage, String adminName) {

        // LOGO
        Image logoImage = new Image(
                getClass()
                        .getResource("/cec-logo.png")
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
                Color.web(DARK_BLUE)
        );

        Label dashboardText =
                new Label("Admin Dashboard");

        dashboardText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        dashboardText.setTextFill(
                Color.web("#555555")
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
                        "Manage Schedules",
                        scheduleIcon
                );


        FontAwesomeIconView appointmentIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.FOLDER
                );

        appointmentIcon.setGlyphSize(18);

        Button appointmentButton =
                new Button(
                        "View Appointments",
                        appointmentIcon
                );


        FontAwesomeIconView updateIcon =
                new FontAwesomeIconView(
                        FontAwesomeIcon.TIMES_CIRCLE
                );

        updateIcon.setGlyphSize(18);

        Button updateButton =
                new Button(
                        "Update Appointment",
                        updateIcon
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
        updateButton.setPrefHeight(48);
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

        updateButton.setMaxWidth(
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
                "-fx-background-color: #1565C0;" +
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

        updateButton.setStyle(
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
                "-fx-background-color: rgba(255,255,255,0.94);"
        );

        // DASHBOARD BUTTON

        dashboardButton.setOnAction(e -> {

            navigation.getChildren().setAll(
                    dashboardButton,
                    scheduleButton,
                    appointmentButton,
                    updateButton
            );

            bottomNavigation.getChildren().setAll(
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

            updateButton.setStyle(
                    normalButtonStyle
            );

            logoutButton.setStyle(
                    logoutStyle
            );
        });

        // SCHEDULE NAVIGATION ACTIONS

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

            updateButton.setStyle(
                normalButtonStyle
            );

            logoutButton.setStyle(
                logoutStyle
            );

           // ManageSchedules manageSchedules =
               // new ManageSchedules();

           // manageSchedules.show(stage);
        });

        // NAVIGATION VIEW APPOINTMENT
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

            updateButton.setStyle(
                normalButtonStyle
            );

            logoutButton.setStyle(
                logoutStyle
            );

        //ViewAppointments appointment =
           // new ViewAppointments();

       // appointment.show(stage);
    });
        
    //NAVIGATION UPDATE BUTTON
    updateButton.setOnAction(e -> {

        dashboardButton.setStyle(
            normalButtonStyle
        );

        scheduleButton.setStyle(
            normalButtonStyle
        );

        appointmentButton.setStyle(
            normalButtonStyle
        );

        updateButton.setStyle(
            activeButtonStyle
         );

        logoutButton.setStyle(
            logoutStyle
        );

        //UpdateAppointment updateAppointment =
           // new UpdateAppointment();

    //logoutButton.setOnAction(e -> {

        SignSlot login =
            new SignSlot();

        login.showLogin(stage);
    });


        // WELCOME

        Label welcome =
                new Label(
                        "Welcome, " +
                        "Admin" +
                        "!"
                );

        welcome.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        40
                )
        );

        welcome.setTextFill(
                Color.web("#0D47A1")
        );

        Label welcomeText =
                new Label(
                        "Efficient scheduling, smoother clearance signing."
                     
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

// MANAGE INSTRUCTOR SCHEDULES

FontAwesomeIconView scheduleCardIcon =
        new FontAwesomeIconView(
                FontAwesomeIcon.CALENDAR
        );

scheduleCardIcon.setGlyphSize(32);

scheduleCardIcon.setFill(
        Color.web("#4D54B4")
);
//+ View Sched. Title
Label scheduleTitle =
        new Label(
                "Manage\n"+
               "Schedules"
        );

scheduleTitle.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                20
        )
);

scheduleTitle.setTextFill(
        Color.web("#4D54B4")
);

scheduleTitle.setAlignment(
        Pos.TOP_CENTER
);
scheduleTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

// + Manage Sced. Caption
Label scheduleCaption =
        new Label(
                "Manage instructor availability\n " +
                    "and maintain accurate\n "+
                 "clearance signing schedules"
        );

scheduleCaption.setFont(
        Font.font(
                "Arial",
                15
        )
);

scheduleCaption.setTextFill(
        Color.web("#4D54B4")
);

scheduleCaption.setAlignment(
        Pos.CENTER
);
scheduleCaption.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
// +Manage Sched. Button
Button scheduleAction =
        new Button(
                "Manage Schedules  →"
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

    scheduleView.show(stage, adminName);
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
"-fx-background-color: linear-gradient(to bottom right, #E1EDD4, #CAE5BC, #70D6C5);" +
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
                "View\n" +
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
                "Monitor scheduled appointments\n"+
                  "and keep track of students \n"+
                      "clearance activities"
        );
// + View Appointment Caption
appointmentCaption.setFont(
        Font.font(
                "Arial",
                15
        )
);

appointmentCaption.setTextFill(
        Color.web("#172B4D")
);

appointmentCaption.setAlignment(
        Pos.CENTER
);
appointmentCaption.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

// + Appointment Button
Button appointmentAction =
        new Button(
                "View Appointments  →"
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

    //ViewAppointments appointment =
          //  new ViewAppointments();

    //appointment.show(stage);
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


// UPDATE APPOINTMENT

FontAwesomeIconView updateCardIcon =
        new FontAwesomeIconView(
                FontAwesomeIcon.TIMES_CIRCLE
        );

updateCardIcon.setGlyphSize(32);

updateCardIcon.setFill(
        Color.web("#4D54B4")
);

Label updateTitle =
        new Label(
                "Update\n"+
               "Appointment"
        );

updateTitle.setFont(
        Font.font(
                "Arial",
                FontWeight.BOLD,
                20
        )
);

updateTitle.setTextFill(
        Color.web("#4D54B4")
);

updateTitle.setAlignment(
        Pos.CENTER
);
updateTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

Label updateCaption =
        new Label(
                "Update appointment statuses\n "+
              "and keep every booking organized"
        );

updateCaption.setFont(
        Font.font(
                "Arial",
                15
        )
);

updateCaption.setTextFill(
        Color.web("#172B4D")
);

updateCaption.setAlignment(
        Pos.CENTER
);
updateCaption.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

Button updateAction =
        new Button(
                "Update Appointment  →"
        );

updateAction.setPrefWidth(
        300
);

updateAction.setPrefHeight(
        46
);

updateAction.setStyle(
"-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
"-fx-background-radius: 10;" +
"-fx-font-family: Arial;" +
"-fx-font-size: 14px;" +
"-fx-font-weight: bold;" +
"-fx-text-fill: white;" +
"-fx-cursor: hand;"
);

updateAction.setOnAction(e -> {

   // UpdateAppointment update =
          //  new UpdateAppointment();

   // update.show(stage);
});

VBox updateCard =
        new VBox(
                5,
                updateCardIcon,
                updateTitle,
                updateCaption,
                updateAction
        );
VBox.setMargin(updateCaption, new Insets(80, 0, 0, 0));
VBox.setMargin(updateAction, new Insets(100, 0, 0, 0));

updateCard.setAlignment(
        Pos.TOP_CENTER
);

updateCard.setPrefHeight(
        700
);

updateCard.setMaxWidth(
        Double.MAX_VALUE
);
updateCard.setStyle(
"-fx-background-color: linear-gradient(to bottom right, #CAE5BC, #E1EDD4, #70D6C5);" +
"-fx-background-radius: 18;" +
"-fx-border-radius: 18;" +
"-fx-border-color: #70D6C5;" +
"-fx-border-width: 1.2;" +
"-fx-padding: 25;"
);


// QUICK CARDS

HBox quickCards =
        new HBox(
                20,
                scheduleCard,
                appointmentCard,
                updateCard
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
        updateCard,
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
                "SignSlot - Admin Dashboard"
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