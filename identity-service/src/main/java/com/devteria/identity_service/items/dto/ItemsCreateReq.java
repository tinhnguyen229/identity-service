package com.devteria.identity_service.items.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsCreateReq {
    private Long groupId;
    private String code;
    private String name;
    private String unit;
    private String codeErp;
}
