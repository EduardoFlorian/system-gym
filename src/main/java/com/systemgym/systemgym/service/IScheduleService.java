package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreateActivityDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface IScheduleService {

    boolean validateOverlappingSchedule(DayOfWeek day, LocalTime startTime, LocalTime endTime);

}
