package com.hive.wargame.server.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    public List<Player> findByUsername(String username);
}
