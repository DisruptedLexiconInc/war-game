package com.hive.wargame.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.hive.wargame.server.dao.Army;
import com.hive.wargame.server.dao.ArmyRepository;

@RequestMapping("/wargameServer/armyService")
@RestController
@Service
@Transactional
public class ArmyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArmyService.class);

    @Autowired
    private ArmyRepository armyRepository;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Army add(@RequestBody Army army) {
        return armyRepository.save(army);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Army> getArmies() {
        Iterable<Army> armyIt = armyRepository.findAll();

        return Lists.newArrayList(armyIt);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Army getArmy(@PathVariable long id) {

        return armyRepository.findOne(id);
    }
}
