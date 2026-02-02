package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreatePartnerDTO;
import com.systemgym.systemgym.dto.response.ResponsePartnerDTO;
import com.systemgym.systemgym.model.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {

    private final ModelMapperConfig modelMapper;

    public PartnerMapper(ModelMapperConfig modelMapper) {
        this.modelMapper = modelMapper;
    }

    //Convertir un request a entidad
    public Partner convertRequestToEntity(CreatePartnerDTO createPartnerDTO){
        return modelMapper.getModelMapper().map(createPartnerDTO,Partner.class);
    }

    //Convertir una entidad a response
    public ResponsePartnerDTO convertEntityToResponseDto(Partner partner){
        return new ResponsePartnerDTO(partner.getId(), partner.getFirstName(), partner.getLastName(), partner.getEmail(), partner.getPhoneNumber(), partner.getRegistrationDate(),partner.isActive());
    }

}
