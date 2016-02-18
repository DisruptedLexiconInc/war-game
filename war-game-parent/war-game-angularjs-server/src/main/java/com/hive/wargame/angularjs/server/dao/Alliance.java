package com.hive.wargame.angularjs.server.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alliances")
public class Alliance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alliance_id")
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "alliance", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    public Alliance() {

    }

    public Alliance(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Alliance(String name, String description, Set<User> users) {
        this();
        this.name = name;
        this.description = description;
        this.users = users;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
