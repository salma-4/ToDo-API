package com.app.todoservice.repository;

import com.app.todoservice.entity.ItemDetails;
import com.app.todoservice.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDetailsRepo extends JpaRepository<ItemDetails, Integer> {
}
