package com.anam.vhsrentalshop.repositories;

import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VhsRepository extends JpaRepository<VhsEntity, Long> {
}
