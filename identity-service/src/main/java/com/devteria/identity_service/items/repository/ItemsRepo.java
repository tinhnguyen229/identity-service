package com.devteria.identity_service.items.repository;


import com.devteria.identity_service.items.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepo extends JpaRepository<Items, Integer> {
    public List<Items> findByGroupIdInAndIsDeleteIsFalse(List<Long> group_ids);
}
