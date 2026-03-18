package com.devteria.identity_service.items.repository;

import com.devteria.identity_service.items.entity.ItemCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoriesRepo extends JpaRepository<ItemCategories, Long> {
}
