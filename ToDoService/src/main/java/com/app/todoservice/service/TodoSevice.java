package com.app.todoservice.service;

import com.app.todoservice.entity.Items;
import com.app.todoservice.model.ItemsDTO;

import java.util.List;

public interface TodoSevice {
    ItemsDTO findByTitle(String title);
    List<ItemsDTO> getAllToDoItems();
    String addNewItem(ItemsDTO newItem);
    String updateItem(ItemsDTO updateItem,int itemId);
    String deleteItemById(int id);
    ItemsDTO findById(int id);
}
