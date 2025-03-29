package com.leang.homework003.controller;

import com.leang.homework003.model.entity.Venue;
import com.leang.homework003.model.request.VenueRequest;
import com.leang.homework003.model.response.ApiResponse;
import com.leang.homework003.service.VenueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<?> getAllVenues(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer size) {
        List<Venue> allVenues = venueService.getAllVenues(offset, size);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("All venues have been successfully fetched.")
                        .payload(allVenues)
                        .build()
        );
    }

    @PutMapping("{venue-id}")
    public ResponseEntity<?> updateVenueById(@Valid @RequestBody VenueRequest venueRequest, @PathVariable("venue-id") Long venueId) {
        Venue venue = venueService.updateVenueById(venueId, venueRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("The venue has been successfully updated.")
                        .payload(venue)
                        .build()
        );
    }

    @DeleteMapping("{venue-id}")
    public ResponseEntity<?> deleteVenueById(@PathVariable("venue-id") Long venueId) {
        venueService.removeVenueById(venueId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("The venue has been successfully deleted.")
                        .build()
        );
    }

    @GetMapping("{venue-id}")
    public ResponseEntity<?> getVenueById(@PathVariable("venue-id") Long venueId) {
        Venue venue = venueService.getVenueById(venueId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("The venue has been successfully retrieved.")
                        .payload(venue)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<?> createVenue(@Valid @RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.addVenue(venueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                        .httpStatus(HttpStatus.CREATED)
                        .message("The venue has been successfully added.")
                        .payload(venue)
                        .build()
        );
    }

}
