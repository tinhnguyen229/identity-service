package com.devteria.identity_service.items.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCategoriesRes {
    Long id;
    String name;
    String code;

    List<ItemGroupsRes> itemGroups;
}
