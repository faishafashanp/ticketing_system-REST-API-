package com.take_home_test.tht.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class loginRequestDTO {

    private String email;
    private String password;
}
