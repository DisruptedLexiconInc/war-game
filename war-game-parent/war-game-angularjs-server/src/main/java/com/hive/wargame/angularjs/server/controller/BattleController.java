package com.hive.wargame.angularjs.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.manager.BattleManager;

@CrossOrigin
@RestController
@RequestMapping("/battles")
public class BattleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BattleController.class);

    @Autowired
    private BattleManager battleManager;

    @RequestMapping(value = { "/opponent", "/opponent/" }, method = { RequestMethod.POST, RequestMethod.OPTIONS })
    public @ResponseBody List<User> getOpponents(@RequestBody User user) {
        return battleManager.getOpponents(user);
    }
}
