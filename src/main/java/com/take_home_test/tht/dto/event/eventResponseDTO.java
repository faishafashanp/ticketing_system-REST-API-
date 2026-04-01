package com.take_home_test.tht.dto.event;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class eventResponseDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime eventDate;
    private Integer availableSlots;
    private boolean isAvailable;
    private String status;
}
