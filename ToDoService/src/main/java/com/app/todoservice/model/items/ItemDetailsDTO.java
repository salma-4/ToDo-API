package com.app.todoservice.model.items;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailsDTO {
    private long id;
    private String description;

    private LocalDateTime createdAt;

    private int priority;

    private boolean status;
}
