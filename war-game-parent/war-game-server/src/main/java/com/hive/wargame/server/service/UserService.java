package com.hive.wargame.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.hive.wargame.server.dao.User;
import com.hive.wargame.server.dao.UserRepository;

@RequestMapping("/user")
@RestController
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public User add(User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(User user) {
        userRepository.delete(user);
    }

    @RequestMapping(value = "/deletelist", method = RequestMethod.DELETE)
    public void deleteList(List<User> user) {
        userRepository.delete(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") long id) {
        userRepository.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        Iterable<User> usersIt = userRepository.findAll();

        return Lists.newArrayList(usersIt);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") long id) {
        return userRepository.findOne(id);
    }
}
