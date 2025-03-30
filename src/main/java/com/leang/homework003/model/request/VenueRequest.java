package com.leang.homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest  {
    @NotBlank
    @Size(max = 50)
    private String venueName;
    @NotBlank
    @Size(max = 255)
    private String location;
}
