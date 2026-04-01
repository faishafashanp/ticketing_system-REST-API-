package com.take_home_test.tht.service;

import com.take_home_test.tht.dto.event.eventRequestDTO;
import com.take_home_test.tht.dto.event.eventResponseDTO;
import com.take_home_test.tht.entity.eventEntity;
import com.take_home_test.tht.repository.eventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class eventService {

    private final eventRepository eventRepository;

    @Transactional
    public eventResponseDTO create(eventRequestDTO request) {
        eventEntity event = new eventEntity();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setPrice(request.getPrice());
        event.setEventDate(request.getEventDate());
        event.setCapacity(request.getCapacity());
        event.setAvailableSlots(request.getCapacity());
        event.setStatus(eventEntity.EventStatus.active);

        eventRepository.save(event);
        return toEventResponse(event);
    }

    public eventResponseDTO update(Long id, eventRequestDTO request) {
        eventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        event.setName(request.getName());
        event.setPrice(request.getPrice());

        eventRepository.save(event);
        return toEventResponse(event);
    }

    public List<eventResponseDTO> getAllActive() {
        return eventRepository.findAllByDeletedAtIsNull().stream()
                .map(this::toEventResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        eventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event tidak ditemukan"));
        event.setDeletedAt(LocalDateTime.now());
        eventRepository.save(event);
    }

    private eventResponseDTO toEventResponse(eventEntity event) {
        return eventResponseDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .price(event.getPrice())
                .eventDate(event.getEventDate())
                .availableSlots(event.getAvailableSlots())
                .isAvailable(event.getAvailableSlots() > 0)
                .status(event.getStatus().name())
                .build();
    }
}
