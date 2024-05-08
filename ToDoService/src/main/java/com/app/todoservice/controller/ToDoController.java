package com.app.todoservice.controller;

import com.app.todoservice.model.items.ItemRequestDTO;
import com.app.todoservice.model.items.ItemResponseDTO;
import com.app.todoservice.service.TodoSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/item")
@RequiredArgsConstructor
public class ToDoController {

    private final TodoSevice todoSevice;

    @GetMapping("/title/{title}")
    public ResponseEntity<ItemResponseDTO> findByTitel(@PathVariable String title) {
        ItemResponseDTO item = todoSevice.findByTitle(title);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ItemRequestDTO> findById(@PathVariable long id) {

        ItemRequestDTO item = todoSevice.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponseDTO>> getAll() {
        List<ItemResponseDTO> items = todoSevice.getAllToDoItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addItem(@RequestBody ItemRequestDTO newItem) {
        System.out.println("item "+newItem);
        String message = todoSevice.addNewItem(newItem);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("id/{id}/item")
    public ResponseEntity<String> updateItem(@RequestBody ItemRequestDTO itemResponseDTO, @PathVariable long id) {
        String message = todoSevice.updateItem(itemResponseDTO, id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable long id) {
        String message = todoSevice.deleteItemById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/id/{id}/avtivation")
    public ResponseEntity<String> activateItem(@PathVariable long id) {
        String message = todoSevice.reActiveItem(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
