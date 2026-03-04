/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.service;

import java.util.Optional;
import java.util.UUID;

import com.xyz.retail.cart.application.port.in.ClearCartUseCase;
import com.xyz.retail.cart.application.port.in.GetCartUseCase;
import com.xyz.retail.cart.application.port.in.query.GetCartQuery;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.order.application.port.in.OrderSearchUseCase;
import com.xyz.retail.order.application.port.in.PlaceOrderUseCase;
import com.xyz.retail.order.application.port.in.command.PlaceOrderCommand;
import com.xyz.retail.order.application.port.in.query.OrderSearchQuery;
import com.xyz.retail.order.application.port.out.LoadOrderPort;
import com.xyz.retail.order.application.port.out.LoadUserPort;
import com.xyz.retail.order.application.port.out.SaveOrderPort;
import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.exception.OrderException;
import com.xyz.retail.order.domain.valueobject.OrderId;
import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.reporting.application.port.out.SaveSalesReportPort;

public class OrderService implements PlaceOrderUseCase, OrderSearchUseCase {

  private final GetCartUseCase getCartUseCase;
  private final ClearCartUseCase clearCartUseCase;
  private final SaveOrderPort saveOrderPort;
  private final LoadOrderPort loadOrderPort;
  private final LoadUserPort loadUserPort;
  private final LoadProductPort loadProductPort;
  private final SaveSalesReportPort saveSalesReportPort;

  public OrderService(
      GetCartUseCase getCartUseCase,
      ClearCartUseCase clearCartUseCase,
      SaveOrderPort saveOrderPort,
      LoadOrderPort loadOrderPort,
      LoadUserPort loadUserPort,
      LoadProductPort loadProductPort,
      SaveSalesReportPort saveSalesReportPort) {
    this.getCartUseCase = getCartUseCase;
    this.clearCartUseCase = clearCartUseCase;
    this.saveOrderPort = saveOrderPort;
    this.loadOrderPort = loadOrderPort;
    this.loadUserPort = loadUserPort;
    this.loadProductPort = loadProductPort;
    this.saveSalesReportPort = saveSalesReportPort;
  }

  @Override
  public Order placeOrder(PlaceOrderCommand command) {
    // Validate user exists
    loadUserPort
        .findByUserId(command.userId())
        .orElseThrow(() -> new OrderException("User does not exist: " + command.userId()));

    // Get user's cart
    Cart cart = getCartUseCase.getCart(new GetCartQuery(command.userId()));

    if (cart.getItems().isEmpty()) {
      throw new OrderException("Cannot place order with empty cart");
    }

    // Validate product availability
    cart.getItems()
        .forEach(
            item -> {
              UUID productId = item.getProductId();
              loadProductPort
                  .loadProductById(productId)
                  .filter(product -> product.getQuantity() >= item.getQuantity())
                  .orElseThrow(
                      () ->
                          new OrderException(
                              "Product not available in requested quantity: "
                                  + item.getProductName()));
            });

    // Create order from cart
    Order order =
        Order.createFromCart(
            command.userId(), command.customerName(), command.mobileNumber(), cart.getItems());

    // Save order
    Order savedOrder = saveOrderPort.save(order);

    // Record sales data for reporting
    saveSalesReportPort.recordSalesFromOrder(savedOrder);

    // Clear the cart after successful order placement
    clearCartUseCase.clearCart(command.userId());

    return savedOrder;
  }

  @Override
  public Optional<Order> findOrderById(OrderSearchQuery query) {
    return loadOrderPort.findById(new OrderId(query.orderId()));
  }
}
