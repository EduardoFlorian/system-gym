package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;
import com.systemgym.systemgym.model.Inscription;

import java.util.List;

public interface IInscriptionService {

    ResponseInscriptionDTO saveInscription(CreateInscriptionDTO createInscriptionDTO) throws Exception;
    ResponseInscriptionDTO findInscriptionById (Integer id) throws Exception;
    List<ResponseInscriptionDTO> findAllInscriptions () throws Exception;

}
