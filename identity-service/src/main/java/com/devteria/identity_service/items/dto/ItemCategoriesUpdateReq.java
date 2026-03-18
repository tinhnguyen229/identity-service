package com.devteria.identity_service.items.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategoriesUpdateReq {
    String name;
    String code;
    String status;
    Boolean isDelete;
}
