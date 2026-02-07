package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Partner;
import com.systemgym.systemgym.model.StatusSubscription;
import com.systemgym.systemgym.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Integer> {

    //Metodo que permite retornar las suscripciones que pertenecen a un Partner en especifico
    List<Subscription> findByPartner(Partner partner);

    //Metodo que retorna todas las suscripciones
    List<Subscription> findAllByStatusSubscriptionAndEndDateBefore (StatusSubscription statusSubscription, LocalDateTime date);

}
