package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainerRepository extends JpaRepository<Trainer, Integer> {
}
