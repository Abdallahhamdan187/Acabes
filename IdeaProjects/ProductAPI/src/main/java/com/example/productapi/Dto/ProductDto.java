package com.example.productapi.Dto;

import java.time.LocalDateTime;

public record ProductDto(
         Long id,
         String name,
         double price,
         String category,
         String description,
         int quantity,
         boolean isPaid,
         boolean isSeen,
         LocalDateTime createdAt
) {
}
