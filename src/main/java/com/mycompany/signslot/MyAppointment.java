package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;
import com.mycompany.signslot.model.Appointment;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MyAppointment {

    private final String BLUE = "#1565C0";
    private final String DARK_BLUE = "#0D47A1";

    public void show(Stage stage, String studentName) {

        StackPane root = new StackPane();

        ImageView background = new ImageView(
                new Image(
                        getClass().getResourceAsStream("/Picture.jpg")
                )
        );

        background.setPreserveRatio(false);
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());

        BorderPane mainLayout = new BorderPane();

        VBox sidebar = new VBox();
        sidebar.setPrefWidth(270);
        sidebar.setPadding(new Insets(25, 15, 20, 15));
        sidebar.setSpacing(10);
        sidebar.setStyle(
                "-fx-background-color: #4E9CE8;"
        );

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
                new Label("Display Appointment Confirmation");

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

        Button dashboardButton = createButton(
                FontAwesomeIcon.HOME,
                "Dashboard",
                false
        );

        Button scheduleButton = createButton(
                FontAwesomeIcon.CALENDAR,
                "View Instructor Schedules",
                false
        );

        Button appointmentButton = createButton(
                FontAwesomeIcon.FOLDER,
                "My Appointments",
                true
        );

        Button cancelButton = createButton(
                FontAwesomeIcon.TIMES_CIRCLE,
                "Cancel Appointment",
                false
        );

        Button profileButton = createButton(
                FontAwesomeIcon.USER,
                "Profile",
                false
        );

        Button logoutButton = createButton(
                FontAwesomeIcon.SIGN_OUT,
                "Logout",
                false
        );

        VBox navigation = new VBox(
                10,
                dashboardButton,
                scheduleButton,
                appointmentButton,
                cancelButton,
                profileButton
        );

        VBox bottomNavigation = new VBox(logoutButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_CENTER);

        VBox.setVgrow(navigation, javafx.scene.layout.Priority.ALWAYS);

        sidebar.getChildren().addAll(
                brand,
                navigation,
                bottomNavigation
        );

        dashboardButton.setOnAction(e -> {
            StudentDashboard dashboard = new StudentDashboard();
            dashboard.show(stage, studentName);
        });

        scheduleButton.setOnAction(e -> {
            ScheduleView scheduleView = new ScheduleView();
            scheduleView.show(stage, studentName);
        });
        appointmentButton.setOnAction(e -> {
            MyAppointment appointment = new MyAppointment();
            appointment.show(stage, studentName);
        });

        cancelButton.setOnAction(e -> {
            CancelAppointment cancelAppointment = new CancelAppointment();
            cancelAppointment.show(stage, studentName);
        });

        logoutButton.setOnAction(e -> {
            SignSlot login = new SignSlot();
            login.showLogin(stage);
        });

        VBox content = new VBox();
        content.setSpacing(20);
        content.setPadding(new Insets(35));
        content.setStyle(
                "-fx-background-color: rgba(245,248,252,0.78);"
        );

        Label title = new Label("My Appointments");
        title.setStyle(
                "-fx-font-family: Arial;" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0D47A1;"
        );

        TableView<Appointment> table = new TableView<>();

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

            // APPLY THE SAME TABLE CSS AS SCHEDULE VIEW
            table.getStylesheets().add(
                    getClass()
                            .getResource("/css/table-style.css")
                            .toExternalForm()
            );

            table.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY
            );
            table.setStyle("-fx-alignment: CENTER;");

            TableColumn<Appointment, Integer> appointmentIdColumn =
                    new TableColumn<>("Appointment ID");

            appointmentIdColumn.setCellValueFactory(
                    new PropertyValueFactory<>("appointmentId")
            );

            TableColumn<Appointment, Integer> studentIdColumn =
                    new TableColumn<>("Student ID");

            studentIdColumn.setCellValueFactory(
                    new PropertyValueFactory<>("studentId")
            );

            TableColumn<Appointment, Integer> scheduleIdColumn =
                    new TableColumn<>("Schedule ID");

            scheduleIdColumn.setCellValueFactory(
                    new PropertyValueFactory<>("scheduleId")
            );

            TableColumn<Appointment, String> statusColumn =
                    new TableColumn<>("Status");

            statusColumn.setCellValueFactory(
                    new PropertyValueFactory<>("status")
            );
            appointmentIdColumn.setStyle("-fx-alignment: CENTER;");
            studentIdColumn.setStyle("-fx-alignment: CENTER;");
            scheduleIdColumn.setStyle("-fx-alignment: CENTER;");
            statusColumn.setStyle("-fx-alignment: CENTER;");

            table.getColumns().addAll(
                    appointmentIdColumn,
                    studentIdColumn,
                    scheduleIdColumn,
                    statusColumn
            );

            loadAppointments(
                    table,
                    studentName
        );

    VBox tableCard = new VBox(20);

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

        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER);

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
            "-fx-cursor: hand;"
        );

        backButton.setOnAction(e -> {
            StudentDashboard dashboard =
                    new StudentDashboard();

            dashboard.show(stage, studentName);
        });

        buttons.getChildren().add(backButton);

        tableCard.getChildren().setAll(
                table,
                buttons
        );

        content.setAlignment(Pos.TOP_CENTER);

            title.setAlignment(Pos.CENTER);
            title.setMaxWidth(Double.MAX_VALUE);

        content.getChildren().addAll(
                    title,
                    tableCard
        );


        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(content);

        root.getChildren().addAll(
                background,
                mainLayout
        );

        Scene scene = new Scene(
                root,
                1200,
                750
        );

        stage.setTitle("SignSlot - My Appointments");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    private Button createButton(
        FontAwesomeIcon icon,
        String text,
        boolean active
) {

    FontAwesomeIconView iconView =
            new FontAwesomeIconView(icon);

    iconView.setGlyphSize(18);

    Button button = new Button(text, iconView);

    button.setPrefWidth(240);
    button.setPrefHeight(48);
    button.setMaxWidth(Double.MAX_VALUE);

    if (active) {

        // Active button: radiant indigo-to-blue gradient
        button.setStyle(
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        iconView.setFill(Color.WHITE);

    } else {

        // Inactive buttons
        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #17324D;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        iconView.setFill(Color.web("#17324D"));

        // Hover effect
        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.22);" +
                    "-fx-font-family: Arial;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #17324D;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-padding: 0 15 0 15;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
            );
        });

        button.setOnMouseExited(e -> {
            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-font-family: Arial;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #17324D;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-padding: 0 15 0 15;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
            );
        });
    }

    return button;
}

    private void loadAppointments(
            TableView<Appointment> table,
            String studentName
    ) {

        ObservableList<Appointment> appointments =
                FXCollections.observableArrayList();

        String sql =
                """
                SELECT
                    appointments.appointment_id,
                    appointments.student_id,
                    appointments.schedule_id,
                    appointments.status
                FROM appointments
                INNER JOIN students
                    ON appointments.student_id = students.id
                INNER JOIN users
                    ON students.user_id = users.id
                WHERE users.name = ?
                AND users.role = 'Student'
                """;

        try (
                Connection connection =
                        Database.connect();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    studentName
            );

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Appointment appointment =
                        new Appointment(
                                result.getInt("appointment_id"),
                                result.getInt("student_id"),
                                result.getInt("schedule_id"),
                                result.getString("status")
                        );

                appointments.add(appointment);
            }

            table.setItems(appointments);

        } catch (Exception e) {

            System.out.println(
                    "Error loading appointments: "
                    + e.getMessage()
            );
        }
    }
}