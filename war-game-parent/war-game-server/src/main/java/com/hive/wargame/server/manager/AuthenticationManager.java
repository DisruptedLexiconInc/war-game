package com.hive.wargame.server.manager;

import java.util.List;

import com.hive.wargame.server.dao.User;

/**
 * Manager for all authentications
 *
 * @author gsuga_000
 *
 */
public interface AuthenticationManager {

    /**
     *
     * @param username
     *            - username of user
     * @param password
     *            - password of user
     * @return - user that has logged in
     */
    public List<User> authenticate(String username, String password);
}