package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Activity;
import com.systemgym.systemgym.model.Trainer;
import jakarta.persistence.Column;

public record ResponseTrainerDTO(

        Integer id,

        String firstName,

        String lastName,

        String specialty
) {

    public static ResponseTrainerDTO fromEntity(Trainer trainer) {
        if (trainer == null) return null;
        return new ResponseTrainerDTO(
                trainer.getId(),
                trainer.getFirstName(),
                trainer.getLastName(),
                trainer.getSpecialty()
        );
    }
}
