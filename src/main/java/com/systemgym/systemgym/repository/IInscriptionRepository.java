package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Inscription;
import com.systemgym.systemgym.model.Partner;
import com.systemgym.systemgym.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInscriptionRepository extends JpaRepository<Inscription, Integer> {

    //Metodo que permite retornar las inscripciones que pertenecen a un Partner en especifico
    List<Inscription> findByPartner(Partner partner);
}
