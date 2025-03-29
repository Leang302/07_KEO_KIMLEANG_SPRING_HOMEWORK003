package com.leang.homework003.controller;

import com.leang.homework003.model.entity.Attendee;
import com.leang.homework003.model.request.AttendeeRequest;
import com.leang.homework003.model.response.ApiResponse;
import com.leang.homework003.service.AttendeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<?> getAllAttendees(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer size) {
        List<Attendee> allAttendees = attendeeService.getAllAttendees(offset, size);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("All attendees have been successfully fetched.")
                        .payload(allAttendees)
                        .build()
        );
    }

    @PutMapping("{attendee-id}")
    public ResponseEntity<?> updateAttendeeById(@Valid @RequestBody AttendeeRequest venueRequest, @PathVariable("attendee-id") Long venueId) {
        Attendee venue = attendeeService.updateAttendeeById(venueId, venueRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("The attendee has been successfully updated.")
                        .payload(venue)
                        .build()
        );
    }

    @DeleteMapping("{attendee-id}")
    public ResponseEntity<?> deleteAttendeeById(@PathVariable("attendee-id") Long venueId) {
        attendeeService.removeAttendeeById(venueId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("The attendee has been successfully deleted.")
                        .build()
        );
    }

    @GetMapping("{attendee-id}")
    public ResponseEntity<?> getAttendeeById(@PathVariable("attendee-id") Long venueId) {
        Attendee venue = attendeeService.getAttendeeById(venueId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("The attendee has been successfully retrieved.")
                        .payload(venue)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<?> createAttendee(@Valid @RequestBody AttendeeRequest venueRequest) {
        Attendee venue = attendeeService.addAttendee(venueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .message("The attendee has been successfully added.")
                        .payload(venue)
                        .build()
        );
    }
}
