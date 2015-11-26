package com.hive.jfx.wargame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Army {

    private long id;

    private int numOfArchers;

    private int numOfWarriors;

    private int energy;

    @JsonIgnore
    private boolean dirty;

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

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        if (energy > 100) {
            energy = 100;
        } else if (energy < 0) {
            energy = 0;
        } else {
            this.energy = energy;
        }
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
