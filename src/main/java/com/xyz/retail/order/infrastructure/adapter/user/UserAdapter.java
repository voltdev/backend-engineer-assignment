/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.infrastructure.adapter.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.xyz.retail.order.application.port.out.LoadUserPort;

@Component
public class UserAdapter implements LoadUserPort {

  // This is a simplified implementation. In a real application, this would connect to a user
  // service or database
  private final Map<String, UserDetails> userDatabase = new HashMap<>();

  public UserAdapter() {
    // Sample data for testing
    userDatabase.put("user123", new UserDetails("user123", "John Doe", "1234567890"));
    userDatabase.put("user456", new UserDetails("user456", "Jane Smith", "0987654321"));
    userDatabase.put(
        "some-user-id", new UserDetails("some-user-id", "Michael Milad", "+48690464612"));
  }

  @Override
  public Optional<UserDetails> findByUserId(String userId) {
    return Optional.ofNullable(userDatabase.get(userId));
  }
}
