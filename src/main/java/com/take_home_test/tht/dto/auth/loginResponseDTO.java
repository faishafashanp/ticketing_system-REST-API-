package com.take_home_test.tht.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class loginResponseDTO {

    private String token;
    private String email;
    private String name;
    private String role;

}
