package com.devteria.identity_service.items.mapper;


import com.devteria.identity_service.items.dto.ItemCategoriesUpdateReq;
import com.devteria.identity_service.items.entity.ItemCategories;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ItemCategoriesMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemCategories(ItemCategoriesUpdateReq request, @MappingTarget ItemCategories itemCategoriesEntity);
}
