package com.take_home_test.tht.service;

import com.take_home_test.tht.dto.order.orderRequestDTO;
import com.take_home_test.tht.dto.order.orderResponseDTO;
import com.take_home_test.tht.entity.eventEntity;
import com.take_home_test.tht.entity.orderEntity;
import com.take_home_test.tht.entity.ticketEntity;
import com.take_home_test.tht.entity.userEntity;
import com.take_home_test.tht.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class orderService {

    private final orderRepository orderRepository;
    private final eventRepository eventRepository;
    private final userRepository userRepository;
    private final ticketRepository ticketRepository;

    @Transactional
    public orderResponseDTO checkout(orderRequestDTO request, String userEmail) {
        eventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        if (event.getAvailableSlots() < request.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tickets are sold out or not available");
        }

        userEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // 1. Potong Stok
        event.setAvailableSlots(event.getAvailableSlots() - request.getQuantity());
        eventRepository.save(event);

        // 2. Buat Order
        BigDecimal total = event.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
        orderEntity order = orderEntity.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .totalAmount(total)
                .paymentMethod(request.getPaymentMethod())
                .status(orderEntity.OrderStatus.success)
                .build();
        orderRepository.save(order);

        // 3. Generate Tiket
        List<String> codes = new ArrayList<>();
        for (int i = 0; i < request.getQuantity(); i++) {
            String code = "TIX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            ticketEntity ticket = ticketEntity.builder()
                    .event(event)
                    .order(order)
                    .ticketCode(code)
                    .status(ticketEntity.TicketStatus.valid)
                    .build();
            ticketRepository.save(ticket);
            codes.add(code);
        }

        return orderResponseDTO.builder()
                .orderId(order.getId())
                .customerName(user.getName())
                .eventName(event.getName())
                .totalAmount(total)
                .status(order.getStatus().name())
                .orderDate(order.getOrderDate())
                .ticketCodes(codes)
                .build();
    }
}
