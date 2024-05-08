package com.app.todoservice.model.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    private long id;
    private String title;
    private ItemDetailsDTO itemDetails;
}
