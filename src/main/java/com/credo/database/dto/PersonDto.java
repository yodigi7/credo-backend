package com.credo.database.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Builder
@Data
public class PersonDto {
    BigInteger id;
    String prefix;
    String preferredName;
    String firstName;
    String middleName;
    String lastName;
    String suffix;
    String membershipLevel;
    Boolean currentMember;
    AddressDto address;
    List<PhoneDto> phones;
    List<EmailDto> emails;
    List<DonationDto> donations;
    List<EventDto> events;
    String generalNotes;
    ParishDto parish;
}
