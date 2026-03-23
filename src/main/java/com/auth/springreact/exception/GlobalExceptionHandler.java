package com.auth.springreact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> exceptionHandler(UserNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Map<String,String>> exceptionHandler(UserCreationException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(UserDeletionException.class)
    public ResponseEntity<Map<String,String>> exceptionHandler(UserDeletionException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<Map<String,String>> exceptionHandler(UserUpdateException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }
}
