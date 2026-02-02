package com.systemgym.systemgym.service;


import com.systemgym.systemgym.dto.request.CreateDurationDTO;
import com.systemgym.systemgym.dto.response.ResponseDurationDTO;

import java.util.List;

public interface IDurationService{

    ResponseDurationDTO saveDuration (CreateDurationDTO createDurationDTO) throws Exception;

    List<ResponseDurationDTO> findAllDuration() throws Exception;

}
