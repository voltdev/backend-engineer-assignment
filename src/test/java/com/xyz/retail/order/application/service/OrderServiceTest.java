/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xyz.retail.cart.application.port.in.ClearCartUseCase;
import com.xyz.retail.cart.application.port.in.GetCartUseCase;
import com.xyz.retail.cart.domain.entity.Cart;
import com.xyz.retail.cart.domain.valueobject.CartId;
import com.xyz.retail.cart.domain.valueobject.UserId;
import com.xyz.retail.order.application.port.in.command.PlaceOrderCommand;
import com.xyz.retail.order.application.port.in.query.OrderSearchQuery;
import com.xyz.retail.order.application.port.out.LoadOrderPort;
import com.xyz.retail.order.application.port.out.LoadUserPort;
import com.xyz.retail.order.application.port.out.SaveOrderPort;
import com.xyz.retail.order.domain.entity.Order;
import com.xyz.retail.order.domain.exception.OrderException;
import com.xyz.retail.product.application.port.out.LoadProductPort;
import com.xyz.retail.product.domain.valueobject.ProductId;
import com.xyz.retail.product.service.domain.entity.Product;
import com.xyz.retail.reporting.application.port.out.SaveSalesReportPort;

class OrderServiceTest {

  @Mock private GetCartUseCase getCartUseCase;

  @Mock private ClearCartUseCase clearCartUseCase;

  @Mock private SaveOrderPort saveOrderPort;

  @Mock private LoadOrderPort loadOrderPort;

  @Mock private LoadUserPort loadUserPort;

  @Mock private LoadProductPort loadProductPort;

  @Mock private SaveSalesReportPort saveSalesReportPort;

  private OrderService orderService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    orderService =
        new OrderService(
            getCartUseCase,
            clearCartUseCase,
            saveOrderPort,
            loadOrderPort,
            loadUserPort,
            loadProductPort,
            saveSalesReportPort);
  }

  @Test
  void shouldPlaceOrderSuccessfully() {
    // Given
    String userId = "user1";
    UUID productId = UUID.randomUUID();
    PlaceOrderCommand command = new PlaceOrderCommand(userId, "John Doe", "1234567890");

    // Create a UserDetails object instead of using the userId string directly
    LoadUserPort.UserDetails userDetails =
        new LoadUserPort.UserDetails(
            userId, "John Doe", "john@example.com"); // Adjust constructor parameters as needed
    when(loadUserPort.findByUserId(userId)).thenReturn(Optional.of(userDetails));

    // Mock cart with items
    Cart cart = new Cart(CartId.generate(), new UserId(userId));
    cart.addItem(productId, "Product", BigDecimal.TEN, 1);
    when(getCartUseCase.getCart(any())).thenReturn(cart);

    // Mock product exists and has stock
    Product product = new Product(new ProductId(productId), "Product", BigDecimal.TEN, 5);
    when(loadProductPort.loadProductById(productId)).thenReturn(Optional.of(product));

    // Mock save order
    Order savedOrder = Order.createFromCart(userId, "John Doe", "1234567890", cart.getItems());
    when(saveOrderPort.save(any(Order.class))).thenReturn(savedOrder);

    // When
    Order result = orderService.placeOrder(command);

    // Then
    assertNotNull(result);
    verify(loadUserPort).findByUserId(userId);
    verify(getCartUseCase).getCart(any());
    verify(saveOrderPort).save(any(Order.class));
    verify(saveSalesReportPort).recordSalesFromOrder(any(Order.class));
    verify(clearCartUseCase).clearCart(userId);
  }

  @Test
  void shouldThrowExceptionWhenUserDoesNotExist() {
    // Given
    PlaceOrderCommand command = new PlaceOrderCommand("unknown", "John", "123");
    when(loadUserPort.findByUserId("unknown")).thenReturn(Optional.empty());

    // When & Then
    assertThrows(OrderException.class, () -> orderService.placeOrder(command));
  }

  @Test
  void shouldThrowExceptionWhenCartIsEmpty() {
    // Given
    String userId = "user1";
    PlaceOrderCommand command = new PlaceOrderCommand(userId, "John Doe", "1234567890");
    LoadUserPort.UserDetails userDetails =
        new LoadUserPort.UserDetails(
            userId, "John Doe", "john@example.com"); // Adjust constructor parameters as needed
    when(loadUserPort.findByUserId(userId)).thenReturn(Optional.of(userDetails));

    Cart emptyCart = new Cart(CartId.generate(), new UserId(userId));
    when(getCartUseCase.getCart(any())).thenReturn(emptyCart);

    // When & Then
    assertThrows(OrderException.class, () -> orderService.placeOrder(command));
  }

  @Test
  void shouldThrowExceptionWhenProductNotAvailable() {
    // Given
    String userId = "user1";
    UUID productId = UUID.randomUUID();
    PlaceOrderCommand command = new PlaceOrderCommand(userId, "John Doe", "1234567890");
    LoadUserPort.UserDetails userDetails =
        new LoadUserPort.UserDetails(
            userId, "John Doe", "john@example.com"); // Adjust constructor parameters as needed
    when(loadUserPort.findByUserId(userId)).thenReturn(Optional.of(userDetails));

    Cart cart = new Cart(CartId.generate(), new UserId(userId));
    cart.addItem(productId, "Product", BigDecimal.TEN, 5);
    when(getCartUseCase.getCart(any())).thenReturn(cart);

    // Product exists but with insufficient stock
    Product product = new Product(new ProductId(productId), "Product", BigDecimal.TEN, 2);
    when(loadProductPort.loadProductById(productId)).thenReturn(Optional.of(product));

    // When & Then
    assertThrows(OrderException.class, () -> orderService.placeOrder(command));
  }

  @Test
  void shouldFindOrderById() {
    // Given
    UUID orderId = UUID.randomUUID();
    OrderSearchQuery query = new OrderSearchQuery(orderId);
    Order order =
        new Order(
            new com.xyz.retail.order.domain.valueobject.OrderId(orderId),
            "user1",
            "John",
            "123",
            new ArrayList<>(),
            BigDecimal.TEN,
            java.time.LocalDateTime.now(),
            com.xyz.retail.order.domain.valueobject.OrderStatus.CREATED);
    when(loadOrderPort.findById(any())).thenReturn(Optional.of(order));

    // When
    Optional<Order> result = orderService.findOrderById(query);

    // Then
    assertTrue(result.isPresent());
    assertEquals(orderId, result.get().getId().value());
  }

  @Test
  void shouldReturnEmptyWhenOrderNotFound() {
    // Given
    UUID orderId = UUID.randomUUID();
    OrderSearchQuery query = new OrderSearchQuery(orderId);
    when(loadOrderPort.findById(any())).thenReturn(Optional.empty());

    // When
    Optional<Order> result = orderService.findOrderById(query);

    // Then
    assertFalse(result.isPresent());
  }
}
