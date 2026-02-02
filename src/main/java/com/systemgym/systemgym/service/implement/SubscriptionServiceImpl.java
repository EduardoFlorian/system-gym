package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateSubscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseSubscriptionDTO;
import com.systemgym.systemgym.mapper.SubscriptionMapper;
import com.systemgym.systemgym.model.*;
import com.systemgym.systemgym.repository.IPartnerRepository;
import com.systemgym.systemgym.repository.IPlanRepository;
import com.systemgym.systemgym.repository.ISubscriptionRepository;
import com.systemgym.systemgym.service.ISubscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

    private final ISubscriptionRepository iSubscriptionRepository;
    private final IPlanRepository iPlanRepository;
    private final IPartnerRepository iPartnerRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionServiceImpl(ISubscriptionRepository iSubscriptionRepository, IPlanRepository iPlanRepository, IPartnerRepository iPartnerRepository, SubscriptionMapper subscriptionMapper) {
        this.iSubscriptionRepository = iSubscriptionRepository;
        this.iPlanRepository = iPlanRepository;
        this.iPartnerRepository = iPartnerRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public ResponseSubscriptionDTO saveSubscription(CreateSubscriptionDTO createSubscriptionDTO) throws Exception{

        //Validar si el id partner es válido
        if(createSubscriptionDTO.idPartner()==null || createSubscriptionDTO.idPartner()<=0){
            throw new Exception("El id Partner ingresado es inválido");
        }

        //Validar si el id plan es válido
        if(createSubscriptionDTO.idPlan()==null || createSubscriptionDTO.idPlan().equals(new UUID(0L, 0L))){
            throw new Exception("El id Plan ingresado es inválido");
        }

        //Obtener el objeto de partner y plan ingresados
        Partner objPartner = iPartnerRepository.findById(createSubscriptionDTO.idPartner()).orElseThrow(() -> new Exception("El id Partner ingresado no existe"));
        Plan objPlan = iPlanRepository.findById(createSubscriptionDTO.idPlan()).orElseThrow(() -> new Exception("El id Plan ingresado no existe"));

        //Convertir el request a entidad
        Subscription objSubscription = subscriptionMapper.convertRequestToEntity(createSubscriptionDTO);

        //Setear valores de manera manual pq son datos que nacen por defecto del sistema, no del request del usuario
        objSubscription.setStartDate(LocalDateTime.now());
        objSubscription.setEndDate(objSubscription.getStartDate().plusDays(objPlan.getDuration().getDurationDays()));
        objSubscription.setStatusSubscription(StatusSubscription.ACTIVE);


        //Setearle los valores faltantes, ya que solo llegan un numero o UUId y nosotros necesitamos guardar un objeto complejo
        objSubscription.setPartner(objPartner);
        objSubscription.setPlan(objPlan);

        //Guardar objeto susbscription en la bd
        iSubscriptionRepository.save(objSubscription);

        return subscriptionMapper.convertEntityToResponseDto(objSubscription);
    }

    @Override
    public ResponseSubscriptionDTO getSubscriptionById(Integer id) throws Exception{

        Subscription subscriptionEntity = iSubscriptionRepository.findById(id).orElseThrow(() -> new Exception("Subscription not found"));

        return subscriptionMapper.convertEntityToResponseDto(subscriptionEntity);

    }

    @Override
    public List<ResponseSubscriptionDTO> findAllSubscriptions() throws Exception{

        List<Subscription> subscriptions = iSubscriptionRepository.findAll();
        List<ResponseSubscriptionDTO> subscriptionDTOList = subscriptions.stream().map(e-> subscriptionMapper.convertEntityToResponseDto(e)).toList();

        return subscriptionDTOList;
    }
}
