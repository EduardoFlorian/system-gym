package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreatePlanDTO;
import com.systemgym.systemgym.dto.response.ResponseDurationDTO;
import com.systemgym.systemgym.dto.response.ResponsePlanDTO;
import com.systemgym.systemgym.model.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    private final ModelMapperConfig modelMapperConfig;


    public PlanMapper(ModelMapperConfig modelMapperConfig) {
        this.modelMapperConfig = modelMapperConfig;
    }

    //Convertir RequestDTO a Entidad
    public Plan convertRequestToEntity(CreatePlanDTO createPlanDTO) {
        return modelMapperConfig.getModelMapper().map(createPlanDTO,Plan.class);
    }

    //Convertir Entidad a ResponseDTO
    public ResponsePlanDTO convertEntityToResponseDTO(Plan plan) {

        ResponseDurationDTO responseDurationDTO = new ResponseDurationDTO(plan.getDuration().getId(), plan.getDuration().getName(), plan.getDuration().getDurationDays());
        return new ResponsePlanDTO(plan.getId(), plan.getName(), plan.getDescription(), plan.getPrice(), responseDurationDTO);

    }

}
