package com.take_home_test.tht.repository;

import com.take_home_test.tht.entity.ticketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ticketRepository extends JpaRepository<ticketEntity, Long> {

    List<ticketEntity> findAllByDeletedAtIsNull();

    List<ticketEntity> findByOrder_User_Email(String email);

    List<ticketEntity> findByOrderUserIdAndDeletedAtIsNull(Long userId);
    Optional<ticketEntity> findByTicketCode(String ticketCode);
    Long countByEventIdAndStatus(Long eventId, ticketEntity.TicketStatus status);
}
