package com.example.shopservice.model;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

    List <Item> findTop10ByType(ItemType type);
    List <Item> findTop10ByItemNameContainingIgnoreCaseAndType(String itemName, ItemType type);
    boolean existsByItemName(String itemName);
}

