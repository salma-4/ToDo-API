package com.app.todoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;




@Data
@AllArgsConstructor
@NoArgsConstructor
    public class ItemDetailsDTO {


        private int id;
        private String description;
        private Timestamp createdAt;
        private int priority;
        private String status;
    }
