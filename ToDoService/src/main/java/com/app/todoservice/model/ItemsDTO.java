package com.app.todoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDTO {
    private int id;
    private String title;
    //private UserDTO userDto;
    private ItemDetailsDTO itemsDetails;


}
