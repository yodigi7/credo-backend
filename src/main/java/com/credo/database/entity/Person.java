package com.credo.database.entity;

import com.credo.database.dto.AddressDto;
import com.credo.database.dto.DonationDto;
import com.credo.database.dto.EmailDto;
import com.credo.database.dto.EventDto;
import com.credo.database.dto.PhoneDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "person")
public class Person extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    BigInteger id;

    String prefix;
    String preferredName;
    String firstName;
    String middleName;
    String lastName;
    String suffix;
    String membershipLevel;
    Boolean currentMember;
    @OneToOne(cascade = CascadeType.ALL)
    Address address;
    String generalNotes;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Phone> phones;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Email> emails;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Donation> donations;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Event> events;
}
