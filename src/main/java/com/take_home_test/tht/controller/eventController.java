package com.take_home_test.tht.controller;

import com.take_home_test.tht.dto.WebResponse;
import com.take_home_test.tht.dto.event.eventRequestDTO;
import com.take_home_test.tht.dto.event.eventResponseDTO;
import com.take_home_test.tht.service.eventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class eventController {
    private final eventService eventService;

    @GetMapping
    public WebResponse<List<eventResponseDTO>> getAll() {
        return WebResponse.<List<eventResponseDTO>>builder()
                .status("success").data(eventService.getAllActive()).build();
    }

    @PostMapping
    public WebResponse<eventResponseDTO> create(@RequestBody eventRequestDTO request) {
        return WebResponse.<eventResponseDTO>builder()
                .status("success").message("Event created").data(eventService.create(request)).build();
    }

    @PutMapping("/{id}")
    public WebResponse<eventResponseDTO> update(@PathVariable Long id, @RequestBody eventRequestDTO request) {
        return WebResponse.<eventResponseDTO>builder()
                .status("success").message("Event updated").data(eventService.update(id, request)).build();
    }

    @DeleteMapping("/{id}")
    public WebResponse<String> delete(@PathVariable Long id) {
        eventService.delete(id);
        return WebResponse.<String>builder().status("success").message("Event deleted").build();
    }
}
