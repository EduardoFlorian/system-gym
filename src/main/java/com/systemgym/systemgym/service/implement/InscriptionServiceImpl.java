package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;
import com.systemgym.systemgym.mapper.InscriptionDetailMapper;
import com.systemgym.systemgym.mapper.InscriptionMapper;
import com.systemgym.systemgym.model.*;
import com.systemgym.systemgym.repository.IInscriptionDetailRepository;
import com.systemgym.systemgym.repository.IInscriptionRepository;
import com.systemgym.systemgym.service.IActivityService;
import com.systemgym.systemgym.service.IInscriptionService;
import com.systemgym.systemgym.service.IPartnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscriptionServiceImpl implements IInscriptionService {

    private final IInscriptionRepository inscriptionRepository;
    private final IInscriptionDetailRepository inscriptionDetailRepository;
    private final InscriptionMapper inscriptionMapper;
    private final InscriptionDetailMapper inscriptionDetailMapper;
    private final IPartnerService partnerService;
    private final IActivityService activityService;

    public InscriptionServiceImpl(IInscriptionRepository inscriptionRepository, IInscriptionDetailRepository inscriptionDetailRepository, InscriptionMapper inscriptionMapper, InscriptionDetailMapper inscriptionDetailMapper, IPartnerService partnerService, IActivityService activityService) {
        this.inscriptionRepository = inscriptionRepository;
        this.inscriptionDetailRepository = inscriptionDetailRepository;
        this.inscriptionMapper = inscriptionMapper;
        this.inscriptionDetailMapper = inscriptionDetailMapper;
        this.partnerService = partnerService;
        this.activityService = activityService;
    }


    @Override
    @Transactional
    public ResponseInscriptionDTO saveInscription(CreateInscriptionDTO createInscriptionDTO) {

        //Verificar si existe el idPartner de la solicitud y alojarlo en un objeto
        Partner partner = partnerService.findByIdPartnerEntity(createInscriptionDTO.idPartner());

        //Verificar si la lista de IdActividades de la solicitud existen y alojarlo en un listado de objetos
        List<Activity> activities = createInscriptionDTO.details().stream()
                .map(e -> activityService.findByIdActivityEntity(e.idActivity()))
                .toList();

        //Pasar la solicitud Dto a Entidad
        Inscription objInscription = inscriptionMapper.convertRequestToEntity(createInscriptionDTO);

        //Seteamos valores por defecto a objInscription
        objInscription.setDateRegistration(LocalDateTime.now());
        objInscription.setPartner(partner);
        objInscription.setStatus(StatusInscription.REGISTERED);

        //Creamos la lista de detalle
        List<InscriptionDetail> details = activities.stream()
                .map(activity -> {
                    InscriptionDetail detail = new InscriptionDetail();
                    detail.setActivity(activity);
                    detail.setInscription(objInscription); //Seteamos el objeto de inscripcion necesario para la creacion  y relacion entre tablas
                    return detail;
                }).toList();

        //Seteamos el listado de detalle a objInscription
        objInscription.setInscriptionDetails(details);

        //Guardamos el objeto
        inscriptionRepository.save(objInscription);

        return inscriptionMapper.convertEntityToResponseDto(objInscription);

    }
}
