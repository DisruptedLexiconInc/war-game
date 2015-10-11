package com.hive.wargame.server.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hive.wargame.server.constants.Rarity;

@RepositoryRestResource(exported = false)
public interface HeroUnitRepository extends CrudRepository<HeroUnit, Long> {

    HeroUnit findByName(String name);

    List<HeroUnit> findByRarity(Rarity rarity);
}
