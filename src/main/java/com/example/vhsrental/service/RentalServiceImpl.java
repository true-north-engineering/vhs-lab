package com.example.vhsrental.service;

import com.example.vhsrental.dto.RentalForm;
import com.example.vhsrental.entity.Rental;
import com.example.vhsrental.entity.User;
import com.example.vhsrental.entity.VHS;
import com.example.vhsrental.exception.ResourceNotFoundException;
import com.example.vhsrental.exception.RentalException;
import com.example.vhsrental.repository.RentalRepository;
import com.example.vhsrental.repository.UserRepository;
import com.example.vhsrental.repository.VHSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalServiceImpl.class);

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VHSRepository vhsRepository;

    @Override
    public List<Rental> getAllRentals() {
        logger.info("Fetching all rentals");
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(Long id) {
        logger.info("Fetching rental with ID: {}", id);
        return rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + id));
    }

    @Override
    public Rental createRental(RentalForm rentalForm) {
        logger.info("Creating rental for user ID: {} and VHS ID: {}", rentalForm.getUserId(), rentalForm.getVhsId());
        Rental rental = new Rental();
        return saveRental(rental, rentalForm);
    }

    @Override
    public Rental updateRental(Long id, RentalForm rentalForm) {
        logger.info("Updating rental with ID: {}", id);
        Rental rental = getRentalById(id);
        return saveRental(rental, rentalForm);
    }

    @Override
    public void deleteRental(Long id) {
        logger.info("Deleting rental with ID: {}", id);
        Rental rental = getRentalById(id);
        rentalRepository.delete(rental);
    }

    private Rental saveRental(Rental rental, RentalForm rentalForm) {
        User user = userRepository.findById(rentalForm.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + rentalForm.getUserId()));
        VHS vhs = vhsRepository.findById(rentalForm.getVhsId())
                .orElseThrow(() -> new ResourceNotFoundException("VHS not found with ID: " + rentalForm.getVhsId()));

        if (!rentalRepository.findByVhsAndRentalDate(vhs, rentalForm.getRentalDate()).isEmpty()) {
            logger.error("VHS ID: {} is already rented on date: {}", rentalForm.getVhsId(), rentalForm.getRentalDate());
            throw new RentalException("VHS is already rented on this date");
        }

        rental.setUser(user);
        rental.setVhs(vhs);
        rental.setRentalDate(rentalForm.getRentalDate());
        rental.setDueDate(rentalForm.getRentalDate().plusDays(7));
        rental.setLateFee(0.0);

        logger.info("Saving rental for user ID: {} and VHS ID: {}", rentalForm.getUserId(), rentalForm.getVhsId());
        return rentalRepository.save(rental);
    }
}
