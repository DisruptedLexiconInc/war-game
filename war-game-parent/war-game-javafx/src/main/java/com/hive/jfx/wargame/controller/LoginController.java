package com.hive.jfx.wargame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hive.jfx.wargame.JFXApplication;
import com.hive.jfx.wargame.restclient.AuthenticationService;

@Component
public class LoginController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label titleLabel;

    private Stage dialogStage;

    // Reference to the main application
    private JFXApplication application;

    @Value("${version}")
    private String version;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param forceProClient
     */
    public void setForceProClient(JFXApplication application) {
        this.application = application;
    }

    /**
     * Initializes the controller class. This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        titleLabel.setText("War Game " + version);
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleLogin() {
        try {

            if (isInputValid()) {

                LOGGER.debug("Attempting to validate login for {} with password {}", usernameField.getText(), passwordField.getText());
                authenticationService.authenticate(usernameField.getText(), passwordField.getText());

                if (AuthenticationService.CURRENT_USER != null) {
                    LOGGER.info("User logged in:\n{} ", AuthenticationService.CURRENT_USER.toString());

                    application.showPlayer();
                } else {
                    LOGGER.info("User could not be logged in. Displaying error.");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Invalid Login");
                    alert.setHeaderText("Please enter correct credentials");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            }
            // forceProClient.showProjectOverview();
            // forceProClient.getEventBus().post(new AuthenticationEvent(true));
            // } catch (AuthenticationException e) {
            // LOGGER.warn("Login failed for user: {}", usernameField.getText(), e);
            // // Show the error message.
            // Alert alert = new Alert(AlertType.ERROR);
            // alert.initOwner(dialogStage);
            // alert.setTitle("Invalid Login");
            // alert.setHeaderText("Please enter correct credentials");
            // alert.setContentText("");
            //
            // alert.showAndWait();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        System.exit(0);
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if ((usernameField.getText() == null) || (usernameField.getText().length() == 0)) {
            errorMessage += "No valid username!\n";
        }
        if ((passwordField.getText() == null) || (passwordField.getText().length() == 0)) {
            errorMessage += "No valid password!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
