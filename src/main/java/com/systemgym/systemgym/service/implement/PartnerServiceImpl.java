package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreatePartnerDTO;
import com.systemgym.systemgym.dto.request.UpdatePartnerDTO;
import com.systemgym.systemgym.dto.response.ResponsePartnerDTO;
import com.systemgym.systemgym.mapper.PartnerMapper;
import com.systemgym.systemgym.model.Partner;
import com.systemgym.systemgym.repository.IPartnerRepository;
import com.systemgym.systemgym.service.IPartnerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartnerServiceImpl implements IPartnerService {

    private final IPartnerRepository iPartnerRepository;
    private final PartnerMapper partnerMapper;

    public PartnerServiceImpl(IPartnerRepository iPartnerRepository, PartnerMapper partnerMapper) {
        this.iPartnerRepository = iPartnerRepository;
        this.partnerMapper = partnerMapper;
    }

    @Override
    public ResponsePartnerDTO savePartner(CreatePartnerDTO createPartnerDTO) throws Exception {

        Partner partnerEntity = partnerMapper.convertRequestToEntity(createPartnerDTO);

        partnerEntity.setRegistrationDate(LocalDateTime.now());
        partnerEntity.setActive(false);

        iPartnerRepository.save(partnerEntity);

        return partnerMapper.convertEntityToResponseDto(partnerEntity);
    }

    @Override
    public ResponsePartnerDTO findByIdPartner(Integer id) throws Exception {

        Partner objPartner = iPartnerRepository.findById(id).orElseThrow(() -> new Exception("Partner not found"));

        return partnerMapper.convertEntityToResponseDto(objPartner);

    }

    @Override
    public ResponsePartnerDTO updatePartner(Integer id, UpdatePartnerDTO updatePartnerDTO) throws Exception {

        Partner partnerEntity = iPartnerRepository.findById(id).orElseThrow(() -> new Exception("Partner not found"));

        partnerEntity.setFirstName(updatePartnerDTO.firstName());
        partnerEntity.setLastName(updatePartnerDTO.lastName());
        partnerEntity.setEmail(updatePartnerDTO.email());
        partnerEntity.setPhoneNumber(updatePartnerDTO.phoneNumber());
        partnerEntity.setActive(updatePartnerDTO.active());

        iPartnerRepository.save(partnerEntity);

        return partnerMapper.convertEntityToResponseDto(partnerEntity);

    }

    @Override
    public List<ResponsePartnerDTO> listPartners() throws Exception {

        List<Partner> listPartnersEntity = iPartnerRepository.findAll();
        List<ResponsePartnerDTO> listPartnersDTO = listPartnersEntity.stream().map(e-> partnerMapper.convertEntityToResponseDto(e)).toList();

        return  listPartnersDTO;
    }

    //Metodo para poder retornar una entidad por id (Metodo de uso para otros servicios en casos de persistencia)
    @Override
    public Partner findByIdPartnerEntity(Integer id) throws Exception {
        Partner objPartnerEntity = iPartnerRepository.findById(id).orElseThrow(() -> new Exception("Partner not found"));
        return objPartnerEntity;
    }
}
