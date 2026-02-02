package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateActivityDTO(

        @NotNull(message = "El campo capacity no puede ser nulo")
        Integer capacity,

        @NotNull(message = "El campo description no puede ser nulo")
        @Size(min = 3, max = 80, message = "El campo nombre debe contener un mínimo de 3 y un máximo de 150 caracteres")
        String description,

        @NotNull(message = "El campo startTime no puede ser nulo")
        LocalTime startTime,

        @NotNull(message = "El campo endTime no puede ser nulo")
        LocalTime endTime,

        @NotNull(message = "El campo startDate no puede ser nulo")
        @FutureOrPresent(message = "La fecha de inicio ingresada debe ser la fecha actual o una fecha futura")
        LocalDate startDate,

        @NotNull(message = "El campo endDate no puede ser nulo")
        @Future(message = "La fecha de finalizacion ingresada debe ser una fecha futura")
        LocalDate endDate,

        @NotNull(message = "El campo idTrainer no puede ser nulo")
        Integer idTrainer


) {
}
