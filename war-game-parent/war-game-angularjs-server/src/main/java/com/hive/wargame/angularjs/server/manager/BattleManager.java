package com.hive.wargame.angularjs.server.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.dao.UserRepository;

@Service
public class BattleManager {

    private UserRepository userRepository;

    public List<User> getOpponents(String username) {
        return userRepository.findTop100NotUsernameOrderByUsername(username);
    }
}
