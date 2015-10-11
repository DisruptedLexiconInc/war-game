package com.hive.wargame.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

    public User findByInfoEmailAddress(String emailAddress);
}
