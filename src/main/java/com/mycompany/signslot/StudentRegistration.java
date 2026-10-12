package com.mycompany.signslot;

import com.mycompany.signslot.database.Database;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
import java.sql.SQLException;

public class StudentRegistration {

    private static final String BLUE = "#1565C0";
    private static final String DARK_BLUE = "#0D47A1";

    public void show(Stage stage) {

        // TITLE
        Label title = new Label("Student Registration");

        title.setFont(
                Font.font("Arial", FontWeight.BOLD, 30)
        );

        title.setTextFill(
                Color.web(DARK_BLUE)
        );

        Label subtitle = new Label(
                "Create your SignSlot student account"
        );

        subtitle.setFont(
                Font.font("Arial", 14)
        );

        subtitle.setTextFill(
                Color.web("#555555")
        );
        
        //LOGO
        Image logoImage = new Image(getClass()
                       .getResource("/registration-logo.png")
                       .toExternalForm()
        );
        ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(70);
        logo.setFitHeight(70);
        logo.setPreserveRatio(true);
        
        VBox registrationText = new VBox(
                15,
                title,
                subtitle
        );
        registrationText.setAlignment(Pos.CENTER_LEFT);
        
        HBox registrationHeader = new HBox(
                15,
                logo,
                registrationText
        );
        registrationHeader.setAlignment(Pos.CENTER);
        
        // MESSAGE
        Label message = new Label();

        message.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        message.setTextFill(Color.RED);

        // INPUTS
        Label nameLabel = new Label("Full Name");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        Label studentIdLabel = new Label("Student ID");
        studentIdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        Label courseLabel = new Label("Course");
        courseLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
       
        Label yearLabel = new Label("Year Level");
        yearLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
       
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
       
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        Label confirmLabel = new Label("Confirm Password");
        confirmLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        //INPUTS
        TextField name = new TextField();
        name.setPromptText("Enter Full Name");
        name.setPrefHeight(40);

        TextField studentId = new TextField();
        studentId.setPromptText("Enter Student ID");
        studentId.setPrefHeight(40);

        ComboBox<String> course = new ComboBox<>();

        course.getItems().addAll(
                "BSIT",
                "BSHM",
                "BSTM",
                "BSED",
                "BEED"
        );

        course.setPromptText("Select Course");
        course.setPrefHeight(40);
        course.setMaxWidth(Double.MAX_VALUE);

        ComboBox<String> yearLevel = new ComboBox<>();

        yearLevel.getItems().addAll(
                "1",
                "2",
                "3",
                "4"
        );

        yearLevel.setPromptText("Select Year Level");
        yearLevel.setPrefHeight(40);
        yearLevel.setMaxWidth(Double.MAX_VALUE);

        TextField username = new TextField();
        username.setPromptText("Create Username");
        username.setPrefHeight(40);

        PasswordField password = new PasswordField();
        password.setPromptText("Create Password");
        password.setPrefHeight(40);

        PasswordField confirmPassword =
                new PasswordField();

        confirmPassword.setPromptText(
                "Re-enter Password"
        );

        confirmPassword.setPrefHeight(40);

        // MESSAGE HEADER
        HBox nameHeader = new HBox(
                nameLabel,
                message
        );

        nameHeader.setAlignment(
                Pos.BASELINE_LEFT
        );

        HBox.setHgrow(
                message,
                Priority.ALWAYS
        );

        message.setAlignment(
                Pos.CENTER_RIGHT
        );

        // FORM
        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(10);
        form.setPrefWidth(700);

        ColumnConstraints column1 =
                new ColumnConstraints();

        ColumnConstraints column2 =
                new ColumnConstraints();

        column1.setPercentWidth(50);
        column2.setPercentWidth(50);

        form.getColumnConstraints().addAll(
                column1,
                column2
        );

        form.add(
                nameHeader,
                0,
                0,
                2,
                1
        );

        form.add(
                name,
                0,
                1,
                2,
                1
        );

        form.add(
                studentIdLabel,
                0,
                2
        );

        form.add(
                courseLabel,
                1,
                2
        );

        form.add(
                studentId,
                0,
                3
        );

        form.add(
                course,
                1,
                3
        );

        form.add(
                yearLabel,
                0,
                4
        );

        form.add(
                usernameLabel,
                1,
                4
        );

        form.add(
                yearLevel,
                0,
                5
        );

        form.add(
                username,
                1,
                5
        );

        form.add(
                passwordLabel,
                0,
                6
        );

        form.add(
                confirmLabel,
                1,
                6
        );

        form.add(
                password,
                0,
                7
        );

        form.add(
                confirmPassword,
                1,
                7
        );

        // ReGISTER BUTTONS
        Button registerButton =
                new Button("✓  CREATE ACCOUNT");

        registerButton.setPrefHeight(47);
        registerButton.setMaxWidth(
                Double.MAX_VALUE
        );

        registerButton.setStyle(
                "-fx-background-color: #1565C0;" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        registerButton.setTextFill(Color.WHITE);

        form.add(
                registerButton,
                0,
                8,
                2,
                1
        );
        GridPane.setMargin(registerButton,new Insets(10,0,0,0));
        
        //BACK BUTTON
        Button backButton =
                new Button("‹  BACK TO LOGIN");

        backButton.setPrefHeight(47);
        backButton.setMaxWidth(
                Double.MAX_VALUE
        );

        backButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #1565C0;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        backButton.setTextFill(
                Color.web(BLUE)
        );

        form.add(
                backButton,
                0,
                9,
                2,
                1
        );

        // DATABASE
        registerButton.setOnAction(e -> {

            String enteredStudentId =
                    studentId.getText().trim();

            String enteredName =
                    name.getText().trim();

            String enteredCourse =
                    course.getValue();

            String enteredYear =
                    yearLevel.getValue();

            String enteredUsername =
                    username.getText().trim();

            String enteredPassword =
                    password.getText();

            String enteredConfirmPassword =
                    confirmPassword.getText();

            if (
                    enteredStudentId.isEmpty() ||
                    enteredName.isEmpty() ||
                    enteredCourse == null ||
                    enteredYear == null ||
                    enteredUsername.isEmpty() ||
                    enteredPassword.isEmpty() ||
                    enteredConfirmPassword.isEmpty()
            ) {

                message.setText("Please complete all fields." );
                message.setTextFill(Color.RED);

                return;
            }

            if (
                    !enteredPassword.equals(
                            enteredConfirmPassword
                    )
            ) {

                message.setText(
                        "Passwords do not match."
                );

                message.setTextFill(Color.RED);

                return;
            }

            int year =
                    Integer.parseInt(enteredYear);

            String userSql =
                    """
                    INSERT INTO users
                    (name, username, password, role)
                    VALUES (?, ?, ?, 'Student')
                    """;

            try (
                    Connection connection =
                            Database.connect();

                    PreparedStatement userStatement =
                            connection.prepareStatement(
                                    userSql,
                                    java.sql.Statement
                                            .RETURN_GENERATED_KEYS
                            )
            ) {

                userStatement.setString(1,enteredName);

                userStatement.setString(2,enteredUsername);

                userStatement.setString(3,enteredPassword);

                userStatement.executeUpdate();

                int userId;

                try (
                        var keys =
                                userStatement
                                        .getGeneratedKeys()
                ) {
                    if (!keys.next()) {

                        message.setText("Registration failed.");

                        message.setTextFill(Color.RED);

                        return;
                    }

                    userId = keys.getInt(1);
                }

                String studentSql =
                        """
                        INSERT INTO students
                        (student_id, user_id, course, year_level)
                        VALUES (?, ?, ?, ?)
                        """;

                try (
                        PreparedStatement studentStatement =
                            connection.prepareStatement(studentSql)
                ) {

                    studentStatement.setString(
                            1,
                            enteredStudentId
                    );

                    studentStatement.setInt(
                            2,
                            userId
                    );

                    studentStatement.setString(
                            3,
                            enteredCourse
                    );

                    studentStatement.setInt(
                            4,
                            year
                    );

                    studentStatement.executeUpdate();
                }

                message.setText(
                        "Registration successful!"
                );

                message.setTextFill(Color.GREEN);

                studentId.clear();
                name.clear();
                course.setValue(null);
                yearLevel.setValue(null);
                username.clear();
                password.clear();
                confirmPassword.clear();

            } catch (SQLException ex) {
                if (
                    ex.getMessage()
                        .toLowerCase()
                        .contains("unique")
                ){
                message.setText( "Student ID or username already exists.");

                } else {
                    message.setText("Registration failed.");
                }
                message.setTextFill(Color.RED);
            }
        });

        //BACK BUTTON
        backButton.setOnAction(e -> {

            SignSlot login = new SignSlot();
            login.showLogin(stage);
        });

        // CONTENT DESIGN     
        VBox content = new VBox(
                14,
                registrationHeader,
                form
        );

        content.setAlignment(Pos.CENTER);

        content.setPadding(
                new Insets(20, 22, 20, 22)
        );

        content.setPrefWidth(850);
        content.setMaxWidth(850);

        content.setStyle(
                "-fx-background-color: rgba(255,255,255,0.70);" +
                "-fx-background-radius: 20;" +
                "-fx-effect: dropshadow(" +
                "gaussian," +
                "rgba(0,0,0,0.20)," +
                "25," +
                "0.18," +
                "0," +
                "8);"
        );
        
        //LAYOUT CONTAINER
        StackPane root = new StackPane();

        Image backgroundImage = new Image
            (getClass()
                .getResource( "/Picture.jpg")
                .toExternalForm()
            );

        ImageView background = new ImageView(backgroundImage);

        background.setPreserveRatio(false);
        
        background.fitWidthProperty().bind(
            root.widthProperty()
        );
        background.fitHeightProperty().bind(
            root.heightProperty()
        );
        
        //ELEMENTS INSIDE THE LAYOUT
        root.getChildren().add(background); //add background image

        StackPane.setMargin(
            content,new Insets(50, 0, 50, 0) //sizes: top,right,bottom,left
        );

        root.getChildren().add(content); // add content container

        Scene scene = new Scene //Window Content
       (
            root,900,700
        );

        stage.setTitle(        //Window Title
            "SignSlot - Student Registration"
        );

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
}