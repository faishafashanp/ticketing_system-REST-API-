package com.take_home_test.tht.controller;

import com.take_home_test.tht.dto.WebResponse;
import com.take_home_test.tht.dto.order.orderRequestDTO;
import com.take_home_test.tht.dto.order.orderResponseDTO;
import com.take_home_test.tht.dto.ticketResponseDTO;
import com.take_home_test.tht.service.orderService;
import com.take_home_test.tht.service.ticketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class ticketController {

    private final ticketService ticketService;
    private final orderService orderService;

    @GetMapping
    public WebResponse<List<ticketResponseDTO>> getTickets() {
        var auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")); // Pastikan nama role sesuai di DB

        List<ticketResponseDTO> data;

        if (isAdmin) {
            data = ticketService.getAllTicketsForAdmin();
        } else {
            data = ticketService.getMyTickets(email);
        }

        return WebResponse.<List<ticketResponseDTO>>builder()
                .status("success")
                .data(data)
                .build();
    }

    @PostMapping
    public WebResponse<orderResponseDTO> buyTicket(@RequestBody orderRequestDTO request) {

        String currentUserEmail = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName();

        return WebResponse.<orderResponseDTO>builder()
                .status("success")
                .message("Ticket purchased")
                .data(orderService.checkout(request, currentUserEmail))
                .build();
    }

    @GetMapping("/{id}")
    public WebResponse<ticketResponseDTO> getDetail(@PathVariable Long id) {
        return WebResponse.<ticketResponseDTO>builder()
                .status("success").data(ticketService.getById(id)).build();
    }

    @PatchMapping("/{id}")
    public WebResponse<String> cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicket(id);
        return WebResponse.<String>builder().status("success").message("Ticket cancelled").build();
    }
}
