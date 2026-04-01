package com.take_home_test.tht.service;

import com.take_home_test.tht.entity.eventEntity;
import com.take_home_test.tht.entity.ticketEntity;
import com.take_home_test.tht.repository.eventRepository;
import com.take_home_test.tht.repository.orderRepository;
import com.take_home_test.tht.repository.ticketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class reportService {

    private final orderRepository orderRepository;
    private final ticketRepository ticketRepository;
    private final eventRepository eventRepository;

    public Map<String, Object> getGeneralSummary() {

        BigDecimal totalRevenue = orderRepository.findAll().stream()
                .map(order -> order.getTotalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long totalTicketsSold = ticketRepository.count();
        long totalEvents = eventRepository.count();

        Map<String, Object> summary = new HashMap<>();
        summary.put("total_revenue", totalRevenue);
        summary.put("total_tickets_sold", totalTicketsSold);
        summary.put("total_active_events", totalEvents);

        return summary;
    }

    public Map<String, Object> getReportByEvent(Long eventId) {
        eventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        long ticketsSold = ticketRepository.countByEventIdAndStatus(eventId, ticketEntity.TicketStatus.valid);

        Map<String, Object> report = new HashMap<>();
        report.put("event_name", event.getName());
        report.put("capacity", event.getCapacity());
        report.put("tickets_sold", ticketsSold);
        report.put("remaining_slots", event.getAvailableSlots());

        return report;
    }
}
