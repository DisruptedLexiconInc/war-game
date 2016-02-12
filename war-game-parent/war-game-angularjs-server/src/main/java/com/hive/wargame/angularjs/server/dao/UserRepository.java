package com.hive.wargame.angularjs.server.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsernameIgnoreCase(String username);

    User findByUsernameIgnoreCaseAndPassword(String username, String password);

}
