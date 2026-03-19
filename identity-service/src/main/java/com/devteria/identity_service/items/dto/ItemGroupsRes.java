package com.devteria.identity_service.items.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemGroupsRes {
    private Long id;
    private Long categoryId;
    private String name;
    private String status;
    private String note;
    private String usingTime;
    List<ItemsRes> itemsRes;
}
