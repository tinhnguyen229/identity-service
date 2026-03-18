package com.devteria.identity_service.items.controller;


import com.devteria.identity_service.items.dto.ItemGroupsCreateReq;
import com.devteria.identity_service.items.dto.ItemGroupsRes;
import com.devteria.identity_service.items.entity.ItemGroups;
import com.devteria.identity_service.items.service.ItemGroupsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemGroupsController {

    @Autowired
    private ItemGroupsService itemGroupsService;


    @PostMapping("/api/v1/item_groups")
    public ItemGroups createItemGroups(@RequestBody @Valid ItemGroupsCreateReq request)
    {
        System.out.println(request);
        return itemGroupsService.createGroups(request);

    }
}
