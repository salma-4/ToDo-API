package com.app.todoservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items", schema = "todo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToOne
    @JoinColumn(name = "item_details_id")
    private ItemDetails itemDetails;

}
