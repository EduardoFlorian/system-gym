package com.systemgym.systemgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Excepcion para error en general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleDefaultException (Exception exception, WebRequest webRequest){

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR); //Retorna 500
    }

    //Excepcion personalizada para cuando no se encuentre un recurso o registro en especifico
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundException (Exception exception, WebRequest webRequest){

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorResponse,  HttpStatus.NOT_FOUND); //Retorna 404


    }

}
