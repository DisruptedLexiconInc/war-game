package com.hive.wargame.angularjs.server.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mision_id")
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int energy;

    @Column(nullable = false)
    private int lowCashRange;

    @Column(nullable = false)
    private int highCashRange;

    @Column(nullable = false)
    private int levelRequired;

    public Mission() {

    }

    public Mission(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    public Mission(String name, int energy, int lowCashRange, int highCashRange) {
        this.name = name;
        this.energy = energy;
        this.lowCashRange = lowCashRange;
        this.highCashRange = highCashRange;
    }

    public Mission(String name, int energy, int lowCashRange, int highCashRange, int levelRequired) {
        this.name = name;
        this.energy = energy;
        this.lowCashRange = lowCashRange;
        this.highCashRange = highCashRange;
        this.levelRequired = levelRequired;
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setCashRange(int lowCashRange, int highCashRange) {
        this.lowCashRange = lowCashRange;
        this.highCashRange = highCashRange;
    }

    public int getLowCashRange() {
        return lowCashRange;
    }

    public void setLowCashRange(int lowCashRange) {
        this.lowCashRange = lowCashRange;
    }

    public int getHighCashRange() {
        return highCashRange;
    }

    public void setHighCashRange(int highCashRange) {
        this.highCashRange = highCashRange;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
