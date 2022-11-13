package com.truenorth.vhslab.controller;

import com.truenorth.vhslab.exception.CustomException;
import com.truenorth.vhslab.model.Rental;
import com.truenorth.vhslab.model.VHS;
import com.truenorth.vhslab.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@Slf4j
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping(value = "")
    public List<Rental> getRentals() {
        log.info("Get all rentals");
        return rentalService.getAllRentals();
    }

    @GetMapping(value = "/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        log.info("Get rental " + id);
        return rentalService.getRentalById(id);
    }

    @GetMapping(value = "/user_id/rentals/{id}")
    public List<Rental> getRentalsByUserId(@PathVariable Long userId) {
        log.info("Get all rentals by user id " + userId);
        return rentalService.getAllRentalsByUserId(userId);
    }

    @PostMapping(value = "")
    public void createRental(@RequestBody Rental rental) {
        log.info("Create rental " + rental.toString());
        rentalService.createRental(rental);
    }

    @PutMapping(value = "/{id}")
    @ExceptionHandler({CustomException.class})
    public void updateRental(@RequestBody Rental rental, @PathVariable Long id) {
        log.info("Update rental " + rental.toString());
        if (rentalService.getRentalById(id) == null) {
            throw new CustomException("Rental not found");
        }
        rentalService.updateRental(rental, id);
    }

    @PutMapping(value = "returned/{id}")
    public Rental calculateFeeForRental(@PathVariable Long id) {
        log.info("Update fee for rental " + getRentalById(id).toString());
        return rentalService.calculateFeeForRental(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRental(@PathVariable Long id) {
        log.info("Delete rental " + id);
        Rental rental = rentalService.getRentalById(id);
        VHS rentalVhs = rental.getVhs();
        rentalVhs.setIsAvailable(true);
        rentalService.deleteRental(id);
    }
}
