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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RentalServiceImplTest {

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VHSRepository vhsRepository;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRentals() {
        when(rentalRepository.findAll()).thenReturn(Collections.singletonList(new Rental()));

        rentalService.getAllRentals();

        verify(rentalRepository, times(1)).findAll();
    }

    @Test
    void getRentalById() {
        Rental rental = new Rental(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(7), 0.0);
        when(rentalRepository.findById(anyLong())).thenReturn(Optional.of(rental));

        rentalService.getRentalById(1L);

        verify(rentalRepository, times(1)).findById(anyLong());
    }

    @Test
    void createRental() {
        RentalForm rentalForm = new RentalForm(1L, 1L, LocalDate.now());
        Rental rental = new Rental(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(7), 0.0);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.of(new VHS()));
        when(rentalRepository.findByVhsAndRentalDate(any(VHS.class), any(LocalDate.class))).thenReturn(Collections.emptyList());
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        rentalService.createRental(rentalForm);

        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void updateRental() {
        RentalForm rentalForm = new RentalForm(1L, 1L, LocalDate.now());
        Rental rental = new Rental(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(7), 0.0);
        when(rentalRepository.findById(anyLong())).thenReturn(Optional.of(rental));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.of(new VHS()));
        when(rentalRepository.findByVhsAndRentalDate(any(VHS.class), any(LocalDate.class))).thenReturn(Collections.emptyList());
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        rentalService.updateRental(1L, rentalForm);

        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void deleteRental() {
        Rental rental = new Rental(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(7), 0.0);
        when(rentalRepository.findById(anyLong())).thenReturn(Optional.of(rental));
        doNothing().when(rentalRepository).delete(any(Rental.class));

        rentalService.deleteRental(1L);

        verify(rentalRepository, times(1)).delete(any(Rental.class));
    }

    @Test
    void createRentalThrowsRentalException() {
        RentalForm rentalForm = new RentalForm(1L, 1L, LocalDate.now());
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.of(new VHS()));
        when(rentalRepository.findByVhsAndRentalDate(any(VHS.class), any(LocalDate.class))).thenReturn(Collections.singletonList(new Rental()));

        assertThrows(RentalException.class, () -> rentalService.createRental(rentalForm));
    }

    @Test
    void createRentalThrowsResourceNotFoundException() {
        RentalForm rentalForm = new RentalForm(1L, 1L, LocalDate.now());
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rentalService.createRental(rentalForm));
    }
}
