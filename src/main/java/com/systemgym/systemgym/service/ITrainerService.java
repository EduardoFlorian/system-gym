package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreatePartnerDTO;
import com.systemgym.systemgym.dto.request.CreateTrainerDTO;
import com.systemgym.systemgym.dto.request.UpdatePartnerDTO;
import com.systemgym.systemgym.dto.request.UpdateTrainerDTO;
import com.systemgym.systemgym.dto.response.ResponsePartnerDTO;
import com.systemgym.systemgym.dto.response.ResponseTrainerDTO;
import com.systemgym.systemgym.model.Plan;
import com.systemgym.systemgym.model.Trainer;

import java.util.List;
import java.util.UUID;

public interface ITrainerService {

    ResponseTrainerDTO saveTrainer (CreateTrainerDTO createTrainerDTO) throws Exception;
    ResponseTrainerDTO findByIdTrainer(Integer id) throws Exception;
    ResponseTrainerDTO updateTrainer(Integer id, UpdateTrainerDTO updateTrainerDTO) throws Exception;
    List<ResponseTrainerDTO> listTrainers() throws Exception;

    //Metodo para poder retornar una entidad por id (Metodo de uso para otros servicios en casos de persistencia)
    Trainer findByIdTrainerEntity(Integer id) throws Exception;

}
