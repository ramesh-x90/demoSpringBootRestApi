package com.example.demo.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewStudentDto(
        @JsonProperty("name")
        @NotNull(message = "name can't be null")
        @NotEmpty String name,

        @JsonProperty("email")
        @NotNull(message = "email can't be null")
        @NotEmpty String email
) {
}
