package com.app.todoservice.mapper;

import com.app.todoservice.entity.ItemDetails;
import com.app.todoservice.entity.Items;
import com.app.todoservice.model.items.ItemDetailsDTO;
import com.app.todoservice.model.items.ItemRequestDTO;
import com.app.todoservice.model.items.ItemResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemsMap {
    Items toEntity(ItemRequestDTO item);

    ItemResponseDTO toDto(Items items);

    List<ItemResponseDTO> toDto(List<Items> items);

    ItemDetails toEntity(ItemDetailsDTO itemDetailsDTO);
    ItemRequestDTO toRequestDTO(Items item);

}
