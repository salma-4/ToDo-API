package com.app.todoservice.controller;

import com.app.todoservice.model.ItemsDTO;
import com.app.todoservice.service.TodoSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/item")
public class ToDoController {
    @Autowired
    TodoSevice todoSevice;
    @GetMapping("/itemByTitle/{title}")
    public ItemsDTO findByTitel(@PathVariable String title){
        return todoSevice.findByTitle(title);
    }
    @GetMapping("/itemById/{id}")
    public ItemsDTO findById(@PathVariable int id){
      return todoSevice.findById(id);
    }
    @GetMapping("/items")
    public List<ItemsDTO> getAll(){
        return todoSevice.getAllToDoItems();
    }
    @PostMapping()
    public String addItem(@RequestBody ItemsDTO newItem) {
      //  System.out.println(newItem.getItemsDetails().getDescription());
        return todoSevice.addNewItem(newItem);
    }
    @PutMapping("/{id}")
    public String updateItem(@RequestBody ItemsDTO itemsDTO, @PathVariable int id){
        return todoSevice.updateItem(itemsDTO,id);
    }
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id){
        return todoSevice.deleteItemById(id);
    }
}
