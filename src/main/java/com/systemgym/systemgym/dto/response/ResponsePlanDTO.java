package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Plan;

import java.util.UUID;

public record ResponsePlanDTO(

        UUID id,

        String name,

        String description,

        Double price,

        ResponseDurationDTO duration
) {

    public static ResponsePlanDTO fromEntity(Plan plan){
        if (plan == null) return null;
        return new ResponsePlanDTO(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                plan.getPrice(),
                ResponseDurationDTO.fromEntity(plan.getDuration())
        );
    }
}
