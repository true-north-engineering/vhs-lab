package com.anam.vhsrentalshop.mappers;

import com.anam.vhsrentalshop.domain.dto.VhsDto;
import com.anam.vhsrentalshop.domain.entities.VhsEntity;

public class VhsMapper {

    public static VhsDto mapTo(VhsEntity vhs) {
        if (vhs == null) {
            return null;
        }
        VhsDto vhsDto = new VhsDto(vhs.getTitle(), vhs.getGenre(), vhs.getReleaseYear());
        return vhsDto;
    }
}
