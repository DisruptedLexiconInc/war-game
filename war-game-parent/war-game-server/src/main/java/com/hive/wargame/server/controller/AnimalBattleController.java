package com.hive.wargame.server.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hive.wargame.server.manager.impl.AuthenticationManagerImpl;

@RestController
@RequestMapping("/")
public class AnimalBattleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManagerImpl.class);

    @PostConstruct
    public void init() {
        LOGGER.debug("Example: {}", new Credential("exUsername", "exPassword").toString());
    }

    @RequestMapping(value = { "/player", "/player/" }, method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody String getPlayer(@RequestBody(required = false) Credential cred) {
        if (cred == null) {
            LOGGER.warn("cred was null");
            return "Ben";
        } else {
            LOGGER.debug("Received cred: {}", cred);
            return cred.toString();
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Credential {

    private String username;
    private String password;

    public Credential() {

    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}