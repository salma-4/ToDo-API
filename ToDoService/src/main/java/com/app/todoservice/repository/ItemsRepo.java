package com.app.todoservice.repository;

import com.app.todoservice.entity.Items;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepo extends JpaRepository<Items, Integer> {
    Items findByTitle(String title);

}
