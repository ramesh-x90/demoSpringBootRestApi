package com.example.demo.domain.exception;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.domain.exception.errors.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler ; 
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String , String> invalidFieldHandler (MethodArgumentNotValidException ex){

        Map<String , String> errorMap= new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
        
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataIntegrityViolationExceptionHandler (DataIntegrityViolationException ex){

        String message = ex.getMostSpecificCause().getMessage();

        return new ResponseEntity<>(new ApiError(message, HttpStatus.CONFLICT), HttpStatus.CONFLICT);
        
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableExceptionHandler (HttpMessageNotReadableException ex){

        String message = ex.getMostSpecificCause().getMessage();

        return new ResponseEntity<>(new ApiError(message, HttpStatus.BAD_REQUEST), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object>  constraintViolationExceptionHandler(ConstraintViolationException ex){
        return new ResponseEntity<>(new ApiError(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST) , HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        return new ResponseEntity<>(new ApiError(ex.getMessage() , HttpStatus.NOT_FOUND) , HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Object> InvalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException ex){
        return new ResponseEntity<>(new ApiError(ex.getMessage() , HttpStatus.BAD_REQUEST) , HttpStatus.BAD_REQUEST);
    }


    
}
