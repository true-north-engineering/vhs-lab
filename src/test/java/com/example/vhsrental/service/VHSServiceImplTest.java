package com.example.vhsrental.service;

import com.example.vhsrental.entity.Rental;
import com.example.vhsrental.entity.VHS;
import com.example.vhsrental.exception.ResourceNotFoundException;
import com.example.vhsrental.exception.VHSAssociatedWithRentalException;
import com.example.vhsrental.repository.RentalRepository;
import com.example.vhsrental.repository.VHSRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class VHSServiceImplTest {

    @Mock
    private VHSRepository vhsRepository;

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private VHSServiceImpl vhsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllVHS() {
        when(vhsRepository.findAll()).thenReturn(Collections.singletonList(new VHS()));

        vhsService.getAllVHS();

        verify(vhsRepository, times(1)).findAll();
    }

    @Test
    void getVHSById() {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.of(vhs));

        vhsService.getVHSById(1L);

        verify(vhsRepository, times(1)).findById(anyLong());
    }

    @Test
    void createVHS() {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsRepository.save(any(VHS.class))).thenReturn(vhs);

        vhsService.createVHS(vhs);

        verify(vhsRepository, times(1)).save(any(VHS.class));
    }

    @Test
    void updateVHS() {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        VHS updatedVHS = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.of(vhs));
        when(vhsRepository.save(any(VHS.class))).thenReturn(updatedVHS);

        vhsService.updateVHS(1L, vhs);

        verify(vhsRepository, times(1)).save(any(VHS.class));
    }

    @Test
    void deleteVHS() {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.of(vhs));
        when(rentalRepository.findByVhs(any(VHS.class))).thenReturn(Collections.emptyList());
        doNothing().when(vhsRepository).delete(any(VHS.class));

        vhsService.deleteVHS(1L);

        verify(vhsRepository, times(1)).delete(any(VHS.class));
    }

    @Test
    void deleteVHSAssociatedWithRentalThrowsException() {
        VHS vhs = new VHS(1L, "The Matrix", "Sci-Fi", 1999);
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.of(vhs));
        when(rentalRepository.findByVhs(any(VHS.class))).thenReturn(Collections.singletonList(new Rental()));

        assertThrows(VHSAssociatedWithRentalException.class, () -> vhsService.deleteVHS(1L));
    }

    @Test
    void getVHSByIdThrowsResourceNotFoundException() {
        when(vhsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vhsService.getVHSById(1L));
    }
}
