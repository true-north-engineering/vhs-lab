package com.truenorth.vhslab.service;

import com.truenorth.vhslab.model.Rental;
import com.truenorth.vhslab.model.User;
import com.truenorth.vhslab.model.VHS;
import com.truenorth.vhslab.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RentalService {

    @Autowired
    public RentalRepository rentalRepo = null;

    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentalRepo.findAll().forEach(rentals::add);
        return rentals;
    }

    public Rental getRentalById(Long id) {
        return id == null ? null : rentalRepo.findByRentalId(id);
    }

    //calculate fee
    public Rental calculateFeeForRental(Long id) {
        Rental rental = rentalRepo.findByRentalId(id);
        int feePerDay = 2; //fee per day is 2 kn
        //check if rental is not returned
        if (rental.getEndDate() == null) {
            rental.setEndDate(new Date()); //current date and time

            long timeDiff = Math.abs(rental.getEndDate().getTime() - rental.getDueDate().getTime());
            long daysDiff = TimeUnit.MILLISECONDS.toDays(timeDiff);

            if (daysDiff <= 0) {
                rental.setFee(0);
            }
            else {
                //calculate total fee
                rental.setFee((int) daysDiff * feePerDay);
            }
            //make VHS available again after return
            rental.getVhs().setIsAvailable(true);
        }
        return rentalRepo.save(rental);
    }

    public List<Rental> getAllRentalsByUserId(Long userId) {

        User user = new User();
        user.setUserId(userId);
        List<Rental> rentals = new ArrayList<>();
        if (user.getUserId() != null) {
            rentals = rentalRepo.findByUser(user);
        }
        return rentals;
    }

    public void createRental(Rental rental) throws PersistenceException {

        VHS vhsRental = rental.getVhs();
        User rentalUser = rental.getUser();
        if (vhsRental == null || rentalUser == null) {
            throw new PersistenceException("Cannot save rental with a non valid vhs or user");
        }
        //check if VHS is available (the same vhs can't have multiple rentals on the same date)
        if (Boolean.FALSE.equals(vhsRental.getIsAvailable())) {
            throw new PersistenceException("The VHS with title " + vhsRental.getTitle() + " is not available");
        }
        vhsRental.setIsAvailable(false);
        rentalRepo.save(rental);
    }

    //updateRental implementation
    public void updateRental(Rental rental, Long id) {
        rentalRepo.save(rental);
    }

    public void deleteRental(Long id) {
        rentalRepo.deleteById(id);
    }
}
