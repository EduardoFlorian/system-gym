package com.systemgym.systemgym.service;

import com.systemgym.systemgym.dto.request.CreatePartnerDTO;
import com.systemgym.systemgym.dto.request.UpdatePartnerDTO;
import com.systemgym.systemgym.dto.response.ResponsePartnerDTO;
import com.systemgym.systemgym.model.Partner;

import java.util.List;

public interface IPartnerService {

    ResponsePartnerDTO savePartner (CreatePartnerDTO createPartnerDTO) throws Exception;
    ResponsePartnerDTO findByIdPartner(Integer id) throws Exception;
    ResponsePartnerDTO updatePartner(Integer id, UpdatePartnerDTO UpdatePartnerDTO) throws Exception;
    List<ResponsePartnerDTO> listPartners() throws Exception;

    //Metodo para poder retornar una entidad por id (Metodo de uso para otros servicios en casos de persistencia)
    Partner findByIdPartnerEntity(Integer id) throws Exception;

}
