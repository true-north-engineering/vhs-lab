package com.anam.vhsrentalshop.services.impl;

import com.anam.vhsrentalshop.domain.dto.RentalDto;
import com.anam.vhsrentalshop.domain.entities.RentalEntity;
import com.anam.vhsrentalshop.domain.entities.UserEntity;
import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import com.anam.vhsrentalshop.exceptions.ResourceNotFoundException;
import com.anam.vhsrentalshop.repositories.RentalRepository;
import com.anam.vhsrentalshop.repositories.UserRepository;
import com.anam.vhsrentalshop.repositories.VhsRepository;
import com.anam.vhsrentalshop.services.RentalService;
import com.anam.vhsrentalshop.services.VhsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RentalServiceImpl implements RentalService {

    private static final Logger logger = LoggerFactory.getLogger(VhsService.class);

    private RentalRepository rentalRepository;

    private VhsRepository vhsRepository;

    private UserRepository userRepository;

    public RentalServiceImpl(RentalRepository rentalRepository, VhsRepository vhsRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.vhsRepository = vhsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RentalEntity> findAll() {
        return StreamSupport.stream(rentalRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalEntity> findAllActiveRentals() {
        List<RentalEntity> activeRentals = StreamSupport.stream(rentalRepository.findAllByDeletedFalse().spliterator(), false)
                .collect(Collectors.toList());
        return activeRentals;
    }

    @Override
    public Optional<RentalEntity> findById(Long id) {
        return rentalRepository.findById(id);
    }

    @Override
    public RentalEntity save(RentalEntity rentalEntity) {
        return rentalRepository.save(rentalEntity);
    }

    @Override
    public RentalEntity createRental(RentalDto rentalDto) {
        UserEntity user = userRepository.findById(rentalDto.getUserId()).get();
        VhsEntity vhs = vhsRepository.findById(rentalDto.getVhsId()).get();
        Long vhsId = rentalDto.getVhsId();

        boolean isRented = rentalRepository.existsByVhsEntityIdAndReturnedFalse(vhsId);

        if (isRented) {
            throw new IllegalStateException("The VHS is already rented.");
        }
        boolean isRentedOnTheDate = rentalRepository.findByVhsEntityIdAndRentalDate(vhsId, rentalDto.getRentalDate()).isPresent();

        if (isRentedOnTheDate) {
            throw new IllegalStateException("The VHS is already rented on this date.");
        }
        RentalEntity rental = rentalDto.toEntity(user, vhs);

        logger.info("Created rental is: {}", rental);
        return rentalRepository.save(rental);
    }

    @Override
    public RentalEntity updateReturnRental(Long id) throws ResourceNotFoundException {
        RentalEntity rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental not found"));
        rental.setReturnDate(LocalDate.now());
        double lateFee = calculateLateFee(rental.getReturnDate(), rental.getDueDate());
        rental.setLateFee(lateFee);
        rental.setDeleted(true); // mark as deleted
        return rentalRepository.save(rental);
    }

    public double calculateLateFee(LocalDate returnDate, LocalDate dueDate) {
        if (returnDate == null || !returnDate.isAfter(dueDate)) {
            return 0.0;
        }
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysLate * 0.5; // fee is 0.5 euros per day
    }
}
