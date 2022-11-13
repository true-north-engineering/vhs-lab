package com.truenorth.vhslab.repository;

import com.truenorth.vhslab.model.Rental;
import com.truenorth.vhslab.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    Rental findByRentalId(Long id);
    List<Rental> findByUser(User user);
}