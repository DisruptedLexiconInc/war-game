package com.hive.wargame.angularjs.server.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface MissionRepository extends CrudRepository<Mission, Long> {

    Mission findByName(String name);

    List<Mission> findByEnergy(int energy);

}
