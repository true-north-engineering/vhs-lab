package com.example.vhsrental.repository;

import com.example.vhsrental.entity.Rental;
import com.example.vhsrental.entity.User;
import com.example.vhsrental.entity.VHS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByVhsAndRentalDate(VHS vhs, LocalDate rentalDate);

    List<Rental> findByVhs(VHS vhs);

    void deleteAllByUser(User user);
}
