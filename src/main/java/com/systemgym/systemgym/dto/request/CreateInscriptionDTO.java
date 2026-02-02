package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.NotNull;


public record CreateInscriptionDTO(

     @NotNull(message = "El campo idActivity no puede ser nulo")
     Integer idActivity,

     @NotNull(message = "El campo idPartner no puede ser nulo")
     Integer idPartner

) {
}
