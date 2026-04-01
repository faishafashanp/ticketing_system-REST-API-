package com.take_home_test.tht.repository;

import com.take_home_test.tht.entity.eventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface eventRepository extends JpaRepository<eventEntity, Long> {
    List<eventEntity> findAllByDeletedAtIsNull();

    List<eventEntity> findByStatusAndDeletedAtIsNull(eventEntity.EventStatus status);
}