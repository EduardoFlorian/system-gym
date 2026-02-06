package com.systemgym.systemgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Devuelve 422: Indica que el registro existe y viola reglas de validación de negocio.
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RecordAlreadyExistsException extends RuntimeException {
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}
