/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.retail.product.infrastructure.entity.JpaProductEntity;

public interface SpringDataProductRepository extends JpaRepository<JpaProductEntity, UUID> {

  List<JpaProductEntity> findByNameContainingIgnoreCase(String name);
}
