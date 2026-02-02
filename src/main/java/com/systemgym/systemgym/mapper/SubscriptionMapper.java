package com.systemgym.systemgym.mapper;

import com.systemgym.systemgym.configuration.ModelMapperConfig;
import com.systemgym.systemgym.dto.request.CreateSubscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseSubscriptionDTO;

import com.systemgym.systemgym.model.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    private final ModelMapperConfig modelMapperConfig;

    public SubscriptionMapper(ModelMapperConfig modelMapperConfig) {
        this.modelMapperConfig = modelMapperConfig;
    }

    //Convertir Request a Entidad
    public Subscription convertRequestToEntity (CreateSubscriptionDTO createSubscriptionDTO) {
        return modelMapperConfig.getModelMapper().map(createSubscriptionDTO, Subscription.class);
    }

    //Convertir Entidad a DTO
    public ResponseSubscriptionDTO convertEntityToResponseDto (Subscription subscription) {
        return ResponseSubscriptionDTO.fromEntity(subscription);
    }
}
