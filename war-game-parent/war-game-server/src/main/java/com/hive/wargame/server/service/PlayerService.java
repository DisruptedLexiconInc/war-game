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
import com.hive.wargame.server.dao.Player;
import com.hive.wargame.server.dao.PlayerRepository;

@RequestMapping("/wargameServer/player")
@RestController
@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/add/{username}", method = RequestMethod.PUT)
    public void add(@PathVariable String username) {
        playerRepository.save(new Player(username));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Player> getAll() {

        Iterable<Player> playerIteratable = playerRepository.findAll();

        return Lists.newArrayList(playerIteratable);
    }
}
