/* Copyright 2026 XYZ Retail */
package com.xyz.retail.cart.application.port.out;

import com.xyz.retail.cart.domain.valueobject.UserId;

public interface DeleteCartPort {
  void deleteByUserId(UserId userId);
}
