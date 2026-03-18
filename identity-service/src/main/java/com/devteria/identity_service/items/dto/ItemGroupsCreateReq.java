package com.devteria.identity_service.items.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemGroupsCreateReq {
    @NotNull
    private Long categoryId;

    @NotBlank
    private String name;

    private String status;
    private String note;
    private String usingTime;
}
