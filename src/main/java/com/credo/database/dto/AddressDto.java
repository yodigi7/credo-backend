package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDto {
    String streetAddress;
    String city;
    String state;
    String zipcode;
}
