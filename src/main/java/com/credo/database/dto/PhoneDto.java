package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PhoneDto {
    String phoneNumber;
    String phoneType;
}
