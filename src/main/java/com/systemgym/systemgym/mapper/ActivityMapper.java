package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreateActivityDTO;
import com.systemgym.systemgym.dto.response.ResponseActivityDTO;
import com.systemgym.systemgym.dto.response.ResponseTrainerDTO;
import com.systemgym.systemgym.model.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    private final ModelMapperConfig modelMapperConfig;

    public ActivityMapper(ModelMapperConfig modelMapperConfig, ModelMapperConfig modelMapperConfig1) {
        this.modelMapperConfig = modelMapperConfig1;
    }

    //Convertir Request a Entity
    public Activity convertRequestToEntity (CreateActivityDTO createActivityDTO){
        return modelMapperConfig.getModelMapper().map(createActivityDTO,Activity.class);
    }

    //Convertir Entity a ResponseDTO
    public ResponseActivityDTO  convertEntityToResponseDTO(Activity activity){
        ResponseTrainerDTO trainerDTO = new ResponseTrainerDTO(activity.getTrainer().getId(),activity.getTrainer().getFirstName(),activity.getTrainer().getLastName(),activity.getTrainer().getSpecialty());
        return new ResponseActivityDTO(activity.getId(),activity.getCapacity(),activity.getDescription(),activity.getSchedule(),activity.getStartTime(),activity.getEndTime(),activity.getStartDate(),activity.getEndDate(),trainerDTO);
    }

}

