package com.xyz.retail.cart.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemJpaEntity, UUID> {
}