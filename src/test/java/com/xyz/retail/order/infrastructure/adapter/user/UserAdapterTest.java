/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xyz.retail.order.application.port.out.LoadUserPort;

class UserAdapterTest {

  private UserAdapter userAdapter;

  @BeforeEach
  void setUp() {
    userAdapter = new UserAdapter();
  }

  @Test
  void shouldFindExistingUser() {
    // When
    Optional<LoadUserPort.UserDetails> user = userAdapter.findByUserId("user123");

    // Then
    assertTrue(user.isPresent());
    assertEquals("user123", user.get().userId());
    assertEquals("John Doe", user.get().name());
    assertEquals("1234567890", user.get().mobileNumber());
  }

  @Test
  void shouldFindAnotherUser() {
    // When
    Optional<LoadUserPort.UserDetails> user = userAdapter.findByUserId("user456");

    // Then
    assertTrue(user.isPresent());
    assertEquals("user456", user.get().userId());
    assertEquals("Jane Smith", user.get().name());
  }

  @Test
  void shouldReturnEmptyForNonExistentUser() {
    // When
    Optional<LoadUserPort.UserDetails> user = userAdapter.findByUserId("unknown-user");

    // Then
    assertFalse(user.isPresent());
  }

  @Test
  void shouldFindUserWithComplexData() {
    // When
    Optional<LoadUserPort.UserDetails> user = userAdapter.findByUserId("some-user-id");

    // Then
    assertTrue(user.isPresent());
    assertEquals("Michael Milad", user.get().name());
  }
}
