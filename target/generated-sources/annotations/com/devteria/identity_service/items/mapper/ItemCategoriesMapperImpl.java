package com.devteria.identity_service.items.mapper;

import com.devteria.identity_service.common.enumerate.ItemStatus;
import com.devteria.identity_service.items.dto.ItemCategoriesUpdateReq;
import com.devteria.identity_service.items.entity.ItemCategories;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-03T15:41:07+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ItemCategoriesMapperImpl implements ItemCategoriesMapper {

    @Override
    public void updateItemCategories(ItemCategoriesUpdateReq request, ItemCategories itemCategoriesEntity) {
        if ( request == null ) {
            return;
        }

        if ( request.getCode() != null ) {
            itemCategoriesEntity.setCode( request.getCode() );
        }
        if ( request.getName() != null ) {
            itemCategoriesEntity.setName( request.getName() );
        }
        if ( request.getStatus() != null ) {
            itemCategoriesEntity.setStatus( Enum.valueOf( ItemStatus.class, request.getStatus() ) );
        }
        if ( request.getIsDelete() != null ) {
            itemCategoriesEntity.setIsDelete( request.getIsDelete() );
        }
    }
}
