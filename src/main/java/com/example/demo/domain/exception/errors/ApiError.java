package com.example.demo.domain.exception.errors;


import org.springframework.http.HttpStatus;

public record ApiError(String message , HttpStatus httpStatus) {
}
