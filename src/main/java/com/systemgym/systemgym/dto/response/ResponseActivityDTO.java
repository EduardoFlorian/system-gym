package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Activity;
import com.systemgym.systemgym.model.Partner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ResponseActivityDTO(

        Integer id,

        Integer capacity,

        String description,

        LocalDate startDate,

        LocalDate endDate,

        ResponseTrainerDTO trainer,

        List<ResponseScheduleDTO> schedules

) {

    public static ResponseActivityDTO fromEntity(Activity activity) {
        if (activity == null) return null;
        return new ResponseActivityDTO(
                activity.getId(),
                activity.getCapacity(),
                activity.getDescription(),
                activity.getStartDate(),
                activity.getEndDate(),
                ResponseTrainerDTO.fromEntity(activity.getTrainer()),
                activity.getSchedules().stream().map(e->ResponseScheduleDTO.fromEntity(e)).toList()

        );
    }
}
