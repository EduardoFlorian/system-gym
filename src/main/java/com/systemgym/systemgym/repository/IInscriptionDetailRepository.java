package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Inscription;
import com.systemgym.systemgym.model.InscriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IInscriptionDetailRepository extends JpaRepository<InscriptionDetail, Integer> {

    List<InscriptionDetail> findByInscription(Inscription inscription);
}
