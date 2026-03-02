package com.xyz.retail.product.presentation.error;

import java.time.Instant;

public record RestErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {}