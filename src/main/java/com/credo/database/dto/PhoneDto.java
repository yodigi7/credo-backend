package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Builder
@Data
public class PhoneDto {
    BigInteger phoneNumber;
    String phoneType;
}
