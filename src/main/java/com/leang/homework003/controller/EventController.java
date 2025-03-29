package com.leang.homework003.controller;

import com.leang.homework003.model.entity.Event;
import com.leang.homework003.model.request.EventRequest;
import com.leang.homework003.model.response.ApiResponse;
import com.leang.homework003.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("{event-id}")
    public ResponseEntity<?> getEventById(@PathVariable("event-id") Long eventId) {
        Event event = eventService.getEventById(eventId);
        return ResponseEntity.ok(ApiResponse.builder().httpStatus(HttpStatus.OK).message("The event has been successfully retrieved.").payload(event).build());
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer size) {
        List<Event> events = eventService.getAllEvents(offset, size);
        return ResponseEntity.ok(ApiResponse.builder().httpStatus(HttpStatus.OK).message("All events have been successfully retrieved.").payload(events).build());
    }

    @DeleteMapping("{event-id}")
    public ResponseEntity<?> removeEventById(@PathVariable("event-id") Long eventId) {
        eventService.removeEventById(eventId);
        return ResponseEntity.ok(ApiResponse.builder().httpStatus(HttpStatus.OK).message("The event has been successfully removed.").build());
    }

    @PostMapping
    public ResponseEntity<?> addEvent(@Valid @RequestBody EventRequest eventRequest) {
        Event event = eventService.addEvent(eventRequest);
        return ResponseEntity.ok(ApiResponse.builder().httpStatus(HttpStatus.OK).message("The event has been successfully added.").payload(event).build());
    }

    @PutMapping("{event-id}")
    public ResponseEntity<?> updateEventById(@PathVariable("event-id") Long eventId,@Valid @RequestBody EventRequest eventRequest) {
        Event event = eventService.updateEventById(eventId, eventRequest);
        return ResponseEntity.ok(ApiResponse.builder().httpStatus(HttpStatus.OK).message("The event has been successfully updated.").payload(event).build());
    }

}
