package com.devteria.identity_service.items.service;

import com.devteria.identity_service.items.dto.ItemGroupsCreateReq;
import com.devteria.identity_service.items.dto.ItemGroupsRes;
import com.devteria.identity_service.items.entity.ItemGroups;
import com.devteria.identity_service.items.repository.ItemGroupsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemGroupsService {

    @Autowired
    private ItemGroupsRepo itemGroupsRepo;

    public ItemGroups createGroups(ItemGroupsCreateReq request)
    {
        ItemGroups group = ItemGroups.builder()
                .name(request.getName())
                .note(request.getNote())
                .categoryId(request.getCategoryId())
                .usingTime(request.getUsingTime())
                .build();

        return itemGroupsRepo.save(group);
    }
}
