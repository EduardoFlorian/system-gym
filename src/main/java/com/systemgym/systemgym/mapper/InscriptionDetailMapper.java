package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreateInscriptionDetailDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDetailDTO;
import com.systemgym.systemgym.model.InscriptionDetail;
import org.springframework.stereotype.Component;

@Component
public class InscriptionDetailMapper {

    private final ModelMapperConfig modelMapperConfig;

    public InscriptionDetailMapper(ModelMapperConfig modelMapperConfig) {
        this.modelMapperConfig = modelMapperConfig;
    }

    //Convertir un request DTO a entidad

    public InscriptionDetail convertRequestToEntity(CreateInscriptionDetailDTO createInscriptionDetailDTO){
        return modelMapperConfig.getModelMapper().map(createInscriptionDetailDTO,InscriptionDetail.class);
    }

    //Convertir Entidad a DTO
    public ResponseInscriptionDetailDTO convertEntityToResponseDto (InscriptionDetail inscriptionDetail) {
        return ResponseInscriptionDetailDTO.fromEntity(inscriptionDetail);
    }

}
