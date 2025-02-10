package org.tames.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateProductRequestDto(@NotBlank String name, String description, @NotNull @Positive BigDecimal price,
                                      @NotNull @PositiveOrZero Integer quantity, String category) {
}
