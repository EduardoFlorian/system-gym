package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;
import com.systemgym.systemgym.model.Inscription;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {

    private final ModelMapperConfig modelMapperConfig;

    public InscriptionMapper(ModelMapperConfig modelMapperConfig, ModelMapperConfig modelMapperConfig1) {
        this.modelMapperConfig = modelMapperConfig1;
    }

    //Convertir un request a entidad
    public Inscription convertRequestToEntity(CreateInscriptionDTO createInscriptionDTO) {
        return modelMapperConfig.getModelMapper().map(createInscriptionDTO, Inscription.class);
    }

    //Converir entidad a DTO
    public ResponseInscriptionDTO convertEntityToResponseDTO(Inscription inscription) {
        return ResponseInscriptionDTO.fromEntity(inscription);
    }

}
