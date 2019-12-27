package com.news.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.*;

public class ErrorHandler {
private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
	
	@GetMapping("/test")
	public void errorHandlerTest(){
		LOGGER.debug("Inside errorHandlerTest()");
		throw new RuntimeException("Bad Request");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleError(Exception ex){
		LOGGER.info("start");
		ErrorResponse error = new ErrorResponse();
		
			if (ex instanceof MethodArgumentNotValidException) 
					{
						MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
						String message = "";
						List<FieldError> errors = exception.getBindingResult().getFieldErrors();
						for (FieldError error1 : errors) {
							message += error1.getDefaultMessage() + ", ";
							
						}
						int length=message.length();
						error.setErrorMessage(message.substring(0,length-2));
						error.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
						error.setReasonCode(HttpStatus.BAD_REQUEST.value());
						return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);

					} 
			 

		else{
			error.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
			error.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			error.setErrorMessage(ex.getMessage());
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
