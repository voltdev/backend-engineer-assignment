package com.xyz.retail.product.infrastructure.repository;

import com.xyz.retail.product.infrastructure.entity.JpaProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataProductRepository extends JpaRepository<JpaProductEntity, UUID> {

    List<JpaProductEntity> findByNameContainingIgnoreCase(String name);
}