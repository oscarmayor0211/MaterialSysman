package com.materiales.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler(FechaInvalida.class)
    public ResponseEntity<?> handle(FechaInvalida ex,  WebRequest request) {
        Error errorDetails = new Error(HttpStatus.NOT_FOUND.toString(), new Date(), "La fecha de compra no puede ser posterior a la fecha de venta.",request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globeExceptionHandler(Exception ex, WebRequest request){
        Error errorDetails = new Error("Error", new Date(), ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
