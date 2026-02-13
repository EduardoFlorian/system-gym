package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateInscriptionDTO (

        @NotNull(message = "El campo idPartner no puede ser nulo")
        @Min(value = 1,message = "El idPartner debe ser mayor a 0")
        Integer idPartner,

        @NotNull(message = "El campo details no puede ser nulo")
        List<CreateInscriptionDetailDTO> details

) {
}
