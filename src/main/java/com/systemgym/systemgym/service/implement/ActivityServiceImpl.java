package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateActivityDTO;
import com.systemgym.systemgym.dto.request.UpdateActivityDTO;
import com.systemgym.systemgym.dto.response.ResponseActivityDTO;
import com.systemgym.systemgym.exception.ResourceNotFoundException;
import com.systemgym.systemgym.mapper.ActivityMapper;
import com.systemgym.systemgym.model.Activity;
import com.systemgym.systemgym.model.Trainer;
import com.systemgym.systemgym.repository.IActivityRepository;
import com.systemgym.systemgym.repository.ITrainerRepository;
import com.systemgym.systemgym.service.IActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements IActivityService {

    private final IActivityRepository iActivityRepository;
    private final ActivityMapper activityMapper;
    private final ITrainerRepository iTrainerRepository;

    public ActivityServiceImpl(IActivityRepository iActivityRepository, ActivityMapper activityMapper, ITrainerRepository iTrainerRepository) {
        this.iActivityRepository = iActivityRepository;
        this.activityMapper = activityMapper;
        this.iTrainerRepository = iTrainerRepository;
    }

    @Override
    public ResponseActivityDTO saveActivity(CreateActivityDTO createActivityDTO) throws Exception {

        Trainer objTrainer = iTrainerRepository.findById(createActivityDTO.idTrainer()).orElseThrow(() ->new ResourceNotFoundException("El id trainer no existe"));

        Activity activityEntity = activityMapper.convertRequestToEntity(createActivityDTO);

        activityEntity.setTrainer(objTrainer);

        iActivityRepository.save(activityEntity);

        return activityMapper.convertEntityToResponseDTO(activityEntity);

    }

    @Override
    public ResponseActivityDTO updateActivity(Integer id, UpdateActivityDTO updateActivityDTO) throws Exception {

        Activity objActivity = iActivityRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("La actividad ingresada no existe"));

        Trainer objTrainer = iTrainerRepository.findById(updateActivityDTO.idTrainer()).orElseThrow(() ->new ResourceNotFoundException("El id trainer no existe"));

        objActivity.setCapacity(updateActivityDTO.capacity());
        objActivity.setDescription(updateActivityDTO.description());
        objActivity.setStartTime(updateActivityDTO.startTime());
        objActivity.setEndTime(updateActivityDTO.endTime());
        objActivity.setStartDate(updateActivityDTO.startDate());
        objActivity.setEndDate(updateActivityDTO.endDate());
        objActivity.setTrainer(objTrainer);

        iActivityRepository.save(objActivity);

        return activityMapper.convertEntityToResponseDTO(objActivity);
    }

    @Override
    public ResponseActivityDTO findActivityById(Integer id) throws Exception {

        Activity activityObj = iActivityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Actividad no encontrada"));

        return activityMapper.convertEntityToResponseDTO(activityObj);
    }

    @Override
    public List<ResponseActivityDTO> findAllActivities() throws Exception {

        List<Activity> activities = iActivityRepository.findAll();
        List<ResponseActivityDTO> responseActivityDTOs = activities.stream().map(e->activityMapper.convertEntityToResponseDTO(e)).toList();

        return responseActivityDTOs;

    }
}
