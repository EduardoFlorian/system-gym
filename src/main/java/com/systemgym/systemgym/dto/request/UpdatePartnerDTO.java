package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdatePartnerDTO(

    @NotNull(message = "El campo firstName no puede ser nulo")
    @Size(min = 3, max = 50, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 50 caracteres")
    String firstName,

    @NotNull(message = "El campo lastName no puede ser nulo")
    @Size(min = 3, max = 50, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 50 caracteres")
    String lastName,

    @NotNull(message = "El campo email no puede ser nulo")
    @Size(min = 3, max = 30, message = "El campo email debe contener un mínimo de 3 y un máximo de 80 caracteres")
    @Email(message = "Ingresa un email con formato válido")
    String email,

    @NotNull(message = "El campo phoneNumber no puede ser nulo")
    @Size(min = 9, max = 9, message = "El campo telefono debe contener 9 caracteres")
    @Pattern(regexp = "^9[0-9]{8}$", message = "El telefono debe empezar con el dígito 9 para ser válido")
    String phoneNumber

) {
}
