package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreateTrainerDTO;
import com.systemgym.systemgym.dto.response.ResponseTrainerDTO;
import com.systemgym.systemgym.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    private final ModelMapperConfig modelMapper;

    public TrainerMapper(ModelMapperConfig modelMapper) {
        this.modelMapper = modelMapper;
    }

    //Convertir un request a entidad
    public Trainer convertRequestToEntity(CreateTrainerDTO createTrainerDTO){
        return modelMapper.getModelMapper().map(createTrainerDTO, Trainer.class);
    }

    //Convertir una entidad a response
    public ResponseTrainerDTO convertEntityToResponseDto(Trainer trainer){
        return new ResponseTrainerDTO(trainer.getId(),trainer.getFirstName(),trainer.getLastName(),trainer.getSpecialty());
    }

}
