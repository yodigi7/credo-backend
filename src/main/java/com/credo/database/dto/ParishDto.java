package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Builder
@Data
public class ParishDto {
    BigInteger id;
    String name;
    List<PersonDto> parishioners;
}
