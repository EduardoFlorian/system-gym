package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
