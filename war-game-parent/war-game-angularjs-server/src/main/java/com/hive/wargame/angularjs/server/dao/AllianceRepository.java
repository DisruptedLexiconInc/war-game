package com.hive.wargame.angularjs.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AllianceRepository extends CrudRepository<Alliance, Long> {

    Alliance findByName(String name);
}
