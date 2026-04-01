package com.take_home_test.tht.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tickets" )
public class ticketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private eventEntity event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private orderEntity order;

    @Column(name = "ticket_code", unique = true, nullable = false)
    private String ticketCode;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    public enum TicketStatus {
        valid,
        cancelled
    }
}
