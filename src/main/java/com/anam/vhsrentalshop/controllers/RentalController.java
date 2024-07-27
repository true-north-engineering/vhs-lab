package com.anam.vhsrentalshop.controllers;

import com.anam.vhsrentalshop.domain.dto.RentalDto;
import com.anam.vhsrentalshop.domain.entities.RentalEntity;
import com.anam.vhsrentalshop.domain.entities.UserEntity;
import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import com.anam.vhsrentalshop.exceptions.ResourceNotFoundException;
import com.anam.vhsrentalshop.services.RentalService;
import com.anam.vhsrentalshop.services.UserService;
import com.anam.vhsrentalshop.services.VhsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

    private final RentalService rentalService;
    private final UserService userService;
    private final VhsService vhsService;

    public RentalController(RentalService rentalService, UserService userService, VhsService vhsService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.vhsService = vhsService;
    }

    @GetMapping()
    public ResponseEntity<List<RentalEntity>> getAllRentals() {
        logger.debug("Entering getAllRentals method");
        List<RentalEntity> rentals = rentalService.findAllActiveRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RentalEntity> createRental(@Valid @RequestBody RentalDto rental) throws ResourceNotFoundException {
        logger.info("Create rental request {}", rental);

        Optional<UserEntity> optionalUser = userService.getUserById(rental.getUserId());

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("error.user.not.found");
        }
        Optional<VhsEntity> optionalVhs = vhsService.getVhsById(rental.getVhsId());

        if (optionalVhs.isEmpty()) {
            throw new ResourceNotFoundException("error.vhs.not.found");
        }
        RentalEntity createdRental = rentalService.createRental(rental);
        return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
    }

    @PutMapping(path = "/return/{id}")
    public ResponseEntity<RentalDto> returnVhs(@PathVariable Long id) throws ResourceNotFoundException {
        RentalEntity savedUpdatedRental = rentalService.updateReturnRental(id);
        return new ResponseEntity(savedUpdatedRental, HttpStatus.OK);
    }
}
