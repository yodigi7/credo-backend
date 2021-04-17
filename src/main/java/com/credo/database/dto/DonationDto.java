package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class DonationDto {
    Double donationAmount;
    Date donationDate;
    String donationNotes;
}
