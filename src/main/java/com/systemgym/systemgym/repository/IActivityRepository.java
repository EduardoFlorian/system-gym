package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivityRepository extends JpaRepository<Activity, Integer> {
}
