package com.hive.wargame.angularjs.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = { "/login", "/login/" }, method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
    public @ResponseBody User getPlayer(@RequestBody User user) throws LoginException {
        return loginManager.getUser(user);
    }
}
