package com.devteria.identity_service.items.repository;

import com.devteria.identity_service.common.enumerate.ItemStatus;
import com.devteria.identity_service.items.entity.ItemCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoriesRepo extends JpaRepository<ItemCategories, Long> {
    Page<ItemCategories> findAllByIsDeleteFalse(Pageable pageable);
    Page<ItemCategories> findByIsDeleteFalseAndStatus(Pageable pageable,  ItemStatus status);

    Page<ItemCategories> findAll(Pageable pageable);
}
