package com.app.todoservice.mapper;

import com.app.todoservice.entity.ItemDetails;
import com.app.todoservice.entity.Items;
import com.app.todoservice.model.ItemDetailsDTO;
import com.app.todoservice.model.ItemsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemsMap {
    Items toEntity(ItemsDTO itemsDTO);
   ItemsDTO toDto(Items items);

  List<ItemsDTO> toDto(List<Items> items);
  ItemDetails toEntity(ItemDetailsDTO itemDetailsDTO);

}
