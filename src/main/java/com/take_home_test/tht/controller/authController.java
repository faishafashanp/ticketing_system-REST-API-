package com.take_home_test.tht.controller;

import com.take_home_test.tht.dto.auth.loginRequestDTO;
import com.take_home_test.tht.dto.auth.loginResponseDTO;
import com.take_home_test.tht.dto.auth.registerRequestDTO;
import com.take_home_test.tht.dto.auth.registerResponseDTO;
import com.take_home_test.tht.dto.WebResponse;
import com.take_home_test.tht.service.authService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {

    private final authService authService;

    @PostMapping("/register")
    public WebResponse<registerResponseDTO> register(@RequestBody registerRequestDTO request) {
        return WebResponse.<registerResponseDTO>builder()
                .status("success").message("User registered").data(authService.register(request)).build();
    }

    @PostMapping("/login")
    public WebResponse<loginResponseDTO> login(@RequestBody loginRequestDTO request) {
        return WebResponse.<loginResponseDTO>builder()
                .status("success").message("Login success").data(authService.login(request)).build();
    }

    @PostMapping("/logout")
    public WebResponse<String> logout() {
        return WebResponse.<String>builder()
                .status("success")
                .message("Logout successful.")
                .build();
    }
}
