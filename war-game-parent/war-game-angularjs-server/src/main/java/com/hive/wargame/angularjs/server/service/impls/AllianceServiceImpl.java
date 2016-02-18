package com.hive.wargame.angularjs.server.service.impls;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hive.wargame.angularjs.server.dao.Alliance;
import com.hive.wargame.angularjs.server.dao.AllianceRepository;
import com.hive.wargame.angularjs.server.service.AllianceService;

@Service(value = "allianceService")
public class AllianceServiceImpl implements AllianceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllianceServiceImpl.class);

    @Autowired
    private AllianceRepository allianceRepository;

    @Override
    public Alliance add(Alliance alliance) {
        if ((alliance == null) || (alliance.getName() == null)) {
            LOGGER.warn("alliance or name is null. Throwing exception.");
            throw new IllegalArgumentException("alliance nor alliance name can be null.");
        }

        return allianceRepository.save(alliance);
    }

    @Override
    public List<Alliance> getAll() {
        return Lists.newArrayList(allianceRepository.findAll());
    }
}
