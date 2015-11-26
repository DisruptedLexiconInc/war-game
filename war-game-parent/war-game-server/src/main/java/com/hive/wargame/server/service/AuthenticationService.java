package com.hive.wargame.server.service;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hive.wargame.server.dao.User;
import com.hive.wargame.server.dao.UserRepository;

@RestController
@RequestMapping("/wargameServer/authenticationService")
public class AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> validateLogin(String username, String password) {

        List<User> result = new ArrayList<User>();

        User possibleuser = userRepository.findByUsername(username);

        if (possibleuser == null) {
            LOGGER.warn("Username does not exist in database: {}", username);
        } else {
            if (possibleuser.getPassword().equals(password)) {
                LOGGER.trace("Correct username and password for: {}", username);
                result.add(possibleuser);
            } else {
                LOGGER.warn("Wrong password entered for user: {}", username);
            }
        }

        return result;
    }
}
