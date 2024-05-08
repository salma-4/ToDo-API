package com.app.todoservice.model.items;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Extra information for a todo item")
public class ItemDetailsDTO {
    private long id;

    @Schema(description = "The description for item")
    private String description;

    private LocalDateTime createdAt;

    private int priority;

    @Schema(description = "Status of item if active or not ")
    private boolean status;
}
