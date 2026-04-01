package com.take_home_test.tht.service;

import com.take_home_test.tht.config.jwtUtils;
import com.take_home_test.tht.dto.auth.loginRequestDTO;
import com.take_home_test.tht.dto.auth.loginResponseDTO;
import com.take_home_test.tht.dto.auth.registerRequestDTO;
import com.take_home_test.tht.dto.auth.registerResponseDTO;
import com.take_home_test.tht.entity.userEntity;
import com.take_home_test.tht.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class authService {

    private final userRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final jwtUtils jwtUtils;

    @Transactional
    public registerResponseDTO register(registerRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already registerd");
        }

        userEntity user = new userEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(userEntity.Role.customer);

        userRepository.save(user);

        return registerResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    public loginResponseDTO login(loginRequestDTO request) {
        userEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email is incorrect"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is incorrect");
        }

        String token = jwtUtils.generateToken(user.getEmail(), user.getRole().name());

        return loginResponseDTO.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .name(user.getName())
                .build();
    }

}
