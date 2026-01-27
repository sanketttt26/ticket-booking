package com.sanketttt26.tickets.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeResponseDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer totalAvailable;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
