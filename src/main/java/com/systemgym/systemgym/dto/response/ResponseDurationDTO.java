package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Duration;
import com.systemgym.systemgym.model.Plan;

public record ResponseDurationDTO(

        Integer id,

        String name,

        Integer durationDays

) {

    public static ResponseDurationDTO fromEntity(Duration duration){
        if (duration == null) return null;
        return new ResponseDurationDTO(
                duration.getId(),
                duration.getName(),
                duration.getDurationDays()
        );
    }

}
