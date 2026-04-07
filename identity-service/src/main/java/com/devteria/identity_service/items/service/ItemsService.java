package com.devteria.identity_service.items.service;


import com.devteria.identity_service.exception.BusinessException;
import com.devteria.identity_service.items.dto.ItemsCreateReq;
import com.devteria.identity_service.items.entity.ItemGroups;
import com.devteria.identity_service.items.entity.Items;
import com.devteria.identity_service.items.repository.ItemGroupsRepo;
import com.devteria.identity_service.items.repository.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemsService {
    @Autowired
    private ItemsRepo itemsRepo;
    @Autowired
    private ItemGroupsRepo itemGroupsRepo;


    public Items createItem(ItemsCreateReq request) {
        ItemGroups group = itemGroupsRepo.findByIdAndIsDeleteFalse(request.getGroupId());
        if (group == null) {
            throw new RuntimeException("Không tồn tại groupId");
        }

        Items items = Items
                .builder()
                .groupId(request.getGroupId())
                .name(request.getName())
                .code(request.getCode())
                .unit(request.getUnit())
                .codeErp(request.getCodeErp())
                .build();
        return itemsRepo.save(items);
    }


    @Transactional
    public List<Items> createMultiItems(List<ItemsCreateReq> requests) {
        List<Items> items = new ArrayList<>();
        Set<Long> existGroupIds = new HashSet<>();
        for (ItemsCreateReq request : requests) {

            if (!existGroupIds.contains(request.getGroupId())) {
                ItemGroups group = itemGroupsRepo.findByIdAndIsDeleteFalse(request.getGroupId());
                if (group == null) {
                    throw new RuntimeException("Không tồn tại groupId");
                } else {
                    existGroupIds.add(request.getGroupId());
                }
            }

            Items item = Items
                .builder()
                .groupId(request.getGroupId())
                .name(request.getName())
                .code(request.getCode())
                .unit(request.getUnit())
                .codeErp(request.getCodeErp())
                .build();

            items.add(item);
        }

        return itemsRepo.saveAll(items);
    }

}
