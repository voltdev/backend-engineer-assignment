package com.xyz.retail.product.infrastructure.adapter;

import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.application.port.out.SaveProductPort;
import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.infrastructure.entity.JpaProductEntity;
import com.xyz.retail.product.infrastructure.mapper.ProductMapper;
import com.xyz.retail.product.infrastructure.repository.SpringDataProductRepository;
import com.xyz.retail.product.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductJpaRepositoryAdapter implements LoadProductPort, SaveProductPort {

    private final SpringDataProductRepository repository;

    public ProductJpaRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> loadByName(String pattern) {
        List<JpaProductEntity> entities = repository.findByNameContainingIgnoreCase(pattern);
        return entities.stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public Product save(Product product) {
        JpaProductEntity saved = repository.save(ProductMapper.toEntity(product));
        return ProductMapper.toDomain(saved);
    }

    public Optional<Product> findById(ProductId id) {
        return repository.findById(id.value())
                .map(ProductMapper::toDomain);
    }
}