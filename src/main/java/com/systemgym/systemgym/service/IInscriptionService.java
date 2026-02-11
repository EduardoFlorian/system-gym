package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;

import java.util.List;

public interface IInscriptionService {

    ResponseInscriptionDTO saveInscription(CreateInscriptionDTO createInscriptionDTO) throws Exception;
}
