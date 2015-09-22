package com.hive.wargame.server.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Date dateJoined;

    @Column(nullable = false)
    private int level;

    private int hitPoints;

    private int attack;

    public Player() {
        this.dateJoined = new Date();
    }

    public Player(String username) {
        this.username = username;
        this.dateJoined = new Date();
        this.hitPoints = 1000;
        this.attack = 100;
    }

    public Player(String username, int hitPoints, int attack) {
        this.username = username;
        this.dateJoined = new Date();
        this.hitPoints = hitPoints;
        this.attack = attack;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
