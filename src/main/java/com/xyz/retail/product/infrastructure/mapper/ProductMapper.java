package com.xyz.retail.product.infrastructure.mapper;

import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.infrastructure.entity.JpaProductEntity;
import com.xyz.retail.product.service.domain.entity.Product;

public class ProductMapper {

    public static JpaProductEntity toEntity(Product product) {
        return new JpaProductEntity(
                product.getId().value(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public static Product toDomain(JpaProductEntity entity) {
        return new Product(
                new ProductId(entity.getId()),
                entity.getName(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }
}