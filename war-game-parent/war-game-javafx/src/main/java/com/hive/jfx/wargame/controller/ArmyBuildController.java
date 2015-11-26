package com.hive.jfx.wargame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hive.jfx.wargame.JFXApplication;
import com.hive.jfx.wargame.constants.Constants;
import com.hive.jfx.wargame.model.Army;
import com.hive.jfx.wargame.restclient.AuthenticationService;

@Component
public class ArmyBuildController {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ArmyBuildController.class);

    @FXML
    private TextField txtWarriorAmount;
    @FXML
    private TextField txtArcherAmount;
    @FXML
    private TextField txtTotalEnergy;
    @FXML
    private TextField txtAvailEnergy;
    @FXML
    private Button btnBuildArmy;

    private Stage dialogStage;

    // Reference to the main application
    private JFXApplication application;

    @FXML
    public void initialize() {
        txtWarriorAmount.setText("0");
        txtWarriorAmount.setOnKeyReleased(event -> {
            try {
                Integer.parseInt(txtWarriorAmount.getText());
                Integer.parseInt(txtArcherAmount.getText());

            } catch (Exception e) {
                txtWarriorAmount.setText("0");
                txtWarriorAmount.setTooltip(new Tooltip("Value must be integer."));
            }

            calculateTotalEnergy();
        });

        txtArcherAmount.setText("0");
        txtArcherAmount.setOnKeyReleased(event -> {
            try {
                Integer.parseInt(txtArcherAmount.getText());
                Integer.parseInt(txtWarriorAmount.getText());
            } catch (Exception e) {
                txtArcherAmount.setText("0");
                txtArcherAmount.setTooltip(new Tooltip("Value must be integer."));
            }

            calculateTotalEnergy();
        });

        btnBuildArmy.setOnAction(event -> {
            Army army = AuthenticationService.CURRENT_USER.getArmy();

            int actionEnergy = Integer.parseInt(txtTotalEnergy.getText());

            if (!txtTotalEnergy.getText().equals("0")) {

                if (army.getEnergy() >= actionEnergy) {
                    army.setNumOfArchers(army.getNumOfArchers() + Integer.parseInt(txtArcherAmount.getText()));
                    army.setNumOfWarriors(army.getNumOfWarriors() + Integer.parseInt(txtWarriorAmount.getText()));
                    army.setEnergy(army.getEnergy() - actionEnergy);
                    army.setDirty(true);
                } else {

                    LOGGER.warn("User tried to use {} energy but only has {}. Displayed error.", actionEnergy, army.getEnergy());
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Unable to Train");
                    alert.setHeaderText("You do not have enough energy to complete training.");
                    alert.setContentText("");
                    alert.showAndWait();
                }
            }

            application.showPlayer();
        });

        refreshUI();
    }

    @Scheduled(fixedDelayString = "${energy.refreshRate}")
    public void refreshUI() {
        if (AuthenticationService.CURRENT_USER != null) {
            Army army = AuthenticationService.CURRENT_USER.getArmy();

            txtAvailEnergy.setText(String.valueOf(army.getEnergy()));
        }
    }

    protected void calculateTotalEnergy() {
        txtTotalEnergy.setText(String.valueOf((Integer.parseInt(txtWarriorAmount.getText()) * Constants.WARRIOR_ENERGY_COST)
                        + (Integer.parseInt(txtArcherAmount.getText()) * Constants.ARCHER_ENERGY_COST)));
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
     *
     * Is called by the main application to give a reference back to itself.
     *
     * @param forceProClient
     */
    public void setForceProClient(JFXApplication application) {
        this.application = application;
    }
}
