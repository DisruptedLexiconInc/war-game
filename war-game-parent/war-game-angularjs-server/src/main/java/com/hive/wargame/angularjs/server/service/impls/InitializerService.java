package com.hive.wargame.angularjs.server.service.impls;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.wargame.angularjs.server.dao.Alliance;
import com.hive.wargame.angularjs.server.dao.Mission;
import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.exception.LoginException;
import com.hive.wargame.angularjs.server.service.AllianceService;
import com.hive.wargame.angularjs.server.service.MissionService;
import com.hive.wargame.angularjs.server.service.UserService;

@Service
public class InitializerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializerService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MissionService missionService;

    @Autowired
    private AllianceService allianceService;

    @PostConstruct
    public void init() throws LoginException {

        LOGGER.trace("Serive starting up.");

        List<User> users = userService.getAll();

        if (users.isEmpty()) {
            LOGGER.trace("There are no users in database. Adding mock users.");

            userService.add(new User("gsugambit", "gsugambit", 0, 0, 1000, 100));
            userService.add(new User("bludbaf", "bludbaf", 0, 0, 1000, 100));
            userService.add(new User("benlm", "benlm", 0, 0, 1000, 100));
            userService.add(new User("alex", "alex", 0, 0, 1000, 100));

            String level = "level";
            String currUser;
            for (int i = 0; i < 51; i++) {
                currUser = level + i;
                userService.add(new User(currUser, currUser, i, i * 50, i, 100));
            }
        } else {
            LOGGER.trace("Database contains users. Will not add mock users.");
        }

        List<Mission> missions = missionService.getAll();

        if (missions.isEmpty()) {
            LOGGER.trace("There are no missions in database. Adding mock missions.");

            missionService.add(new Mission("Kickbox", 100, 10, 200, 0));
            missionService.add(new Mission("Rob Bank", 200, 15, 400, 1));
        } else {
            LOGGER.trace("Database contains users. Will not add mock users.");
        }

        List<Alliance> alliances = allianceService.getAll();

        if (alliances.isEmpty()) {
            LOGGER.trace("There are no alliances in database. Adding mock alliances.");

            allianceService.add(new Alliance("The Bears", "Interesting club for bears"));
            allianceService.add(new Alliance("The Cats", "Interesting club for cats"));
        } else {
            LOGGER.trace("Database contains alliances. Will not add mock alliances.");
        }
    }
}
