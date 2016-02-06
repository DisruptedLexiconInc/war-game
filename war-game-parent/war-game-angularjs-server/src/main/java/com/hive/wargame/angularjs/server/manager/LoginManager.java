package com.hive.wargame.angularjs.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.exception.LoginException;
import com.hive.wargame.angularjs.server.service.UserService;

@Service
public class LoginManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginManager.class);

    @Autowired
    private UserService userService;

    public User getUser(User user) throws LoginException {
        User possUser = userService.getUser(user);

        if (possUser != null) {
            return possUser;
        } else {
            return userService.add(user);
        }
    }
}
