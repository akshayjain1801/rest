package com.prince.rest.handlingexceptionwithspecificstructure;

import com.prince.rest.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // Handling All the Exception-

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception{
        ExceptionEntity exceptionEntity = new ExceptionEntity(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Handling Specific Exception-

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptionException(Exception ex, WebRequest request) throws Exception{
        ExceptionEntity exceptionEntity = new ExceptionEntity(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    // Handling Method Argument Exception by Overriding handleMethodArgumentNotValid() method -
    // Remember it is useful to override handleMethodArgumentNotValid() in case of applying Validation.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionEntity exceptionEntity = new ExceptionEntity(new Date(), "Validation Failed", ex.getBindingResult().toString());
        //getBindingResult().toString - It gives consumer the information of what actually went wrong.
        // Further on getBindingResult() we can invoke other method as well like getAllErrors().
        // Here in Message box, instead of writing ex.getMessage(), we simply write our own small message.
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
