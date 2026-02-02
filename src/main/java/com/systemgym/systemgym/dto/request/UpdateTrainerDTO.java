package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTrainerDTO(

    @NotNull(message = "El campo firstName no puede ser nulo")
    @Size(min = 3, max = 30, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 30 caracteres")
    String firstName,

    @NotNull(message = "El campo firstName no puede ser nulo")
    @Size(min = 3, max = 30, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 30 caracteres")
    String lastName

) {
}
