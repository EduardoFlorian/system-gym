package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInscriptionRepository extends JpaRepository<Inscription, Integer> {
}
