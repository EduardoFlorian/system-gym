package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreateActivityDTO;
import com.systemgym.systemgym.dto.request.UpdateActivityDTO;
import com.systemgym.systemgym.dto.response.ResponseActivityDTO;
import com.systemgym.systemgym.exception.ResourceNotFoundException;
import com.systemgym.systemgym.model.Activity;

import java.util.List;

public interface IActivityService {

    ResponseActivityDTO saveActivity (CreateActivityDTO createActivityDTO) throws Exception;
    ResponseActivityDTO updateActivity (Integer id, UpdateActivityDTO updateActivityDTO) throws Exception;
    ResponseActivityDTO findActivityById (Integer id) throws Exception;
    List<ResponseActivityDTO> findAllActivities () throws Exception;

    //Metodo para poder retornar una entidad por id (Metodo de uso para otros servicios en casos de persistencia)
    Activity findByIdActivityEntity(Integer id) throws ResourceNotFoundException;
}
