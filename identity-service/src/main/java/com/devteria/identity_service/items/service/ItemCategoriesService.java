package com.devteria.identity_service.items.service;

import com.devteria.identity_service.common.enumerate.ItemStatus;
import com.devteria.identity_service.items.dto.ItemCategoriesCreateReq;
import com.devteria.identity_service.items.dto.ItemCategoriesRes;
import com.devteria.identity_service.items.dto.ItemCategoriesUpdateReq;
import com.devteria.identity_service.items.dto.ItemGroupsRes;
import com.devteria.identity_service.items.entity.ItemCategories;
import com.devteria.identity_service.items.entity.ItemGroups;
import com.devteria.identity_service.items.mapper.ItemCategoriesMapper;
import com.devteria.identity_service.items.repository.ItemCategoriesRepo;
import com.devteria.identity_service.items.repository.ItemGroupsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemCategoriesService {
    @Autowired
    ItemCategoriesRepo itemCategoriesRepo;

    @Autowired
    ItemGroupsRepo itemGroupsRepo;

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

    public Page<ItemCategoriesRes> getAll(int page, int size, ItemStatus status) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<ItemCategories> result;

        if (status != null) {
            result = itemCategoriesRepo.findByIsDeleteFalseAndStatus(pageable, status);
        } else {
            result = itemCategoriesRepo.findAllByIsDeleteFalse(pageable);
        }

        List<ItemCategories> categories = result.getContent();
        List<Long> categoryIds = categories.stream().map(category -> category.getId()).toList();
        System.out.println(categories);
        System.out.println(categoryIds);

        List<ItemGroups> groups = itemGroupsRepo.findByCategoryIdInAndIsDeleteFalse(categoryIds);
        System.out.println(groups);

        // Mapping categoryId với item_groups
        Map<Long, List<ItemGroups>> groupMap = groups.stream()
                .collect(Collectors.groupingBy(group -> group.getCategoryId()));

        System.out.println(groupMap);

        List<ItemCategoriesRes> content = categories.stream()
            .map(category -> {
                List<ItemGroupsRes> groupRes = groupMap
                    .getOrDefault(category.getId(), List.of())
                    .stream()
                    .map(
                        g -> ItemGroupsRes.builder()
                                .id(g.getId())
                                .name(g.getName())
                                .status(g.getStatus().name())
                                .note(g.getNote())
                                .usingTime(g.getUsingTime())
                                .categoryId(g.getCategoryId())
                                .build()
                    )
                    .toList();

                return ItemCategoriesRes.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .code(category.getCode())
                        .itemGroups(groupRes)
                        .build();
            }).toList();

        System.out.println(content);
        return new PageImpl<>(content, pageable, result.getTotalElements());
//        return result.map(entity -> this.toResponse(entity));
    }

    private ItemCategoriesRes toResponse(ItemCategories entity) {
        return ItemCategoriesRes.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .build();
    }

}
