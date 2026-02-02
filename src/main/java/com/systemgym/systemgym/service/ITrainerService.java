package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreatePartnerDTO;
import com.systemgym.systemgym.dto.request.CreateTrainerDTO;
import com.systemgym.systemgym.dto.request.UpdatePartnerDTO;
import com.systemgym.systemgym.dto.request.UpdateTrainerDTO;
import com.systemgym.systemgym.dto.response.ResponsePartnerDTO;
import com.systemgym.systemgym.dto.response.ResponseTrainerDTO;

import java.util.List;

public interface ITrainerService {

    ResponseTrainerDTO saveTrainer (CreateTrainerDTO createTrainerDTO) throws Exception;
    ResponseTrainerDTO findByIdTrainer(Integer id) throws Exception;
    ResponseTrainerDTO updateTrainer(Integer id, UpdateTrainerDTO updateTrainerDTO) throws Exception;
    List<ResponseTrainerDTO> listTrainers() throws Exception;

}
