package com.hive.wargame.server.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "serverId", "name" }) )
@Entity
public class Player {

    private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private Date dateJoined;

    @Column(nullable = false)
    private int serverId;

    @Column(nullable = false)
    private int level;

    public Player() {
        this.dateJoined = new Date();
    }

    public Player(String username) {
        this.dateJoined = new Date();
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
