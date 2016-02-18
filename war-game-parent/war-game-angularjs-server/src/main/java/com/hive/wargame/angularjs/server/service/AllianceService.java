package com.hive.wargame.angularjs.server.service;

import java.util.List;

import com.hive.wargame.angularjs.server.dao.Alliance;

public interface AllianceService {

    public abstract Alliance add(Alliance alliance);

    public abstract List<Alliance> getAll();

}