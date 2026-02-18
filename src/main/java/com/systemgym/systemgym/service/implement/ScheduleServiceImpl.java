package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.exception.BusinessException;
import com.systemgym.systemgym.repository.IScheduleRepository;
import com.systemgym.systemgym.service.IScheduleService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    private final IScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(IScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public boolean validateOverlappingSchedule(DayOfWeek day, LocalTime startTime, LocalTime endTime) {

        boolean scheduleExist = scheduleRepository.existsOverlappingSchedule(day, startTime, endTime);

        if(scheduleExist) {
            throw new BusinessException(String.format("El horario %s de %s a %s ya está ocupado por otra actividad",
                    day, startTime, endTime));
        }
        return scheduleExist;
    }
}
