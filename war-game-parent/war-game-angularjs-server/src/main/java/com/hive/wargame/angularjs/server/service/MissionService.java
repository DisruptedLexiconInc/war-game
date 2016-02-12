package com.hive.wargame.angularjs.server.service;

import java.util.List;

import com.hive.wargame.angularjs.server.dao.Mission;

public interface MissionService {

    Mission add(Mission mission);

    List<Mission> getAll();
}