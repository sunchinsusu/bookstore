package com.ntt.service;

import com.ntt.entity.Item;
import com.ntt.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepo;

    public List<Item> createList(List<Item> items){
        return itemRepo.saveAll(items);
    }

    public Item create(Item item){
        return itemRepo.save(item);
    }

    public List<Item> getByCart_id(int id){
        return itemRepo.findByInvoice_Id(id);
    }
}
