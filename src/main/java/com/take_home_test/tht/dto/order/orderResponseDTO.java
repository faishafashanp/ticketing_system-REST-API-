package com.take_home_test.tht.dto.order;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class orderResponseDTO {

    private Long orderId;
    private String customerName;
    private String eventName;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private List<String> ticketCodes;
}
