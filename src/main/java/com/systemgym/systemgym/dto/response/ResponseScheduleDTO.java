package com.systemgym.systemgym.dto.response;

import com.systemgym.systemgym.model.Schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record ResponseScheduleDTO(

        DayOfWeek dayOfWeekSchedule,

        LocalTime startTime,

        LocalTime endTime

) {
    public static ResponseScheduleDTO fromEntity(Schedule schedule) {
        if (schedule == null) return null;
        return new ResponseScheduleDTO(
                schedule.getDayOfWeekSchedule(),
                schedule.getStartTime(),
                schedule.getEndTime()
        );
    }
}
