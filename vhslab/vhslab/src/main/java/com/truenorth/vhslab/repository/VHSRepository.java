package com.truenorth.vhslab.repository;

import com.truenorth.vhslab.model.VHS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {
    VHS findByVhsId (Long id);
}