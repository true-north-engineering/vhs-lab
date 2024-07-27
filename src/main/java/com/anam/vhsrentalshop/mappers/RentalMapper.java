package com.anam.vhsrentalshop.mappers;

import com.anam.vhsrentalshop.domain.dto.RentalDto;
import com.anam.vhsrentalshop.domain.entities.RentalEntity;

public class RentalMapper {

    public static RentalDto mapTo(RentalEntity rentalEntity) {
        if (rentalEntity == null) {
            return null;
        }
        RentalDto rentalDto = new RentalDto();
        rentalDto.setUserId(rentalEntity.getUserEntity().getId());
        rentalDto.setVhsId(rentalEntity.getVhsEntity().getId());
        rentalDto.setRentalDate(rentalEntity.getRentalDate());
        return rentalDto;
    }
}
