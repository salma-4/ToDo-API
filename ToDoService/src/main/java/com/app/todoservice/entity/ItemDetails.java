package com.app.todoservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;




    @Entity
    @Table(name = "Items_details", schema = "todo")
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class ItemDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "description")
        private String description;

        @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private Timestamp createdAt;

        @Column(name = "priority")
        private int priority;

        @Column(name = "status", length = 50)
        private String status;
    }
