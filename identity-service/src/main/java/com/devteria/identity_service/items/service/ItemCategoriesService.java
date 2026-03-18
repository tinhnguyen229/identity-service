package com.devteria.identity_service.items.service;

import com.devteria.identity_service.common.enumerate.ItemStatus;
import com.devteria.identity_service.items.dto.ItemCategoriesCreateReq;
import com.devteria.identity_service.items.entity.ItemCategories;
import com.devteria.identity_service.items.repository.ItemCategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoriesService {
    @Autowired
    ItemCategoriesRepo itemCategoriesRepo;

    public ItemCategories createItemCategories(ItemCategoriesCreateReq request)
    {
        System.out.println(request);
        ItemCategories itemCategories = new ItemCategories();

        itemCategories.setName(request.getName());
        itemCategories.setCode(request.getCode());

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            itemCategories.setStatus(ItemStatus.valueOf(request.getStatus()));
        }

        itemCategoriesRepo.save(itemCategories);

        return itemCategories;
    }

}
