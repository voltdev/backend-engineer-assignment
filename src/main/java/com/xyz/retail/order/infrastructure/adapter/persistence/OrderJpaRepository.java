/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, UUID> {
  List<OrderJpaEntity> findByUserIdOrderByCreatedAtDesc(String userId);
}
