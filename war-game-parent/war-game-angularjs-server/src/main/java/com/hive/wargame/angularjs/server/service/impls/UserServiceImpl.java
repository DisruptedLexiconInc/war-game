package com.hive.wargame.angularjs.server.service.impls;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hive.wargame.angularjs.server.dao.User;
import com.hive.wargame.angularjs.server.dao.UserRepository;
import com.hive.wargame.angularjs.server.exception.LoginException;
import com.hive.wargame.angularjs.server.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public User getUser(User user) throws LoginException {
        // we ensure nothing is null
        if ((user == null) || (user.getUsername() == null) || (user.getPassword() == null)) {
            LOGGER.warn("User object is null.");
            throw new LoginException("Null username or password.");
        } else {
            LOGGER.trace("{}", user.toString());
            String username = user.getUsername();
            String password = MD5(user.getPassword());

            User possUser = repository.findByUsernameIgnoreCase(username);

            if (possUser != null) {

                if (possUser.getPassword().equals(password)) {
                    LOGGER.trace("User entered correct password: {}", username);
                    return possUser;
                } else {
                    throw new LoginException("Incorrect password");
                }
            } else {
                LOGGER.trace("Could not find user: {}", username);
                return null;
            }
        }
    }

    @Override
    public User add(User user) throws LoginException {
        // we ensure nothing is null
        if ((user == null) || (user.getUsername() == null) || (user.getPassword() == null)) {
            LOGGER.warn("User object is null.");
            throw new LoginException("Null username or password.");
        } else {

            String username = user.getUsername();
            String password = MD5(user.getPassword());

            User possUser = repository.findByUsernameIgnoreCaseAndPassword(username, password);

            if (possUser != null) {
                LOGGER.trace("Retuning instead of adding duplicate for user: {}", username);
                return possUser;
            } else {
                LOGGER.trace("Creating user: {}", username);
                user.setPassword(password);
                return repository.save(user);
            }
        }
    }

    protected String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}