package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSubscriptionDTO(

         @NotNull(message = "El campo idPartner no puede ser nulo")
         Integer idPartner,

         @NotNull(message = "El campo idPlan no puede ser nulo")
         UUID idPlan

) {
}
