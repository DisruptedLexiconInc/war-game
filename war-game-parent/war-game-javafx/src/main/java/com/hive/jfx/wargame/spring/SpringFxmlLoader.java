package com.hive.jfx.wargame.spring;

import java.net.URL;

import javafx.fxml.FXMLLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hive.jfx.wargame.exception.FXMLLoadException;

@Component
public class SpringFxmlLoader {
    private static final Logger logger = LoggerFactory.getLogger(SpringFxmlLoader.class);

    @Autowired
    ApplicationContext context;

    private FXMLLoader loader;

    public <T> T load(String url) throws FXMLLoadException {
        try {
            URL theUrl = Thread.currentThread().getContextClassLoader().getResource(url);
            loader = new FXMLLoader();
            loader.setLocation(theUrl);
            loader.setControllerFactory(aClass -> context.getBean(aClass));
            return loader.load();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new FXMLLoadException(String.format("Failed to load FXML file '%s'", url));
        }
    }

    public <T> T getController() {
        return loader.getController();
    }

}
