package com.app.todoservice.model.items;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "How data appear in search")
public class ItemResponseDTO {
    private long id;
    private String title;
}
