package com.devteria.identity_service.items.service;

import com.devteria.identity_service.common.enumerate.ItemStatus;
import com.devteria.identity_service.items.dto.ItemCategoriesCreateReq;
import com.devteria.identity_service.items.dto.ItemCategoriesUpdateReq;
import com.devteria.identity_service.items.entity.ItemCategories;
import com.devteria.identity_service.items.mapper.ItemCategoriesMapper;
import com.devteria.identity_service.items.repository.ItemCategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoriesService {
    @Autowired
    ItemCategoriesRepo itemCategoriesRepo;

    @Autowired
    ItemCategoriesMapper itemCategoriesMapper;

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

    public ItemCategories updateItemCategories(Long id, ItemCategoriesUpdateReq request)
    {
        ItemCategories itemCategory = itemCategoriesRepo
                .findById(id)
                .orElseThrow(
                    () -> new RuntimeException("Không tìm thấy loại vật tuw!")
                );

        itemCategoriesMapper.updateItemCategories(request, itemCategory);

        return itemCategoriesRepo.save(itemCategory);
    }

    public void deleteItemCategories(Long id) {
        itemCategoriesRepo.deleteById(id);
    }

}
