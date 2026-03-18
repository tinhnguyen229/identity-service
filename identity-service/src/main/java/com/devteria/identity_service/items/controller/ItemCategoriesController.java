package com.devteria.identity_service.items.controller;

import com.devteria.identity_service.items.dto.ItemCategoriesCreateReq;
import com.devteria.identity_service.items.dto.ItemCategoriesRes;
import com.devteria.identity_service.items.entity.ItemCategories;
import com.devteria.identity_service.items.service.ItemCategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemCategoriesController {

    @Autowired
    ItemCategoriesService itemCategoriesService;


    @PostMapping("/api/v1/item_categories")
    public ItemCategoriesRes createItemCategories(@RequestBody @Valid ItemCategoriesCreateReq request){
        ItemCategories category = itemCategoriesService.createItemCategories(request);
        return ItemCategoriesRes.builder()
                .id(category.getId())
                .code(category.getCode())
                .name(category.getName())
                .build();

    }

}
