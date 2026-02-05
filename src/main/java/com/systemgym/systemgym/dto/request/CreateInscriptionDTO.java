package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record CreateInscriptionDTO(

     @NotNull(message = "El campo idActivity no puede ser nulo")
     @Min(value = 1, message = "El idActivity debe ser mayor a 0")
     Integer idActivity,

     @NotNull(message = "El campo idPartner no puede ser nulo")
     @Min(value = 1, message = "El idPartner debe ser mayor a 0")
     Integer idPartner

) {
}
