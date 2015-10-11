package com.hive.wargame.server.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hive.wargame.server.constants.Rarity;

@RepositoryRestResource(exported = false)
public interface ArmyUnitRepository extends CrudRepository<ArmyUnit, Long> {

    ArmyUnit findByName(String name);

    List<ArmyUnit> findByRarity(Rarity rarity);
}
