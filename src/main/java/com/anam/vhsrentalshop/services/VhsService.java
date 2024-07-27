package com.anam.vhsrentalshop.services;

import com.anam.vhsrentalshop.domain.entities.VhsEntity;

import java.util.List;
import java.util.Optional;

public interface VhsService {

    List<VhsEntity> findAll();

    Optional<VhsEntity> getVhsById(Long id);

    VhsEntity save(VhsEntity vhsEntity);

    boolean isExists(Long id);

    void delete(Long id);
}
