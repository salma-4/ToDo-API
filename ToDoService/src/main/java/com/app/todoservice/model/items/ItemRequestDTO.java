package com.app.todoservice.model.items;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "How data in request")
public class ItemRequestDTO {
    private long id;
    private String title;
    @Schema(description = "details for each item")
    private ItemDetailsDTO itemDetails;

    public ItemRequestDTO(String title, ItemDetailsDTO itemDetails) {
        this.title = title;
        this.itemDetails = itemDetails;
    }
}
