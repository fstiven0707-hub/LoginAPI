package com.stif.loginapi.exception;

import com.stif.loginapi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Captura errores generales de la aplicación
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> manejarErrores(RuntimeException ex) {


        ApiResponse respuesta = new ApiResponse(
                ex.getMessage(),
                null
        );


        return new ResponseEntity<>(
                respuesta,
                HttpStatus.BAD_REQUEST
        );
    }



    /**
     * Captura errores inesperados
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> manejarErroresGenerales(Exception ex) {


        ApiResponse respuesta = new ApiResponse(
                "Error interno del servidor",
                null
        );


        return new ResponseEntity<>(
                respuesta,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}