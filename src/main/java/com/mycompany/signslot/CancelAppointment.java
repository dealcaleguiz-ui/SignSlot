package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;
import com.mycompany.signslot.model.Appointment;

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

public class CancelAppointment {

    private static final String BLUE = "#1565C0";
    private static final String DARK_BLUE = "#0D47A1";

    public void show(Stage stage, String studentName) {

        // Logo
        Image logoImage = new Image(
                getClass()
                        .getResource("/blue-logo.png")
                        .toExternalForm()
        );

        ImageView logo = new ImageView(logoImage);

        logo.setFitWidth(90);
        logo.setFitHeight(90);
        logo.setPreserveRatio(true);

        Label signSlot = new Label("SignSlot");

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

        Label pageText =
                new Label("Cancel Appointment");

        pageText.setFont(
                Font.font("Arial", 13)
        );

        pageText.setTextFill(
                Color.web(DARK_BLUE)
        );

        VBox logoText =
                new VBox(
                        2,
                        signSlot,
                        pageText
                );

        logoText.setAlignment(Pos.CENTER);

        VBox brand =
                new VBox(
                        12,
                        logo,
                        logoText
                );

        brand.setAlignment(Pos.CENTER);

        // Navigation buttons
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

        Button[] buttons = {
            dashboardButton,
            scheduleButton,
            appointmentButton,
            cancelButton,
            profileButton,
            logoutButton
        };

        for (Button button : buttons) {

            button.setPrefHeight(48);
            button.setMaxWidth(
                    Double.MAX_VALUE
            );
        }

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

        dashboardButton.setStyle(normalButtonStyle);
        scheduleButton.setStyle(normalButtonStyle);
        appointmentButton.setStyle(normalButtonStyle);
        cancelButton.setStyle(activeButtonStyle);
        profileButton.setStyle(normalButtonStyle);
        logoutButton.setStyle(logoutStyle);

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

        sidebar.setPrefWidth(270);

        sidebar.setStyle(
                 "-fx-background-color: #4E9CE8; "
        );

        // Navigation
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

        logoutButton.setOnAction(e -> {

            SignSlot login =
                    new SignSlot();

            login.showLogin(stage);
        });

        // Page title
        Label title =
                new Label(
                        "Cancel Appointment"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        35
                )
        );

        title.setTextFill (Color.web(DARK_BLUE));

        // Appointment table
        TableView<Appointment> table =
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

        TableColumn<Appointment, Integer> appointmentIdColumn =
                new TableColumn<>(
                        "Appointment ID"
                );

        appointmentIdColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "appointmentId"
                )
        );

        TableColumn<Appointment, Integer> studentIdColumn =
                new TableColumn<>(
                        "Student ID"
                );

        studentIdColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "studentId"
                )
        );

        TableColumn<Appointment, Integer> scheduleIdColumn =
                new TableColumn<>(
                        "Schedule ID"
                );

        scheduleIdColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "scheduleId"
                )
        );

        TableColumn<Appointment, String> statusColumn =
                new TableColumn<>(
                        "Status"
                );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
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

        // Cancel button
        Button cancelAppointmentButton =
                new Button(
                        "Cancel Appointment"
                );

        cancelAppointmentButton.setPrefWidth(200);
        cancelAppointmentButton.setPrefHeight(45);

        cancelAppointmentButton.setStyle(
                "-fx-background-color: #D32F2F;" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        cancelAppointmentButton.setOnAction(e -> {

            Appointment selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.WARNING
                        );

                alert.setTitle(
                        "No Appointment Selected"
                );

                alert.setHeaderText(null);

                alert.setContentText(
                        "Please select an appointment first."
                );

                alert.showAndWait();

                return;
            }

            cancelAppointment(
                    selected,
                    table
            );
        });

        // Back button
        Button backButton =
                new Button("Back");

        backButton.setPrefWidth(120);
        backButton.setPrefHeight(45);

        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0D47A1;"
        );

        backButton.setOnAction(e -> {

            StudentDashboard dashboard =
                    new StudentDashboard();

            dashboard.show(
                    stage,
                    studentName
            );
        });

        HBox buttonBox =
                new HBox(
                        15,
                        cancelAppointmentButton,
                        backButton
                );

        buttonBox.setAlignment(
                Pos.CENTER
        );

        VBox tableCard =
                new VBox(
                        20,
                        table,
                        buttonBox
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

        // Background
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

        background.setPreserveRatio(false);

        background.fitWidthProperty().bind(
                root.widthProperty()
        );

        background.fitHeightProperty().bind(
                root.heightProperty()
        );

        root.getChildren().add(
                background
        );

        BorderPane appointmentPage =
                new BorderPane();

        appointmentPage.setLeft(
                sidebar
        );

        appointmentPage.setCenter(
                mainContent
        );

        appointmentPage.setStyle(
                "-fx-background-color: rgba(245,248,252,0.78);"
        );

        root.getChildren().add(
                appointmentPage
        );

        Scene scene =
                new Scene(
                        root,
                        1200,
                        750
                );

        stage.setTitle(
                "SignSlot - Cancel Appointment"
        );

        stage.setScene(scene);

        stage.setResizable(true);

        stage.show();
    }

    // Load appointments
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

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                appointments.add(
                        new Appointment(
                                resultSet.getInt(
                                        "appointment_id"
                                ),
                                resultSet.getInt(
                                        "student_id"
                                ),
                                resultSet.getInt(
                                        "schedule_id"
                                ),
                                resultSet.getString(
                                        "status"
                                )
                        )
                );
            }

            table.setItems(
                    appointments
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database error: " +
                    e.getMessage()
            );
        }
    }

    // Cancel appointment
    private void cancelAppointment(
            Appointment appointment,
            TableView<Appointment> table
    ) {

        String updateAppointment =
                """
                UPDATE appointments
                SET status = 'Cancelled'
                WHERE appointment_id = ?
                """;

        String updateSchedule =
                """
                UPDATE schedules
                SET available_slots = available_slots + 1
                WHERE schedule_id = ?
                """;

        try (
                Connection connection =
                        Database.connect()
        ) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement appointmentStatement =
                            connection.prepareStatement(
                                    updateAppointment
                            );

                    PreparedStatement scheduleStatement =
                            connection.prepareStatement(
                                    updateSchedule
                            )
            ) {

                appointmentStatement.setInt(
                        1,
                        appointment.getAppointmentId()
                );

                appointmentStatement.executeUpdate();

                scheduleStatement.setInt(
                        1,
                        appointment.getScheduleId()
                );

                scheduleStatement.executeUpdate();

                connection.commit();

                appointment.setStatus(
                        "Cancelled"
                );

                table.refresh();

                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION
                        );

                alert.setTitle(
                        "Cancellation Successful"
                );

                alert.setHeaderText(null);

                alert.setContentText(
                        "Appointment cancelled successfully!"
                );

                alert.showAndWait();

            } catch (SQLException e) {

                connection.rollback();

                throw e;
            }

        } catch (SQLException e) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR
                    );

            alert.setTitle(
                    "Cancellation Failed"
            );

            alert.setHeaderText(null);

            alert.setContentText(
                    "Unable to cancel the appointment."
            );

            alert.showAndWait();

            System.out.println(
                    "Database error: " +
                    e.getMessage()
            );
        }
    }
}