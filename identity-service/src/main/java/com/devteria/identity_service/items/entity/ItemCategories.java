package com.devteria.identity_service.items.entity;

import com.devteria.identity_service.common.enumerate.ItemStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Table(name = "item_categories")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String code;

    String name;

    @Enumerated(EnumType.STRING)
    ItemStatus status = ItemStatus.ACTIVE; // Mặc định là active

    Boolean isDelete = false; // Mặc định là false
}
