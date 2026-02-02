package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateInscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseInscriptionDTO;
import com.systemgym.systemgym.mapper.InscriptionMapper;
import com.systemgym.systemgym.model.Activity;
import com.systemgym.systemgym.model.Inscription;
import com.systemgym.systemgym.model.Partner;
import com.systemgym.systemgym.model.StatusInscription;
import com.systemgym.systemgym.repository.IActivityRepository;
import com.systemgym.systemgym.repository.IInscriptionRepository;
import com.systemgym.systemgym.repository.IPartnerRepository;
import com.systemgym.systemgym.service.IInscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InscriptionServiceImpl implements IInscriptionService {

    private final IInscriptionRepository inscriptionRepository;
    private final InscriptionMapper inscriptionMapper;
    private final IPartnerRepository partnerRepository;
    private final IActivityRepository activityRepository;

    public InscriptionServiceImpl(IInscriptionRepository inscriptionRepository, InscriptionMapper inscriptionMapper, IPartnerRepository partnerRepository, IActivityRepository activityRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.inscriptionMapper = inscriptionMapper;
        this.partnerRepository = partnerRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public ResponseInscriptionDTO saveInscription(CreateInscriptionDTO createInscriptionDTO) throws Exception {

        //Validamos si los ids enviamos de los objetos complejos son validos
        if (createInscriptionDTO.idPartner()==null || createInscriptionDTO.idPartner()<=0 ) {
            throw new Exception("El idPartner ingresado no es válido");
        }

        if (createInscriptionDTO.idActivity()==null || createInscriptionDTO.idActivity()<=0 ) {
            throw new Exception("El idActivity ingresado no es válido");
        }

        //Obtenemos el objeto de cada id de objeto complejo obtenido
        Partner partner = partnerRepository.findById(createInscriptionDTO.idPartner()).orElseThrow(() -> new Exception("El idPartner ingresado no existe"));
        Activity activity = activityRepository.findById(createInscriptionDTO.idActivity()).orElseThrow(() -> new Exception("El idActivity ingresado no existe"));

        //Convertir a entidad el request
        Inscription inscription = inscriptionMapper.convertRequestToEntity(createInscriptionDTO);

        //Setear valores de manera manual pq son datos que nacen por defecto del sistema, no del request del usuario
        inscription.setDateRegistration(LocalDateTime.now());
        inscription.setStatus(StatusInscription.REGISTERED);

        //Setearle los valores faltantes, ya que solo llegan solo en valor numero y nosotros necesitamos guardar un objeto complejo
        inscription.setPartner(partner);
        inscription.setActivity(activity);

        //Guardar el objeto
        inscriptionRepository.save(inscription);

        return inscriptionMapper.convertEntityToResponseDTO(inscription);
    }

    @Override
    public ResponseInscriptionDTO findInscriptionById(Integer id) throws Exception {

        Inscription inscription = inscriptionRepository.findById(id).orElseThrow(() -> new Exception("Inscription not found"));

        return inscriptionMapper.convertEntityToResponseDTO(inscription);
    }

    @Override
    public List<ResponseInscriptionDTO> findAllInscriptions() throws Exception {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        List<ResponseInscriptionDTO> inscriptionDTOs = inscriptions.stream().map(e->inscriptionMapper.convertEntityToResponseDTO(e)).toList();

        return inscriptionDTOs;
    }
}
