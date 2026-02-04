package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreatePlanDTO;
import com.systemgym.systemgym.dto.request.UpdatePlanDTO;
import com.systemgym.systemgym.dto.response.ResponsePlanDTO;
import com.systemgym.systemgym.model.Plan;

import java.util.List;
import java.util.UUID;

public interface IPlanService {

    ResponsePlanDTO savePlan (CreatePlanDTO createPlanDTO) throws Exception;
    ResponsePlanDTO updatePlan (UUID id, UpdatePlanDTO updatePlanDTO) throws Exception;
    ResponsePlanDTO findById (UUID idPlan) throws Exception;
    List<ResponsePlanDTO> findAll () throws Exception;

    //Metodo para poder retornar una entidad por id (Metodo de uso para otros servicios en casos de persistencia)
    Plan findByIdPlanEntity(UUID id) throws Exception;
}
