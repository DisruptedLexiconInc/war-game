package com.hive.jfx.wargame.restclient;

import java.util.List;

import javafx.collections.ObservableList;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hive.jfx.wargame.exception.RemoteServiceException;
import com.hive.jfx.wargame.model.User;
import com.hive.jfx.wargame.restclient.util.JsonTaskFactory;

@Service(value = "authenticationServiceClient")
public class AuthenticationService {

    public static User CURRENT_USER = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private JsonTaskFactory taskFactory;

    public void authenticate(String username, String password) throws RemoteServiceException {

        LOGGER.debug("Posting login attempt to server from {} with password: {}", username, password);

        ObservableList<User> userObservable = taskFactory.get(new GenericType<List<User>>() {
        }, "/authenticationManager/authenticate", username, password);

        if (userObservable.size() > 0) {
            CURRENT_USER = userObservable.get(0);
        } else {
            LOGGER.debug("No user was logged in.");
        }
    }
}

class Credential {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public Credential() {

    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}