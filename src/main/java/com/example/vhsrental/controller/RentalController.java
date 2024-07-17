package com.example.vhsrental.controller;

import com.example.vhsrental.dto.RentalForm;
import com.example.vhsrental.entity.Rental;
import com.example.vhsrental.service.RentalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public ResponseEntity<Rental> createRental(@RequestBody @Valid RentalForm rentalForm) {
        logger.info("Received request to create rental");
        Rental rental = rentalService.createRental(rentalForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        logger.info("Received request to fetch all rentals");
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        logger.info("Received request to fetch rental with ID: {}", id);
        Rental rental = rentalService.getRentalById(id);
        return ResponseEntity.ok().body(rental);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody @Valid RentalForm rentalForm) {
        logger.info("Received request to update rental with ID: {}", id);
        Rental rental = rentalService.updateRental(id, rentalForm);
        return ResponseEntity.ok(rental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        logger.info("Received request to delete rental with ID: {}", id);
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}
