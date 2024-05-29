package com.example.shopservice.service;
import com.example.shopservice.model.Item;
import com.example.shopservice.model.ItemRepository;
import com.example.shopservice.model.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ShopService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getFirstItems(){
        List<Item> items = itemRepository.findTop10ByType(ItemType.PRODUCT);
        items.addAll(itemRepository.findTop10ByType(ItemType.EVENT));
        return items;
    }

    public List<Item> getItems(String name){
        if (name != null && !name.isEmpty()) {
            List<Item> items = itemRepository.findTop10ByItemNameContainingIgnoreCaseAndType(name,ItemType.PRODUCT);
            items.addAll(itemRepository.findTop10ByItemNameContainingIgnoreCaseAndType(name,ItemType.EVENT));
            return items;
        }else{
            return getFirstItems();
        }
    }
    public Boolean checkItemExits(String itemName) {
        return itemRepository.existsByItemName(itemName);
    }
    public void addNewItem(Item item) {
        itemRepository.save(item);
    }
}
