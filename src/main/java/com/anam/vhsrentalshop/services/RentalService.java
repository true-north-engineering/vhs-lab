package com.anam.vhsrentalshop.services;

import com.anam.vhsrentalshop.domain.dto.RentalDto;
import com.anam.vhsrentalshop.domain.entities.RentalEntity;
import com.anam.vhsrentalshop.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RentalService {

    List<RentalEntity> findAll();

    RentalEntity save(RentalEntity rentalEntity);

    RentalEntity createRental(RentalDto rentalDto) throws ResourceNotFoundException;

    RentalEntity updateReturnRental(Long id) throws ResourceNotFoundException;

    Optional<RentalEntity> findById(Long id) throws ResourceNotFoundException;

    List<RentalEntity> findAllActiveRentals();
}
