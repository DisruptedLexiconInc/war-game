package com.hive.jfx.wargame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hive.jfx.wargame.controller.ArmyBuildController;
import com.hive.jfx.wargame.controller.LoginController;
import com.hive.jfx.wargame.controller.PlayerController;
import com.hive.jfx.wargame.spring.SpringFxmlLoader;

@SpringBootApplication
@ComponentScan(basePackages = { "com.hive.jfx.wargame" })
@EnableScheduling
public class JFXApplication extends Application {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JFXApplication.class);
    private static ApplicationContext applicationContext;

    @Autowired
    SpringFxmlLoader fxmlLoader;
    private Stage primaryStage;
    private BorderPane borderPane;

    @Value("${version}")
    private String version;

    public static void main(String[] args) {
        String javaVersion = System.getProperty("java.version");

        // float f = Float.valueOf(javaVersion);
        // JOptionPane.showMessageDialog(null, "" + f);

        LOGGER.info("Client java version is: {}", javaVersion);
        double minorVersion = Double.parseDouble(javaVersion.substring(0, 3));
        int update = Integer.parseInt(javaVersion.substring(javaVersion.indexOf("_") + 1));
        if ((minorVersion < 1.8) || (update < 45)) {
            LOGGER.warn("Client is not running Java 1.8.0 Update 45 or above. Closing.");
            JOptionPane.showMessageDialog(null, String.format(
                            "The minimum JAVA requirement for WarGame is JAVA 1.8.0 Update 45. You only have %s. Please update your JAVA", javaVersion),
                            "Required JAVA Version", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {
            LOGGER.trace("Client running acceptable java version. Loading application.");
            applicationContext = SpringApplication.run(JFXApplication.class, args);
            Package pkg = Package.getPackage("com.hive.jfx.wargame");
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("War Game " + version);

        this.primaryStage.setOnCloseRequest(we -> {
            LOGGER.debug("Stage is closing.");
            SpringApplication.exit(applicationContext, () -> 0);
        });

        showLogin();
    }

    /**
     * Shows the login screen inside the root layout
     */
    public void showLogin() {
        try {
            primaryStage.toFront();

            // Load person overview.
            AnchorPane login = fxmlLoader.load("view/LoginView.fxml");

            Scene scene = new Scene(login);
            primaryStage.setScene(scene);

            primaryStage.show();

            // Give the controller access to the main app.
            LoginController controller = fxmlLoader.getController();
            controller.setForceProClient(this);

        } catch (Exception e) {
            LOGGER.error("Could not load login", e);
        }
    }

    public void showPlayer() {
        try {
            primaryStage.toFront();

            AnchorPane player = fxmlLoader.load("view/PlayerView.fxml");

            Scene scene = new Scene(player);
            primaryStage.setScene(scene);

            primaryStage.show();

            // Give the controller access to the main app.
            PlayerController controller = fxmlLoader.getController();
            controller.setForceProClient(this);
        } catch (Exception e) {
            LOGGER.error("Could not load PlayerView", e);
        }
    }

    public void showArmyBuild() {
        try {
            primaryStage.toFront();

            AnchorPane armyBuild = fxmlLoader.load("view/ArmyBuildView.fxml");

            Scene scene = new Scene(armyBuild);
            primaryStage.setScene(scene);

            primaryStage.show();

            // Give the controller access to the main app.
            ArmyBuildController controller = fxmlLoader.getController();
            controller.setForceProClient(this);
            controller.setDialogStage(primaryStage);
        } catch (Exception e) {
            LOGGER.error("Could not load ArmyBuildView", e);
        }
    }
}
