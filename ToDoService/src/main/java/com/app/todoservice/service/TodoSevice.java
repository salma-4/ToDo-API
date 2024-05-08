package com.app.todoservice.service;

import com.app.todoservice.model.items.ItemRequestDTO;
import com.app.todoservice.model.items.ItemResponseDTO;

import java.util.List;

public interface TodoSevice {
    ItemResponseDTO findByTitle(String title);
    List<ItemResponseDTO> getAllToDoItems();
    String addNewItem(ItemRequestDTO newItem);
    String updateItem(ItemRequestDTO updateItem, long itemId);
    String deleteItemById(long id);
    ItemRequestDTO findById(long id);
    String reactivateItem(long id);
    String deactivateItem(long id);
}
