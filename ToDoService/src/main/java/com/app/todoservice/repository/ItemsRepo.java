package com.app.todoservice.repository;

import com.app.todoservice.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemsRepo extends JpaRepository<Items, Long> {
    Optional<Items> findByTitle(String title);

}
