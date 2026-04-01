package com.take_home_test.tht.service;


import com.take_home_test.tht.dto.ticketResponseDTO;
import com.take_home_test.tht.entity.ticketEntity;
import com.take_home_test.tht.repository.ticketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ticketService {

    private final ticketRepository ticketRepository;

    public List<ticketResponseDTO> getAllTicketsForAdmin() {
        return ticketRepository.findAll().stream()
                .map(this::toTicketResponse)
                .collect(Collectors.toList());
    }

    public List<ticketResponseDTO> getMyTickets(String email) {
        return ticketRepository.findByOrder_User_Email(email).stream()
                .map(this::toTicketResponse)
                .collect(Collectors.toList());
    }

    public ticketResponseDTO getById(Long id) {
        ticketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
        return toTicketResponse(ticket);
    }

    public void cancelTicket(Long id) {
        ticketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        ticket.setStatus(ticketEntity.TicketStatus.cancelled);
        ticketRepository.save(ticket);
    }

    private ticketResponseDTO toTicketResponse(ticketEntity ticket) {
        return ticketResponseDTO.builder()
                .id(ticket.getId())
                .ticketCode(ticket.getTicketCode())
                .eventName(ticket.getEvent().getName())
                .eventDate(ticket.getEvent().getEventDate())
                .customerName(ticket.getOrder().getUser().getName())
                .status(ticket.getStatus().name())
                .build();
    }
}
