package com.xyz.retail.product.presentation.controller;

import com.xyz.retail.product.application.port.in.CreateProductUseCase;
import com.xyz.retail.product.application.port.in.SearchProductsUseCase;
import com.xyz.retail.product.application.port.in.command.CreateProductCommand;
import com.xyz.retail.product.application.port.in.query.SearchProductsQuery;
import com.xyz.retail.product.presentation.dto.ProductRequestDto;
import com.xyz.retail.product.presentation.dto.ProductResponseDto;
import com.xyz.retail.product.presentation.mapper.ProductApiMapper;
import com.xyz.retail.product.service.domain.entity.Product;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final SearchProductsUseCase searchProductsUseCase;
    private final CreateProductUseCase createProductUseCase;

    public ProductController(
            SearchProductsUseCase searchProductsUseCase,
            CreateProductUseCase createProductUseCase
    ) {
        this.searchProductsUseCase = searchProductsUseCase;
        this.createProductUseCase = createProductUseCase;
    }

    @Operation(summary = "Search products by name")
    @GetMapping("/search")
    public List<ProductResponseDto> searchProducts(@RequestParam String name) {
        List<Product> results = searchProductsUseCase.search(new SearchProductsQuery(name));
        return results.stream().map(ProductApiMapper::toResponse).toList();
    }

    @Operation(summary = "Create a new product")
    @PostMapping
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto dto) {
        CreateProductCommand command = ProductApiMapper.toCommand(dto);
        Product created = createProductUseCase.create(command);
        return ProductApiMapper.toResponse(created);
    }
}