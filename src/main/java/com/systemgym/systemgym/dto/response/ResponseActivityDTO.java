package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Activity;
import com.systemgym.systemgym.model.Partner;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseActivityDTO(

        Integer id,

        Integer capacity,

        String description,

        String schedule,

        LocalTime startTime,

        LocalTime endTime,

        LocalDate startDate,

        LocalDate endDate,

        ResponseTrainerDTO trainer

) {

    public static ResponseActivityDTO fromEntity(Activity activity) {
        if (activity == null) return null;
        return new ResponseActivityDTO(
                activity.getId(),
                activity.getCapacity(),
                activity.getDescription(),
                activity.getSchedule(),
                activity.getStartTime(),
                activity.getEndTime(),
                activity.getStartDate(),
                activity.getEndDate(),
                ResponseTrainerDTO.fromEntity(activity.getTrainer())

        );
    }
}
