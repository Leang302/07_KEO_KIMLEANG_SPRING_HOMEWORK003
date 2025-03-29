package com.leang.homework003.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank
    @Size(max = 50)
    private String attendeeName;
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;
}
