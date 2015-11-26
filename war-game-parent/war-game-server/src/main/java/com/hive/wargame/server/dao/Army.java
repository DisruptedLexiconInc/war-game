package com.hive.wargame.server.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Army {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int numOfArchers;

    private int numOfWarriors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumOfArchers() {
        return numOfArchers;
    }

    public void setNumOfArchers(int numOfArchers) {
        this.numOfArchers = numOfArchers;
    }

    public int getNumOfWarriors() {
        return numOfWarriors;
    }

    public void setNumOfWarriors(int numOfWarriors) {
        this.numOfWarriors = numOfWarriors;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
