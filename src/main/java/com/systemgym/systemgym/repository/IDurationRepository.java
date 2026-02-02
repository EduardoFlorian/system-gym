package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDurationRepository extends JpaRepository<Duration, Integer> {
}
