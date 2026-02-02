package com.systemgym.systemgym.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdatePlanDTO(

        @NotNull
        @Min(value = 80)
        @Max(value = 1000)
        Double price

) {
}
