package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateSubscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseSubscriptionDTO;
import com.systemgym.systemgym.exception.ResourceNotFoundException;
import com.systemgym.systemgym.mapper.SubscriptionMapper;
import com.systemgym.systemgym.model.*;
import com.systemgym.systemgym.repository.ISubscriptionRepository;
import com.systemgym.systemgym.service.IPartnerService;
import com.systemgym.systemgym.service.IPlanService;
import com.systemgym.systemgym.service.ISubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

    private final ISubscriptionRepository iSubscriptionRepository;
    private final IPlanService iPlanService;
    private final IPartnerService iPartnerService;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionServiceImpl(ISubscriptionRepository iSubscriptionRepository, IPlanService iPlanService, SubscriptionMapper subscriptionMapper, IPartnerService iPartnerService) {
        this.iSubscriptionRepository = iSubscriptionRepository;
        this.iPlanService = iPlanService;
        this.subscriptionMapper = subscriptionMapper;
        this.iPartnerService = iPartnerService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseSubscriptionDTO saveSubscription(CreateSubscriptionDTO createSubscriptionDTO) throws Exception{

        //Obtener el objeto de partner y plan ingresados
        //Al estar dentro de un metodo @Transactional, estos objetos son guardados en el contexto de Persistencia
        Partner objPartner = iPartnerService.findByIdPartnerEntity(createSubscriptionDTO.idPartner());
        Plan objPlan = iPlanService.findByIdPlanEntity(createSubscriptionDTO.idPlan());

        //Convertir el request a entidad
        Subscription objSubscription = subscriptionMapper.convertRequestToEntity(createSubscriptionDTO);

        //Setear valores de manera manual para el objeto de suscripcion pq son datos que nacen por defecto del sistema, no del request del usuario
        objSubscription.setStartDate(LocalDateTime.now());
        objSubscription.setEndDate(objSubscription.getStartDate().plusDays(objPlan.getDuration().getDurationDays()));
        objSubscription.setStatusSubscription(StatusSubscription.ACTIVE);


        //Setearle los valores faltantes, ya que solo llegan un numero o UUId y nosotros necesitamos guardar un objeto complejo
        objSubscription.setPartner(objPartner);
        objSubscription.setPlan(objPlan);

        //Guardar objeto susbscription en la bd
        iSubscriptionRepository.save(objSubscription);

        //Como ya se creó la sub, proceder a actualizar el estado del socio
        //Podemos hacer el cambio de estado sin necesidad de un save debido a que el objPartner se encuentra en el contexto de Persistencia
        //El Contexto de Persistencia es una caja fuerte temporal donde JPA guarda todas las entidades que has recuperado de la base de datos durante una transacción
        //A partir de ese momento, JPA "vigila" ese objeto. Cualquier cambio que le hagas a las propiedades de ese objeto es detectado automáticamente por JPA.
        //Si hay diferencias, JPA genera y ejecuta los UPDATE de SQL automáticamente por ti.
        objPartner.setActive(true);

        return subscriptionMapper.convertEntityToResponseDto(objSubscription);
    }

    @Override
    public ResponseSubscriptionDTO getSubscriptionById(Integer id) throws Exception{

        Subscription subscriptionEntity = iSubscriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));

        return subscriptionMapper.convertEntityToResponseDto(subscriptionEntity);

    }

    @Override
    public List<ResponseSubscriptionDTO> findAllSubscriptions() throws Exception{

        List<Subscription> subscriptions = iSubscriptionRepository.findAll();
        List<ResponseSubscriptionDTO> subscriptionDTOList = subscriptions.stream().map(e-> subscriptionMapper.convertEntityToResponseDto(e)).toList();

        return subscriptionDTOList;
    }
}
