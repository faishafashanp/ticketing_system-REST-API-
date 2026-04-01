package com.take_home_test.tht.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ticketResponseDTO {

    private Long id;
    private String ticketCode;
    private String eventName;
    private LocalDateTime eventDate;
    private String customerName;
    private String status;
}
