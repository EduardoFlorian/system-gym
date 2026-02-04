package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CreateSubscriptionDTO(

         @NotNull(message = "El campo idPartner no puede ser nulo")
         @Min(value = 1, message = "El idPartner debe ser mayor a 0")
         Integer idPartner,

         @NotNull(message = "El campo idPlan no puede ser nulo")
         UUID idPlan

) {
}
