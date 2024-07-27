package com.anam.vhsrentalshop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VhsDto {

    private String title;
    private String genre;
    private int releaseYear;
}
