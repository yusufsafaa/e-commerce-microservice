package com.example.product.core.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
