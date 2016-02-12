package com.hive.wargame.angularjs.server.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hive.wargame.angularjs.server.dao.Mission;
import com.hive.wargame.angularjs.server.service.MissionService;

@CrossOrigin
@RestController
@RequestMapping("/missions")
public class MissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MissionController.class);

    @Autowired
    private MissionService missionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Mission> getAllMissions() {
        return missionService.getAll();
    }

}
