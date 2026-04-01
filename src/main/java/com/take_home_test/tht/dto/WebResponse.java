package com.take_home_test.tht.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebResponse<T> {
    private String status;
    private String message;
    private T data;
}
