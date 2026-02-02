package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreateDurationDTO;
import com.systemgym.systemgym.dto.response.ResponseDurationDTO;
import com.systemgym.systemgym.model.Duration;
import org.springframework.stereotype.Component;

@Component
public class DurationMapper {

    private final ModelMapperConfig modelMapper;

    public DurationMapper(ModelMapperConfig modelMapper) {
        this.modelMapper = modelMapper;
    }

    //Convertir un request a entidad
    public Duration convertRequestToEntity(CreateDurationDTO createDurationDTO){
        return modelMapper.getModelMapper().map(createDurationDTO,Duration.class);
    }

    //Convertir una entidad a response
    public ResponseDurationDTO convertEntityToResponseDto(Duration duration){
        return new ResponseDurationDTO(duration.getId(),duration.getName(),duration.getDurationDays());
    }

}
