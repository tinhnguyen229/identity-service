package com.devteria.identity_service.items.entity;


import com.devteria.identity_service.common.enumerate.ItemStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "item_groups")
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    ItemStatus status = ItemStatus.ACTIVE; // Mặc định là active

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "using_time")
    private String usingTime;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

    @Column(name="category_id")
    private Long categoryId;


}
