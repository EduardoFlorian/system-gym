package com.systemgym.systemgym.service.implement;

import com.systemgym.systemgym.dto.request.CreateSubscriptionDTO;
import com.systemgym.systemgym.dto.response.ResponseSubscriptionDTO;
import com.systemgym.systemgym.exception.RecordAlreadyExistsException;
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
import java.util.Optional;
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

        //Validar si el usuario ingresado cuenta con alguna suscripcion activa
        iSubscriptionRepository.findByPartner(objPartner)
                .stream()
                .filter(e->e.getStatusSubscription()==StatusSubscription.ACTIVE)
                .findFirst()
                .ifPresent(e-> {throw new RecordAlreadyExistsException("El socio cuenta con una subscripción activa. No puede asociarse a una nueva hasta que esta culmine y/o cancele la actual");});

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelSubscription(Integer idPartner, Integer idSubscription) throws Exception {

        //Validar parametros si son validos
        if(idPartner==null || idPartner<=0){
            throw new ResourceNotFoundException("El idPartner es inválido. El valor enviado no debe ser nulo y debe ser un número entero");
        }
        if(idSubscription==null || idSubscription<=0){
            throw new ResourceNotFoundException("El idSubscription es inválido. El valor enviado no debe ser nulo y debe ser un número entero");
        }

        //Obtener objetos tanto del partner como de la suscripcion mediante los parametros enviados
        Partner objPartner = iPartnerService.findByIdPartnerEntity(idPartner);
        Subscription objSubscription = iSubscriptionRepository.findById(idSubscription).orElseThrow(() -> new ResourceNotFoundException("La subscripcion ingresada no existe"));

        //Validar si la suscripcion enviada por el usuario está activa y ademas si pertenece al partner enviado.
        if(objSubscription.getStatusSubscription()==StatusSubscription.ACTIVE){

            if(objSubscription.getPartner().getId()==objPartner.getId()){
                objSubscription.setStatusSubscription(StatusSubscription.CANCELLED);
                objPartner.setActive(false);
            }
            else {
                throw new Exception("La suscripcion enviada no pertenece al usuario enviado");
            }
        }
        else{
            throw new Exception("La suscripcion enviada no se encuentra activa");
        }

    }

}
