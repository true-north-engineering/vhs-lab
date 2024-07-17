package com.example.vhsrental.controller;

import com.example.vhsrental.dto.RentalForm;
import com.example.vhsrental.entity.Rental;
import com.example.vhsrental.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RentalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalController rentalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rentalController).build();
    }

    @Test
    void createRental() throws Exception {
        Rental rental = new Rental(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(7), 0.0);
        when(rentalService.createRental(any(RentalForm.class))).thenReturn(rental);

        mockMvc.perform(post("/api/rental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"vhsId\":1,\"rentalDate\":\"2024-01-01\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));

        verify(rentalService, times(1)).createRental(any(RentalForm.class));
    }

    @Test
    void getAllRentals() throws Exception {
        when(rentalService.getAllRentals()).thenReturn(Collections.singletonList(new Rental()));

        mockMvc.perform(get("/api/rental"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(rentalService, times(1)).getAllRentals();
    }

    @Test
    void getRentalById() throws Exception {
        Rental rental = new Rental(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(7), 0.0);
        when(rentalService.getRentalById(anyLong())).thenReturn(rental);

        mockMvc.perform(get("/api/rental/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(rentalService, times(1)).getRentalById(anyLong());
    }

    @Test
    void updateRental() throws Exception {
        Rental rental = new Rental(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(7), 0.0);
        when(rentalService.updateRental(anyLong(), any(RentalForm.class))).thenReturn(rental);

        mockMvc.perform(put("/api/rental/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"vhsId\":1,\"rentalDate\":\"2024-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(rentalService, times(1)).updateRental(anyLong(), any(RentalForm.class));
    }

    @Test
    void deleteRental() throws Exception {
        doNothing().when(rentalService).deleteRental(anyLong());

        mockMvc.perform(delete("/api/rental/1"))
                .andExpect(status().isNoContent());

        verify(rentalService, times(1)).deleteRental(anyLong());
    }
}
