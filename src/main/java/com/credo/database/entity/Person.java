package com.credo.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.ArrayList;
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
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "parish_id")
    Parish parish;
    String generalNotes;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Phone> phones = new ArrayList<>();
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Email> emails = new ArrayList<>();
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Donation> donations = new ArrayList<>();
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Event> events = new ArrayList<>();
}
