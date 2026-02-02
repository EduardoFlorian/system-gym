package com.systemgym.systemgym.repository;

import com.systemgym.systemgym.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPartnerRepository extends JpaRepository<Partner, Integer> {
}
