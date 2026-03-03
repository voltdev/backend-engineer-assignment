/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.infrastructure.adapter.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemJpaEntity, UUID> {}
