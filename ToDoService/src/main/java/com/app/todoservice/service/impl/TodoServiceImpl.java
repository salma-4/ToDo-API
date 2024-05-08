package com.app.todoservice.service.impl;

import com.app.todoservice.entity.ItemDetails;
import com.app.todoservice.entity.Items;
import com.app.todoservice.exception.ConflicException;
import com.app.todoservice.exception.ItemNotFoundException;
import com.app.todoservice.mapper.ItemsMap;
import com.app.todoservice.model.items.ItemRequestDTO;
import com.app.todoservice.model.items.ItemResponseDTO;
import com.app.todoservice.repository.ItemDetailsRepo;
import com.app.todoservice.repository.ItemsRepo;
import com.app.todoservice.service.TodoSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoSevice {

    private final ItemsRepo itemsRepo;
    private final ItemDetailsRepo itemDetailsRepo;
    private final ItemsMap map;

    @Override
    public ItemResponseDTO findByTitle(String title) {
        Items item = itemsRepo.findByTitle(title)
                .orElseThrow(() -> new ItemNotFoundException("There is no item with title :" + title));
        return map.toDto(item);
    }

    @Override
    public List<ItemResponseDTO> getAllToDoItems() {
        List<Items> items = itemsRepo.findAll();
        if (items.isEmpty())
            throw new ItemNotFoundException("No todos added");
        return map.toDto(items);
    }

    @Override
    public String addNewItem(ItemRequestDTO newItem) {
        Items items = map.toEntity(newItem);
        ItemDetails itemDetails = map.toEntity(newItem.getItemDetails());
        itemDetails.setCreatedAt(LocalDateTime.now());
        itemDetailsRepo.save(itemDetails);
        items.setItemDetails(itemDetails);
        itemsRepo.save(items);
        return "added successfully";
    }

    @Override
    public String updateItem(ItemRequestDTO updateItem, long id) {
        if (id > 0) {
            Items item = itemsRepo.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException("No item with id " + id));
            ItemDetails itemDetails = map.toEntity(updateItem.getItemDetails());
            item.setTitle(updateItem.getTitle());
            item.setItemDetails(itemDetails);
            itemDetailsRepo.save(itemDetails);
            itemsRepo.save(item);
            return "Item updated successfully";
        }
        throw new IllegalArgumentException("invalid id " + id);
    }

    @Override
    public String deleteItemById(long id) {
        if (id > 0) {
            Items item = itemsRepo.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException("No item with id " + id));
            ItemDetails itemDetails = item.getItemDetails();
            if (itemDetails.isStatus()) {
                itemDetails.setStatus(false);
                itemDetailsRepo.save(itemDetails);
                return "item deleted";
            } else
                throw new ConflicException("already not active");
        }
        throw new IllegalArgumentException("Invalid id " + id);
    }

    @Override
    public ItemRequestDTO findById(long id) {
        if (id > 0) {
            Items item = itemsRepo.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + id));
            return map.toRequestDTO(item);
        }
        throw new IllegalArgumentException("invalid id " + id);

    }

    @Override
    public String reActiveItem(long id) {
        if (id > 0) {
            Items item = itemsRepo.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException("No item with id " + id));
            ItemDetails itemDetails = item.getItemDetails();
            if (!itemDetails.isStatus()) {
                itemDetails.setStatus(true);
                itemDetailsRepo.save(itemDetails);
                return "activated...";
            } else
                throw new ConflicException("Item already active");
        }
        throw new IllegalArgumentException("Invalid id " + id);
    }
}
// Develope DB
// Cruds for items
//  Add a global exception handlers to handle exceptions (Not_Found Exception for example)
// TODO Use best practices in APIs design while designing these APIs
// TODO Document with swagger
// TODO Secure these apis with spring security by using JWT when calling these APIs
// TODO Make unit testing for these APIs using JUnit5
// TODO Make a backend validation on the attributes
// TODO Make a Postman Collection with the above APIs
