package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

public record UpdateStudentDto(
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        @Email
        String email

) {
}
