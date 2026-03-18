package com.devteria.identity_service.items.repository;


import com.devteria.identity_service.items.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepo extends JpaRepository<Items, Integer> {

}
