package com.anam.vhsrentalshop.domain.dto;

import com.anam.vhsrentalshop.domain.entities.RentalEntity;
import com.anam.vhsrentalshop.domain.entities.UserEntity;
import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RentalDto {

    @NotNull(message = "The user ID is required.")
    private Long userId;

    @NotNull(message = "The user ID is required.")
    private Long vhsId;

    @NotNull(message = "The rental date is required.")
    @FutureOrPresent(message = "The rental date must be today or in the future.")
    private LocalDate rentalDate;

    public RentalDto(Long userId, Long vhsId, LocalDate rentalDate) {
        this.userId = userId;
        this.vhsId = vhsId;
        this.rentalDate = rentalDate;
    }

    public RentalEntity toEntity(UserEntity userEntity, VhsEntity vhsEntity) {
        return new RentalEntity(userEntity, vhsEntity, this.rentalDate);
    }
}
