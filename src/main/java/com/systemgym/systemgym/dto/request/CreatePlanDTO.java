package com.systemgym.systemgym.dto.request;

import com.systemgym.systemgym.model.Duration;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreatePlanDTO(

        @NotNull(message = "El campo name no puede ser nulo")
        @Size(min = 3, max = 50, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 50 caracteres")
        String name,

        @NotNull(message = "El campo description no puede ser nulo")
        @Size(min = 3, max = 80, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 80 caracteres")
        String description,

        @NotNull
        @Min(value = 80)
        @Max(value = 1000)
        Double price,

        @NotNull
        @Min(value = 1,message = "El idDuration debe ser mayor a 0")
        Integer idDuration

) {
}
