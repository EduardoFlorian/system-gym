package com.systemgym.systemgym.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateScheduleDTO(

        Integer id,

        DayOfWeek dayOfWeekSchedule,

        LocalTime startTime,

        LocalTime endTime,

        CreateActivityDTO activity

) {
}
