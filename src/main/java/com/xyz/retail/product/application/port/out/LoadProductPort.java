package com.xyz.retail.product.application.port.out;

import com.xyz.retail.product.service.domain.entity.Product;

import java.util.List;


public interface LoadProductPort {
    List<Product> loadByName(String namePattern);
}
