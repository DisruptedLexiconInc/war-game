package com.hive.wargame.angularjs.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.exception.LoginException;
import com.hive.wargame.angularjs.server.manager.LoginManager;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginManager loginManager;

    @RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
    public @ResponseBody User getPlayer(@RequestParam("password") String password, @RequestParam("username") String username) throws LoginException {
        return loginManager.getUser(new User(username, password));
    }
}
