/* Copyright 2026 XYZ Retail */
package com.xyz.retail.order.application.port.out;

import java.util.Optional;

public interface LoadUserPort {
  Optional<UserDetails> findByUserId(String userId);

  record UserDetails(String userId, String name, String mobileNumber) {}
}
