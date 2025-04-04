package com.demo.employeeManagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Marking this class as a global exception handler for all controllers
@ControllerAdvice
public class GlobalExceptionHandler {
	// Handles any exception that occurs in the application
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)   // Sets the HTTP status to 404 (Not Found)
	public String handleRuntimeException(Exception ex) {
        return ex.getMessage();
    }

}

