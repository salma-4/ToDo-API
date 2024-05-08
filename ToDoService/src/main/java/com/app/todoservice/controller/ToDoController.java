package com.app.todoservice.controller;

import com.app.todoservice.model.items.ItemRequestDTO;
import com.app.todoservice.model.items.ItemResponseDTO;
import com.app.todoservice.service.TodoSevice;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "TODO API"
                ,version = "1.0"
                ,description = "Handle operations ^cruds^ on items"
        )
)
@RestController
@RequestMapping("/todo/item")
@RequiredArgsConstructor
public class ToDoController {

    private final TodoSevice todoSevice;

    @Operation(summary = "Find item by using its title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Item found")
            ,@ApiResponse(responseCode = "404",description = "Not found , no item with this title")
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<ItemResponseDTO> findByTitel(@PathVariable String title) {
        ItemResponseDTO item = todoSevice.findByTitle(title);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @Operation(summary = "Find item data  and its details by using ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Item found")
            ,@ApiResponse(responseCode = "404",description = "Not found , no item with this id")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<ItemRequestDTO> findById(@PathVariable long id) {

        ItemRequestDTO item = todoSevice.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @Operation(summary = "Take all your Items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Items found")
            ,@ApiResponse(responseCode = "404",description = "Not found , no todos :)")
    })
    @GetMapping("/items")
    public ResponseEntity<List<ItemResponseDTO>> getAll() {
        List<ItemResponseDTO> items = todoSevice.getAllToDoItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @Operation(summary = "Add new item and its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "CREATED ,item inserted")
    })
    @PostMapping()
    public ResponseEntity<String> addItem(@RequestBody ItemRequestDTO newItem) {
        System.out.println("item "+newItem);
        String message = todoSevice.addNewItem(newItem);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @Operation(summary = "Update item data  and its details by using ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Item updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , no item with this id")
    })
    @PutMapping("id/{id}/item")
    public ResponseEntity<String> updateItem(@RequestBody ItemRequestDTO itemResponseDTO, @PathVariable long id) {
        String message = todoSevice.updateItem(itemResponseDTO, id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @Operation(summary = "Delete item using ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Item deleted")
            ,@ApiResponse(responseCode = "404",description = "Not found , no item with this id")
    })
    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable long id) {
        String message = todoSevice.deleteItemById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @Operation(summary = "Activate item using ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Item activated")
            ,@ApiResponse(responseCode = "404",description = "Not found , no item with this id")
            ,@ApiResponse(responseCode = "409", description = "Item is already active ")
    })
    @GetMapping("/id/{id}/activated")
    public ResponseEntity<String> activateItem(@PathVariable long id) {
        String message = todoSevice.reactivateItem(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @Operation(summary = "Deactivate item using ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Item deactivated/deleted")
            ,@ApiResponse(responseCode = "404",description = "Not found , no item with this id")
            ,@ApiResponse(responseCode = "409", description = "Item was already deactivated before")
    })
    @GetMapping("/id/{id}/deactivated")
    public ResponseEntity<String> deactivateItem(@PathVariable long id) {
        String message = todoSevice.deactivateItem(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
