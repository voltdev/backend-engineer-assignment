/* Copyright 2026 XYZ Retail */
package com.xyz.retail.product.application.service;

import java.util.Objects;

import com.xyz.retail.product.application.port.in.CreateProductUseCase;
import com.xyz.retail.product.application.port.in.command.CreateProductCommand;
import com.xyz.retail.product.application.port.out.SaveProductPort;
import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.service.domain.entity.Product;

public class CreateProductService implements CreateProductUseCase {

  private final SaveProductPort saveProductPort;

  public CreateProductService(SaveProductPort saveProductPort) {
    this.saveProductPort = saveProductPort;
  }

  @Override
  public Product create(CreateProductCommand command) {
    Objects.requireNonNull(command, "Command cannot be null");

    Product product =
        new Product(ProductId.newId(), command.name(), command.price(), command.quantity());

    return saveProductPort.save(product);
  }
}
