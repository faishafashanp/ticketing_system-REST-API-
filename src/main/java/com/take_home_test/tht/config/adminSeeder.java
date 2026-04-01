package com.take_home_test.tht.config;

import com.take_home_test.tht.entity.userEntity;
import com.take_home_test.tht.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class adminSeeder implements CommandLineRunner {

    private final userRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@test.com";

        // Cek dulu biar nggak duplikat tiap kali restart
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            userEntity admin = new userEntity();
            admin.setName("Super Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(userEntity.Role.admin);

            userRepository.save(admin);
            System.out.println("=========================================");
            System.out.println(">>> AKUN ADMIN BERHASIL DIBUAT: " + adminEmail);
            System.out.println("=========================================");
        }
    }
}
