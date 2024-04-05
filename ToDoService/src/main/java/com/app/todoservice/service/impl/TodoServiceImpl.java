package com.app.todoservice.service.impl;

import com.app.todoservice.entity.ItemDetails;
import com.app.todoservice.entity.Items;
import com.app.todoservice.mapper.ItemsMap;
import com.app.todoservice.model.ItemsDTO;
import com.app.todoservice.repository.ItemDetailsRepo;
import com.app.todoservice.repository.ItemsRepo;
import com.app.todoservice.service.TodoSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoSevice {

    @Autowired
    ItemsRepo itemsRepo;
    @Autowired
    ItemDetailsRepo itemDetailsRepo;
@Autowired
    ItemsMap map;
    @Override
    public ItemsDTO findByTitle(String title) {
        Items items = itemsRepo.findByTitle(title);
             if (items !=null) {
            return map.toDto(items);
           } else {
            throw new RuntimeException("Item not found with title: " + title);
        }

    }

    @Override
    public List<ItemsDTO> getAllToDoItems() {
        List<Items> items = itemsRepo.findAll();

        return map.toDto(items);
    }

    @Override
    public String addNewItem(ItemsDTO newItem) {
        Items items = map.toEntity(newItem);
        ItemDetails itemDetails = items.getItemsDetails();
        itemDetailsRepo.save(itemDetails);
        items.setItemsDetails(itemDetails);


        itemsRepo.save(items);

        return "added successfully";
    }

    @Override
    public String updateItem(ItemsDTO updateItem, int itemId) {
        Optional<Items> optionalItem = itemsRepo.findById(itemId);
        if (optionalItem.isPresent()) {
            Items existingItem = optionalItem.get();
            ItemDetails itemDetails=map.toEntity(updateItem.getItemsDetails());
            existingItem.setTitle(updateItem.getTitle());
            existingItem.setItemsDetails(itemDetails);
            itemDetailsRepo.save(itemDetails);
            itemsRepo.save(existingItem);
            return "Item updated successfully";
        } else {
            throw new RuntimeException("Item not found with ID: " + itemId);
        }
    }

    @Override
    public String deleteItemById(int id) {
        Optional<Items> optionalItem = itemsRepo.findById(id);
        if (optionalItem.isPresent()) {
            Items item = optionalItem.get();
            itemsRepo.deleteById(id);
            itemDetailsRepo.deleteById(item.getItemsDetails().getId());
            return "Item deleted successfully";
        } else {
            throw new RuntimeException("Item not found with ID: " + id);
        }
    }

    @Override
    public ItemsDTO findById(int id) {
        Optional<Items> itemsOptional = itemsRepo.findById(id);
        if (itemsOptional.isPresent()) {
            Items items = itemsOptional.get();
            return map.toDto(items);
        } else {
            throw new RuntimeException("Item not found with ID: " + id);
        }
    }
}
// Develope DB
// Cruds for items
// TODO Add a global exception handlers to handle exceptions (Not_Found Exception for example)
// TODO Use best practices in APIs design while designing these APIs
// TODO Document with swagger
// TODO Secure these apis with spring security by using JWT when calling these APIs
// TODO Make unit testing for these APIs using JUnit5
// TODO Make a backend validation on the attributes
// TODO Make a Postman Collection with the above APIs
