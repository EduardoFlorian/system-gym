package com.systemgym.systemgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN) //Devuelve 403 El servidor entiende la petición y los datos son correctos, pero se niega a ejecutarl debido a una condición de negocio (el estado del socio)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
