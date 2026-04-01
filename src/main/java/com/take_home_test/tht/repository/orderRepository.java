package com.take_home_test.tht.repository;

import com.take_home_test.tht.entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface orderRepository extends JpaRepository<orderEntity, Long> {

    List<orderEntity> findByUserIdAndDeletedAtIsNull(Long userId);
}
