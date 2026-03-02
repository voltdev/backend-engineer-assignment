package com.xyz.retail.product.presentation.mapper;

import com.xyz.retail.product.application.port.in.command.CreateProductCommand;
import com.xyz.retail.product.presentation.dto.ProductRequestDto;
import com.xyz.retail.product.presentation.dto.ProductResponseDto;
import com.xyz.retail.product.service.domain.entity.Product;

public class ProductApiMapper {

    public static CreateProductCommand toCommand(ProductRequestDto dto) {
        return new CreateProductCommand(
                dto.name(),
                dto.price(),
                dto.quantity()
        );
    }

    public static ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(
                product.getId().value(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.isLowStock()
        );
    }
}
