package com.anam.vhsrentalshop.repositories;

import com.anam.vhsrentalshop.domain.entities.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Long> {

    Optional<RentalEntity> findByVhsEntityIdAndRentalDate(Long vhsId, LocalDate rentalDate);

    boolean existsByVhsEntityIdAndReturnedFalse(Long vhsId);

    Optional<RentalEntity> findById(Long aLong);

    List<RentalEntity> findAllByDeletedFalse();
}
