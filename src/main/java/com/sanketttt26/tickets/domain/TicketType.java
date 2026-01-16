package com.sanketttt26.tickets.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "total_available")
    private Integer totalAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="event_id")
    private Event event;

    @CreatedDate
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

}
