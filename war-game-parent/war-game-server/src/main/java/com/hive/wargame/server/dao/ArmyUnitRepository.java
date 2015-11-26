package com.hive.wargame.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ArmyUnitRepository extends CrudRepository<ArmyUnit, Long> {

    ArmyUnit findByName(String name);
}
