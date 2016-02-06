package com.hive.wargame.angularjs.server.service;

import java.util.List;

import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.exception.LoginException;

public interface UserService {

    /**
     * Get all users from database
     *
     * @return - all users
     */
    List<User> getAll();

    /**
     * Attempts to get user from database (adds user if they do not exist)
     *
     * @param user
     *            - object with username and password of user attempting login
     * @return - user DAO or exception if unsuccessful
     * @throws LoginException
     *             - thrown if parameters or null or password is wrong
     */
    User getUser(User user) throws LoginException;

    /**
     * Adds user if does not exist
     *
     * @param user
     *            - user to potentially add
     * @return - new user object if updated
     * @throws LoginException
     */
    User add(User user) throws LoginException;
}