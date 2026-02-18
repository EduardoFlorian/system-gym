package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateActivityDTO;
import com.systemgym.systemgym.dto.request.CreateScheduleDTO;
import com.systemgym.systemgym.dto.response.ResponseActivityDTO;
import com.systemgym.systemgym.exception.ResourceNotFoundException;
import com.systemgym.systemgym.mapper.ActivityMapper;
import com.systemgym.systemgym.model.Activity;
import com.systemgym.systemgym.model.Schedule;
import com.systemgym.systemgym.model.Trainer;
import com.systemgym.systemgym.repository.IActivityRepository;
import com.systemgym.systemgym.service.IActivityService;
import com.systemgym.systemgym.service.IScheduleService;
import com.systemgym.systemgym.service.ITrainerService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements IActivityService {

    private final IActivityRepository iActivityRepository;
    private final ActivityMapper activityMapper;
    private final ITrainerService iTrainerService;
    private final IScheduleService iScheduleService;

    public ActivityServiceImpl(IActivityRepository iActivityRepository, ActivityMapper activityMapper, ITrainerService iTrainerService, IScheduleService iScheduleService) {
        this.iActivityRepository = iActivityRepository;
        this.activityMapper = activityMapper;
        this.iTrainerService = iTrainerService;
        this.iScheduleService = iScheduleService;
    }


    @Override
    public ResponseActivityDTO saveActivity(CreateActivityDTO createActivityDTO) throws Exception {
        // ---- VALIDACIONES DE ENTRADA DE DATOS

        //1. Existe el Trainer ingresado en el request?
        Trainer objTrainer = iTrainerService.findByIdTrainerEntity(createActivityDTO.idTrainer());

        //2. Validar horarios en el request (Que no se crucen o no se envien duplicados)
        validateSchedulesinRequest(createActivityDTO.schedules());

        //3. Validar a nivel de BD que un horario del request no exista previamente en BD, sino enviar excepcion
        createActivityDTO.schedules().stream().forEach(objSchedule -> {
            iScheduleService.validateOverlappingSchedule(objSchedule.dayOfWeekSchedule(),objSchedule.startTime(),objSchedule.endTime());
        });

        //4. Una ves validado el request, lo convertimos a entidad
        Activity objActivity = activityMapper.convertRequestToEntity(createActivityDTO);

        //5.
        // Seteamos valores necesarios para su posterior guardado.
        objActivity.setTrainer(objTrainer);

        // Guardamos el listado de horarios en un objeto para su posterior guardado.
        List<Schedule> lstSchedules = createActivityDTO.schedules().stream()
                .map(e-> {
                    Schedule sch = new Schedule();

                    sch.setActivity(objActivity);
                    sch.setStartTime(e.startTime());
                    sch.setEndTime(e.endTime());
                    sch.setDayOfWeekSchedule(e.dayOfWeekSchedule());

                    return sch;
                }).toList();

        objActivity.setSchedules(lstSchedules);

        //6. Guardamos el objeto
        iActivityRepository.save(objActivity);

        return activityMapper.convertEntityToResponseDTO(objActivity);

    }

    @Override
    public Activity findByIdActivityEntity(Integer id) throws ResourceNotFoundException {
        return iActivityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Activity not found"));
    }

    public void validateSchedulesinRequest(List<CreateScheduleDTO> schedules) {
        // 1. Agrupamos por día para no comparar Lunes con Martes innecesariamente
        Map<DayOfWeek, List<CreateScheduleDTO>> schedulesByDay = schedules.stream()
                .collect(Collectors.groupingBy(CreateScheduleDTO::dayOfWeekSchedule));

        // 2. Revisamos cada grupo de horarios (día por día)
        schedulesByDay.forEach((day, daySchedules) -> {

            for (int i = 0; i < daySchedules.size(); i++) {
                for (int j = i + 1; j < daySchedules.size(); j++) {

                    CreateScheduleDTO s1 = daySchedules.get(i);
                    CreateScheduleDTO s2 = daySchedules.get(j);

                    // 3. LA GRAN VALIDACIÓN (Traslape y Duplicados)
                    // (Inicio1 < Fin2) Y (Inicio2 < Fin1)
                    if (s1.startTime().isBefore(s2.endTime()) && s2.startTime().isBefore(s1.endTime())) {

                        throw new IllegalArgumentException(String.format(
                                "Conflicto de horarios en el día %s: El rango %s-%s choca con el horario de otra actividad de tu solicitud",
                                day, s1.startTime(), s1.endTime(), s2.startTime(), s2.endTime()
                        ));
                    }
                }
            }
        });
    }

}
