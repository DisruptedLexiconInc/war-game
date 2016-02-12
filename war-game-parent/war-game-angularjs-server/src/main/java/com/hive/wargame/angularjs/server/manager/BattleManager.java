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

    public List<User> getOpponents(long id) {
        LOGGER.trace("Returning opponents for {}", id);

        if (id == 0) {
            return new ArrayList<User>();
        }

        Iterable<User> users = userRepository.findAll();

        Iterator<User> userIt = users.iterator();

        List<User> userList = new ArrayList<User>();

        while (userIt.hasNext()) {
            User user = userIt.next();

            if (id != user.getId()) {
                userList.add(user);
            }
        }

        return userList;
    }
}
