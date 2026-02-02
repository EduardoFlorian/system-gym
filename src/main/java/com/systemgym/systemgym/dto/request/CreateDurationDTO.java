package com.systemgym.systemgym.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDurationDTO(

        @NotNull(message = "El campo name no puede ser nulo")
        @Size(min = 3, max = 50, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 30 caracteres")
        String name,

        @NotNull(message = "El campo durationDays no puede ser nulo")
        Integer durationDays

) {
}
