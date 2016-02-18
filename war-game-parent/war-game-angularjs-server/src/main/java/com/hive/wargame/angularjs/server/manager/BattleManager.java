package com.hive.wargame.angularjs.server.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.dao.UserRepository;

@Service
public class BattleManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BattleManager.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getOpponents(User user) {
        LOGGER.trace("Returning opponents for {}", user.getId());

        if (user.getId() == 0) {
            return new ArrayList<User>();
        }

        Iterable<User> users = userRepository.findByLevel(user.getLevel());

        Iterator<User> userIt = users.iterator();

        List<User> opponentList = new ArrayList<User>();

        while (userIt.hasNext()) {
            User posUser = userIt.next();

            if (user.getId() != posUser.getId()) {
                opponentList.add(posUser);
            }
        }

        return opponentList;
    }
}
