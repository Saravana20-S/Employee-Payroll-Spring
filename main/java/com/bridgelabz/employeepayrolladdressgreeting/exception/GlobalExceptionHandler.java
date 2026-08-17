package com.bridgelabz.employeepayrolladdressgreeting.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFound exception) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFound.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFound(AddressNotFound exception) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(DepartmentNotFound.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFound(DepartmentNotFound exception) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}