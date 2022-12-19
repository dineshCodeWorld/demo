package com.infy.tesla.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ResponseStatus
@RestControllerAdvice
public class UserNotFoundExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(UserNotFoundException.class)
  public Error exceptionHandler(UserNotFoundException exception)
  {
	  Error error = new Error(exception.getMessage(),HttpStatus.NOT_FOUND);
	  return error;
  }
    
}
