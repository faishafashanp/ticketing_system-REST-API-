package com.take_home_test.tht.dto.event;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class eventRequestDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime eventDate;
    private Integer capacity;
}
