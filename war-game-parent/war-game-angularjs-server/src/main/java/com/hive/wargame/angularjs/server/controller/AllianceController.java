package com.hive.wargame.angularjs.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hive.wargame.angularjs.server.dao.Alliance;
import com.hive.wargame.angularjs.server.service.AllianceService;

@CrossOrigin
@RestController
@RequestMapping("/alliances")
public class AllianceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllianceController.class);

    @Autowired
    private AllianceService allianceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Alliance> getAllAlliances() {
        return allianceService.getAll();
    }
}
