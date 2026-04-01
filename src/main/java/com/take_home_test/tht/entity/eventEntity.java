package com.take_home_test.tht.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events" )
public class eventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    private Integer capacity;

    @Column(name = "available_slots", nullable = false)
    private Integer availableSlots;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    public enum EventStatus {
        active,
        finished,
        cancelled
    }
}
