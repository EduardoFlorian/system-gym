package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface IScheduleRepository extends JpaRepository<Schedule, Integer> {

    //Query para conocer si existe un cruce de horarios o no.
    //La query aplica la siguiente formula para evitar el solapamiento (horarios sobrepuestos) ==> Inicio1 < Fin2 AND Inicio2 < Fin1

    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE s.dayOfWeekSchedule = :day " +
            "AND :startTime < s.endTime " +
            "AND :endTime > s.startTime")
    boolean existsOverlappingSchedule(@Param("day") DayOfWeek day,
                                      @Param("startTime") LocalTime startTime,
                                      @Param("endTime") LocalTime endTime);

}
