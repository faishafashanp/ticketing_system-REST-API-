package com.take_home_test.tht.repository;

import com.take_home_test.tht.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userEntity, Long> {

    Optional<userEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
}
