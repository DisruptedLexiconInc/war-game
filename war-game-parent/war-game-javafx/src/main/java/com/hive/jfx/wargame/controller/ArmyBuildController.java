package com.hive.jfx.wargame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import org.slf4j.Logger;
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
    private Button btnBuildArmy;

    // Reference to the main application
    private JFXApplication application;

    @FXML
    public void initialize() {
        txtWarriorAmount.setText("0");
        txtWarriorAmount.setOnKeyReleased(event -> {
            try {
                int warriorAmount = Integer.parseInt(txtWarriorAmount.getText());
                int archerAmount = Integer.parseInt(txtArcherAmount.getText());

                txtTotalEnergy.setText(String.valueOf((warriorAmount * Constants.WARRIOR_ENERGY_COST) + (archerAmount * Constants.ARCHER_ENERGY_COST)));
            } catch (Exception e) {
                txtWarriorAmount.setText("0");
                txtWarriorAmount.setTooltip(new Tooltip("Value must be integer."));
            }

        });

        txtArcherAmount.setText("0");
        txtArcherAmount.setOnKeyReleased(event -> {
            try {
                int archerAmount = Integer.parseInt(txtArcherAmount.getText());
                int warriorAmount = Integer.parseInt(txtWarriorAmount.getText());

                txtTotalEnergy.setText(String.valueOf((warriorAmount * Constants.WARRIOR_ENERGY_COST) + (archerAmount * Constants.ARCHER_ENERGY_COST)));
            } catch (Exception e) {
                txtArcherAmount.setText("0");
                txtArcherAmount.setTooltip(new Tooltip("Value must be integer."));
            }
        });

        btnBuildArmy.setOnAction(event -> {
            Army army = AuthenticationService.CURRENT_USER.getArmy();

            if (!txtArcherAmount.getText().equals("0") && !txtWarriorAmount.getText().equals("0")) {

                army.setNumOfArchers(army.getNumOfArchers() + Integer.parseInt(txtArcherAmount.getText()));
                army.setNumOfWarriors(army.getNumOfWarriors() + Integer.parseInt(txtWarriorAmount.getText()));
                army.setDirty(true);
            }

            application.showPlayer();
        });

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
