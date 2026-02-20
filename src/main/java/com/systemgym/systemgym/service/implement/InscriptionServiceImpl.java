package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;
import com.systemgym.systemgym.exception.BusinessException;
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

        //Validar si el usuario cuenta con una subscripcion activa
        if(partner.isActive()!=true){
            throw new BusinessException("El socio no cuenta con una subscripción activa. No puede inscribirse a ninguna actividad.");
        }

        //Validar que los ids de actividades enviados en el dto no sean repetidos:
        //1. Obtenemos los id unicos de la lista de createInscriptionDTO.details()
        List<Integer> actividadesUnicas = createInscriptionDTO.details().stream().map(e->e.idActivity()).distinct().toList();

        //2. Comparamos el tamaño de la lista con ids unicos con la lista original del dto (createInscriptionDTO.details())
        if(actividadesUnicas.size()!= createInscriptionDTO.details().size()) {
            throw new IllegalArgumentException("Tu solicitud contiene actividades duplicadas. Solo te puedes inscribir en una actividad una sola ves");
        }

        //Verificar si la lista de ids de la lista actividadesUnicas existen en BD y alojarlo en un listado de objetos de tipo Activity
        List<Activity> activities = actividadesUnicas.stream()
                .map(e -> activityService.findByIdActivityEntity(e))
                .toList();

        //3. Verificar si aún hay aforo disponible para cada Actividad que se desean inscribir
        for(Activity activity : activities) {

            Integer inscritosActuales = inscriptionDetailRepository.countParticipantsByActivityId(activity.getId());

            if(inscritosActuales == activity.getCapacity()){
                throw new BusinessException("La Actividad: " + activity.getDescription() + " ya alcanzó el límite de inscripciones. Aforo lleno." );
            }
        }

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
                    detail.setInscription(objInscription); //Seteamos el objeto de inscripcion necesario para la creacion y relacion entre tablas
                    return detail;
                }).toList();

        // ----------- LOGICA DE VALIDACION DE ACTIVIDADES INSCRITAS -----------

        //1. Primero validar que las actividades de la nueva inscripcion no choque con inscripciones que el socio ya tenía registradas
        //El usuario cuenta con inscripciones vigentes?

        //Obtenemos en una lista todas las inscripciones del socio
        List<Inscription> inscriptionsforPartner = inscriptionRepository.findByPartner(partner);

        //Si la lista inscriptionsforPartner tiene registros...
        if(!inscriptionsforPartner.isEmpty()) {

            //De la lista obtenida anteriormente, creamos una nueva pero solo con las inscripciones que tengan estado "REGISTERED" o "INPROGRESS"
            List<Inscription> inscriptionsCurrentforPartner = inscriptionsforPartner.stream()
                    .filter(e -> e.getStatus() == StatusInscription.REGISTERED || e.getStatus() == StatusInscription.INPROGRESS)
                    .toList();

            //Si la lista inscriptionsCurrentforPartner cuenta con registros REGISTERED o INPROGRESS...
            if(!inscriptionsCurrentforPartner.isEmpty()) {
                //Recorremos la lista inscriptionsCurrentforPartner
                for (Inscription inscription : inscriptionsCurrentforPartner) {
                    //Creamos una nueva lista donde obtenemos el DetailInscription de cada inscripcion
                    List<InscriptionDetail> DetailsforCurrentInscription = inscriptionDetailRepository.findByInscription(inscription);

                    //Recorremos la lista details que contiene los detalles de la nueva solicitud de inscripcion
                    for(InscriptionDetail detailRequest : details) {
                        //A su vez recorremos la lista detailforCurrentInscription donde tenemos los detalles de las inscripciones vigentes con las q cuenta el socio
                        for(InscriptionDetail detailforCurrentInscription : DetailsforCurrentInscription){
                            //En caso la nueva solicitud contenga actividades las cuales el socio ya tiene registradas, lanzamos una excepcion
                            if(detailRequest.getActivity() == detailforCurrentInscription.getActivity()){
                                throw new BusinessException("Actualmente tienes una inscripción vigente en una de las actividades solicitadas. No puedes inscribirte dos veces a una misma Actividad");
                            }
                        }
                    }
                }
            }
        }

        //Seteamos el listado de detalle a objInscription
        objInscription.setInscriptionDetails(details);

        //Guardamos el objeto
        inscriptionRepository.save(objInscription);

        return inscriptionMapper.convertEntityToResponseDto(objInscription);

    }
}
