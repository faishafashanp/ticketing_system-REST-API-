package com.take_home_test.tht.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class orderRequestDTO {

    private Long eventId;
    private Integer quantity;
    private String paymentMethod;
}
