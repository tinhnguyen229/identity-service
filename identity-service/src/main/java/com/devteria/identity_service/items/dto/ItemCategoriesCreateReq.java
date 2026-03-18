package com.devteria.identity_service.items.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategoriesCreateReq {
    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Không được để trống")
    String name;


    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Không được để trống")
    String code;

    String status;
}
