package com.hive.wargame.server.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hive.wargame.server.dao.User;
import com.hive.wargame.server.manager.AuthenticationManager;
import com.hive.wargame.server.service.AuthenticationService;

@RestController
@RequestMapping("/wargameServer/authenticationManager")
public class AuthenticationManagerImpl implements AuthenticationManager {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AuthenticationManagerImpl.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    @RequestMapping("/authenticate/{username}/{password}")
    public List<User> authenticate(@PathVariable String username, @PathVariable String password) {
        LOGGER.debug("Login attempt from {} with password: {}", username, password);
        return authenticationService.validateLogin(username, password);
    }
}