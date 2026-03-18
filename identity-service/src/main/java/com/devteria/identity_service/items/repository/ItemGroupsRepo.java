package com.devteria.identity_service.items.repository;

import com.devteria.identity_service.items.entity.ItemGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemGroupsRepo extends JpaRepository<ItemGroups, Long> {

}
