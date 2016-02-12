package com.hive.wargame.angularjs.server.service.impls;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.wargame.angularjs.server.dao.Mission;
import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.exception.LoginException;
import com.hive.wargame.angularjs.server.service.MissionService;
import com.hive.wargame.angularjs.server.service.UserService;

@Service
public class InitializerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializerService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MissionService missionService;

    @PostConstruct
    public void init() throws LoginException {

        LOGGER.trace("Serive starting up.");

        List<User> users = userService.getAll();

        if (users.isEmpty()) {
            LOGGER.trace("There are no users in database. Adding mock users.");

            userService.add(new User("gsugambit", "gsugambit"));
            userService.add(new User("bludbaf", "bludbaf"));
            userService.add(new User("benlm", "benlm"));
        } else {
            LOGGER.trace("Database contains users. Will not add mock users.");
        }

        List<Mission> missions = missionService.getAll();

        if (missions.isEmpty()) {
            LOGGER.trace("There are no missions in database. Adding mock missions.");

            missionService.add(new Mission("Kickbox", 100));
            missionService.add(new Mission("Rob Bank", 200));
        } else {
            LOGGER.trace("Database contains users. Will not add mock users.");
        }
    }
}
