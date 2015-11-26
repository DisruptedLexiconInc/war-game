package com.hive.jfx.wargame.restclient;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.jfx.wargame.exception.RemoteServiceException;
import com.hive.jfx.wargame.model.Army;
import com.hive.jfx.wargame.restclient.util.JsonTaskFactory;

@Service(value = "armyServiceClient")
public class ArmyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArmyService.class);

    @Autowired
    private JsonTaskFactory taskFactory;

    public Army save(Army army) throws RemoteServiceException {
        return taskFactory.post(army, new GenericType<Army>() {
        }, "/armyService/update", new String[0]);
    }

    public Army get(long id) throws RemoteServiceException {
        return taskFactory.getOne(new GenericType<Army>() {
        }, "/armyService/", String.valueOf(id));
    }
}
