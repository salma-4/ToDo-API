package com.app.todoservice.repository;

import com.app.todoservice.entity.ItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDetailsRepo extends JpaRepository<ItemDetails, Long> {
}
