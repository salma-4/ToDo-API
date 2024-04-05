package com.app.todoservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items",schema = "todo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToOne
    @JoinColumn(name = "item_details_id")
    private ItemDetails itemsDetails;

}
