package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPlanRepository extends JpaRepository<Plan, UUID> {
}
