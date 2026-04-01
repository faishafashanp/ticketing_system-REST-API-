package com.take_home_test.tht.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class registerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String role;

}
