package com.hive.wargame.server.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hive.wargame.server.constants.ItemType;
import com.hive.wargame.server.constants.Rarity;

@RepositoryRestResource(exported = false)
public interface ItemRepository extends CrudRepository<Item, Long> {

    Item findByName(String name);

    List<Item> findByRarity(Rarity rarity);

    List<Item> findByItemType(ItemType itemType);

}
