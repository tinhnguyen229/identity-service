package com.devteria.identity_service.items.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    private String code;
    private String name;
    private String unit;

    @Column(name = "code_erp")
    private String codeErp;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

}
