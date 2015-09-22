package com.hive.wargame.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hive.wargame.server.dao.Player;

@RestController
@RequestMapping("/battle")
public class BattleService {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    private Player doBattle(@RequestBody List<Player> playerList) {
        Player p1 = playerList.get(0);
        Player p2 = playerList.get(1);

        // TODO: Implement battle logic

        return p1;
    }
}
