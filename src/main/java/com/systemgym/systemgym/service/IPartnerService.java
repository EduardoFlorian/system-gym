package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreatePartnerDTO;
import com.systemgym.systemgym.dto.request.UpdatePartnerDTO;
import com.systemgym.systemgym.dto.response.ResponsePartnerDTO;

import java.util.List;

public interface IPartnerService {

    ResponsePartnerDTO savePartner (CreatePartnerDTO createPartnerDTO) throws Exception;
    ResponsePartnerDTO findByIdPartner(Integer id) throws Exception;
    ResponsePartnerDTO updatePartner(Integer id, UpdatePartnerDTO UpdatePartnerDTO) throws Exception;
    List<ResponsePartnerDTO> listPartners() throws Exception;

}
