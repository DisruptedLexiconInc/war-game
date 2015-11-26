package com.hive.jfx.wargame.model;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Player {

    private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

    private long id;

    private String name;

    private Date dateJoined;

    private int serverId;

    private int level;

    public Player() {
        dateJoined = new Date();
    }

    public Player(String username) {
        dateJoined = new Date();
    }

    public Player(String name, int serverId, int level) {
        this();
        this.name = name;
        this.serverId = serverId;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Error creating json to string. Returns super.toString()", e);
            return super.toString();
        }
    }
}
