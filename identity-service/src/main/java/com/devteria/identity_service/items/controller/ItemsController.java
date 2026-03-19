package com.devteria.identity_service.items.controller;


import com.devteria.identity_service.items.dto.ItemsCreateReq;
import com.devteria.identity_service.items.dto.ItemsRes;
import com.devteria.identity_service.items.entity.Items;
import com.devteria.identity_service.items.service.ItemsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsController {
    @Autowired
    private ItemsService itemsService;


    @PostMapping("/api/v1/items")
    public Items createItems(@RequestBody @Valid ItemsCreateReq req) {
        return itemsService.createItem(req);
    }
}
