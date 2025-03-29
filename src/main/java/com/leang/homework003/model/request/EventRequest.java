package com.leang.homework003.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank
    @Size(max = 50)
    private String eventName;
    @FutureOrPresent
    @NotNull
    private Instant eventDate;
    @NotNull
    @Min(value = 1, message = "Venue ID must be a valid number")
    private Long venueId;
    @NotNull
    @Size(min = 1, message = "Attendees list cannot be empty")
    private List<@NotNull(message = "Attendee ID is required") @Min(value = 1, message = "Attendee ID must be a valid number") Long> attendeesId;
}
