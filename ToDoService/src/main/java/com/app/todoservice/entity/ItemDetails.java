package com.app.todoservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


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
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "priority")
    private int priority;

    @Column(name = "status", nullable = false)
    private boolean status;
}
