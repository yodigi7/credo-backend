package com.credo.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    String mailingLabel;
    String nameTag;
    Date membershipStartDate;
    Date membershipExpirationDate;
    String organizationName;
    String generalNotes;

    AddressDto address;
    List<PhoneDto> phones;
    List<EmailDto> emails;
    List<PaymentDto> payments;
    List<EventDto> events;
    ParishDto parish;
}
