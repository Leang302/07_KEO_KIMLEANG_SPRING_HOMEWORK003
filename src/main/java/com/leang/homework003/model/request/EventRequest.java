package com.leang.homework003.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank(message = "Event name must not be blank")
    @Size(max = 50, message = "Event name must not exceed 50 characters")
    private String eventName;
    @NotNull(message = "Event date is required")
    private Instant eventDate;
    @NotNull(message = "Venue ID is required")
    @Min(value = 1, message = "Venue ID must be a valid number")
    private Long venueId;
    @NotNull(message = "Attendees list cannot be null")
    @Size(min = 1, message = "Attendees list cannot be empty")
    private List<@NotNull(message = "Attendee ID is required") @Min(value = 1, message = "Attendee ID must be a valid number") Long> attendeesId;
}
