package com.example.vhsrental.service;

import com.example.vhsrental.dto.RentalForm;
import com.example.vhsrental.entity.Rental;

import java.util.List;

public interface RentalService {
    List<Rental> getAllRentals();
    Rental getRentalById(Long id);
    Rental createRental(RentalForm rentalForm);
    Rental updateRental(Long id, RentalForm rentalForm);
    void deleteRental(Long id);
}
