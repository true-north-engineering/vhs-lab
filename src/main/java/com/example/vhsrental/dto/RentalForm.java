package com.example.vhsrental.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class RentalForm {
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "VHS ID cannot be null")
    private Long vhsId;

    @NotNull(message = "Rental date cannot be null")
    private LocalDate rentalDate;

    public RentalForm(Long userId, Long vhsId, LocalDate rentalDate) {
        this.userId = userId;
        this.vhsId = vhsId;
        this.rentalDate = rentalDate;
    }

    public RentalForm() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVhsId() {
        return vhsId;
    }

    public void setVhsId(Long vhsId) {
        this.vhsId = vhsId;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }
}
