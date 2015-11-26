package com.hive.jfx.wargame.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hive.jfx.wargame.JFXApplication;
import com.hive.jfx.wargame.exception.RemoteServiceException;
import com.hive.jfx.wargame.model.Army;
import com.hive.jfx.wargame.restclient.ArmyService;
import com.hive.jfx.wargame.restclient.AuthenticationService;

@Component
public class PlayerController {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PlayerController.class);

    @FXML
    private Label lblWelcome;
    @FXML
    private TextField txtArcher;
    @FXML
    private TextField txtWarrior;
    @FXML
    private TextField txtHero;
    @FXML
    private Button btnBuildWarrior;
    @FXML
    private Button btnBuildArcher;

    // Reference to the main application
    private JFXApplication application;

    private int heroLevel;

    @Autowired
    private ArmyService armyService;

    @FXML
    public void initialize() {
        lblWelcome.setText("Welcome " + AuthenticationService.CURRENT_USER.getUsername());

        btnBuildWarrior.setOnAction(event -> {
            Army army = AuthenticationService.CURRENT_USER.getArmy();

            army.setNumOfWarriors(army.getNumOfWarriors() + 1);
            army.setDirty(true);
            refreshUI();
        });

        btnBuildArcher.setOnAction(event -> {

            Army army = AuthenticationService.CURRENT_USER.getArmy();
            army.setNumOfArchers(army.getNumOfArchers() + 1);
            army.setDirty(true);
            refreshUI();
        });

        refreshUI();
    }

    public void refreshUI() {

        Army army = AuthenticationService.CURRENT_USER.getArmy();

        txtWarrior.setText(String.valueOf(army.getNumOfWarriors()));
        txtArcher.setText(String.valueOf(army.getNumOfArchers()));
        txtHero.setText(String.valueOf(heroLevel));
    }

    @Scheduled(fixedRate = 1000)
    protected void saveArmy() {

        // we only run if user is logged in and army has changed
        if (AuthenticationService.CURRENT_USER != null) {

            Army army = AuthenticationService.CURRENT_USER.getArmy();

            if (army.isDirty()) {

                LOGGER.trace("Running autosave for army on {}. \n{}", AuthenticationService.CURRENT_USER.getUsername(), army.toString());

                try {
                    // automatically sets dirty to false because it is not in server side code
                    AuthenticationService.CURRENT_USER.setArmy(armyService.save(army));
                } catch (RemoteServiceException e) {
                    LOGGER.warn("Error trying to autosave army:" + e.getMessage(), e);
                }
            } else {
                // we do this in case the user has another client and changes the armies. forces sync no matter how many clients
                try {

                    LOGGER.trace("Updating army to be latest from database for {}. \n {}", AuthenticationService.CURRENT_USER.getUsername(),
                                    AuthenticationService.CURRENT_USER.getArmy().toString());

                    AuthenticationService.CURRENT_USER.setArmy(armyService.get(army.getId()));

                    refreshUI();
                } catch (RemoteServiceException e) {
                    LOGGER.warn("Error trying to autosave army:" + e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param forceProClient
     */
    public void setForceProClient(JFXApplication application) {
        this.application = application;
    }
}
