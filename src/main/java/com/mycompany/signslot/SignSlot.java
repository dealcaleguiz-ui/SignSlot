package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignSlot extends Application {

    private static final String BLUE = "#1565C0";
    private static final String DARK_BLUE = "#0D47A1";

    @Override
    public void start(Stage stage) {
        showLogin(stage);
    }

    // LOGIN PAGE
    public void showLogin(Stage stage) {

        Image schoolLogo = new Image(
                getClass()
                        .getResource("/school-logo.jpg")
                        .toExternalForm()
        );

        ImageView logoImage =
                new ImageView(schoolLogo);

        logoImage.setFitWidth(110);
        logoImage.setFitHeight(110);
        logoImage.setPreserveRatio(true);

        Label logo =
                new Label("SignSlot");

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        38
                )
        );

        logo.setTextFill(
                Color.web(DARK_BLUE)
        );

        Label subtitle =
                new Label(
                        "Clearance Signing Appointment System"
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        subtitle.setTextFill(
                Color.web("#444444")
        );

        Label roleLabel =
                new Label("Login as");

        roleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        ComboBox<String> role =
                new ComboBox<>();

        role.getItems().addAll(
                "Student",
                "Admin"
        );

        role.setPromptText(
                "Select Role"
        );

        role.setPrefHeight(42);
        role.setPrefWidth(700);

        role.setStyle(
                "-fx-background-color: rgba(255,255,255,0.90);" +
                "-fx-border-color: #90A4AE;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;"
        );

        Label usernameLabel =
                new Label("Username");

        usernameLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        TextField username =
                new TextField();

        username.setPromptText(
                "Enter your username"
        );

        username.setPrefHeight(42);

        username.setMaxWidth(
                Double.MAX_VALUE
        );

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Enter your password"
        );

        password.setPrefHeight(42);

        password.setMaxWidth(
                Double.MAX_VALUE
        );

        Label message =
                new Label();

        message.setWrapText(true);

        message.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox roleHeader =
                new HBox(
                        10,
                        roleLabel,
                        message
                );

        roleHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                message,
                Priority.ALWAYS
        );

        message.setAlignment(
                Pos.CENTER_RIGHT
        );
        
        //LOG IN BUTTON
        Button loginButton =
            new Button("→  LOGIN");

        loginButton.setPrefHeight(44);
        loginButton.setPrefWidth(700);

        loginButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4D52B4, #4E9CE8);" +
                "-fx-background-radius: 10;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;" +
                "-fx-cursor: hand;"
        );

        loginButton.setTextFill(
                Color.WHITE
        );
        
        //REGISTER LABEL/BUTTON
        Label dontHave =
                new Label(
                        "Don't have an account?"
                );

        Label registerLink =
                new Label(
                        "Register as a Student here"
                );

        dontHave.setStyle(
                "-fx-font-size: 13px"
        );

        registerLink.setStyle(
                "-fx-text-fill: #1565C0;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-underline: true"
        );

        HBox registerRow =
                new HBox(
                        5,
                        dontHave,
                        registerLink
                );

        registerRow.setAlignment(
                Pos.CENTER
        );
            
        
        loginButton.setOnAction(e -> {

            if (
                    role.getValue() == null ||
                    username.getText()
                            .trim()
                            .isEmpty() ||
                    password.getText()
                            .trim()
                            .isEmpty()
            ) {

                message.setText(
                        "Please complete all fields."
                );

                message.setTextFill(
                        Color.RED
                );

                return;
            }
            
            //INPUT CONNECT DATABASE
            String enteredUsername =
                    username.getText().trim();

            String enteredPassword =
                    password.getText();

            String selectedRole =
                    role.getValue();

            String sql =
                    "SELECT id, name, role " +
                    "FROM users " +
                    "WHERE username = ? " +
                    "AND password = ? " +
                    "AND role = ?";

            try (
                    Connection connection =
                            Database.connect();

                    PreparedStatement statement =
                            connection.prepareStatement(sql)
            ) {

                statement.setString(
                        1,
                        enteredUsername
                );

                statement.setString(
                        2,
                        enteredPassword
                );

                statement.setString(
                        3,
                        selectedRole
                );

                ResultSet result =
                        statement.executeQuery();

                if (result.next()) {

                    if (
                            selectedRole.equals(
                                    "Student"
                            )
                    ) {

                        StudentDashboard dashboard =
                                new StudentDashboard();

                        dashboard.show(
                                stage,
                                result.getString("name")
                        );

                    } else if (
                            selectedRole.equals(
                                    "Admin"
                            )
                    ) {

                        AdminDashboard dashboard =
                                new AdminDashboard();

                        dashboard.show(stage);
                    }

                } else {

                    if (
                            selectedRole.equals(
                                    "Student"
                            )
                    ) {

                        message.setText(
                                "Student account not found. " +
                                "Please register first."
                        );

                    } else {

                        message.setText(
                                "Invalid admin username " +
                                "or password."
                        );
                    }

                    message.setTextFill(
                            Color.RED
                    );
                }

            } catch (SQLException ex) {

                message.setText(
                        "Database error."
                );

                message.setTextFill(
                        Color.RED
                );

                System.out.println(
                        "Login error: "
                        + ex.getMessage()
                );
            }
        });

        registerLink.setOnMouseClicked(e -> {

            StudentRegistration registration =
                    new StudentRegistration();

            registration.show(stage);
        });

        VBox form =
                new VBox(
                        7,
                        roleHeader,
                        role,
                        usernameLabel,
                        username,
                        passwordLabel,
                        password
                );

        form.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox actions =
                new VBox(
                        10,
                        loginButton,
                        registerRow
                );

        actions.setAlignment(
                Pos.CENTER
        );

        actions.setPrefWidth(700);
        actions.setMaxWidth(700);

        VBox content =
                new VBox(
                        14,
                        logoImage,
                        logo,
                        subtitle,
                        form,
                        actions
                );

        content.setAlignment(
                Pos.CENTER
        );

        content.setPadding(
                new Insets(
                        20,
                        22,
                        20,
                        22
                )
        );

        content.setPrefWidth(650);
        content.setMaxWidth(650);

        content.setStyle(
                "-fx-background-color: rgba(255,255,255,0.70);" +
                "-fx-background-radius: 20;" +
                "-fx-effect: dropshadow(" +
                "gaussian," +
                "rgba(0,0,0,0.20)," +
                "25," +
                "0.18," +
                "0," +
                "8" +
                ");"
        );

        StackPane root =
                new StackPane();

        Image backgroundImage =
                new Image(
                        getClass()
                                .getResource("/Picture.jpg")
                                .toExternalForm()
                );

        ImageView background =
                new ImageView(backgroundImage);

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

        StackPane.setMargin(
                content,
                new Insets(
                        30,
                        0,
                        30,
                        0
                )
        );

        root.getChildren().add(
                content
        );

        Scene scene =
                new Scene(
                        root,
                        900,
                        600
                );

        stage.setTitle(
                "SignSlot - Login"
        );

        stage.setScene(
                scene
        );

        stage.setResizable(true);

        stage.show();
    }

    public static Button button(
            String text,
            String background,
            Color textColor
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(45);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setStyle(
                "-fx-background-color: "
                + background + ";" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        button.setTextFill(
                textColor
        );

        return button;
    }

    public static void main(
            String[] args
    ) {

        Database.createTables();

        Database.insertSampleUsers();

        Database.insertSampleInstructors();

        Database.insertSampleSchedules();

        launch(args);
    }
}