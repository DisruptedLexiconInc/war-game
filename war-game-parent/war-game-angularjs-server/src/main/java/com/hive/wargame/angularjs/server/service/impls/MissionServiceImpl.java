package com.hive.wargame.angularjs.server.service.impls;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hive.wargame.angularjs.server.dao.Mission;
import com.hive.wargame.angularjs.server.dao.MissionRepository;
import com.hive.wargame.angularjs.server.service.MissionService;

@Service(value = "missionService")
public class MissionServiceImpl implements MissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MissionServiceImpl.class);

    @Autowired
    private MissionRepository missionRepository;

    @Override
    public Mission add(Mission mission) {
        if ((mission == null) || (mission.getName() == null)) {
            LOGGER.debug("Receiving a null mission: {} or mission-name: {}", mission == null, (mission == null) ? "null" : mission.getName());
            return null;
        }

        return missionRepository.save(mission);
    }

    @Override
    public List<Mission> getAll() {
        return Lists.newArrayList(missionRepository.findAll());
    }
}
