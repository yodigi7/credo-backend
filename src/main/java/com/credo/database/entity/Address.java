package com.credo.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "address")
public class Address extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    BigInteger id;

    String streetAddress;
    String city;
    String state;
    String zipcode;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    Person person;

    public boolean similar(Address address) {
        return ((StringUtils.isNotBlank(address.streetAddress) && address.streetAddress.equalsIgnoreCase(streetAddress)) || StringUtils.isBlank(address.streetAddress)) &&
                ((StringUtils.isNotBlank(address.city) && address.city.equalsIgnoreCase(city)) || StringUtils.isBlank(address.city)) &&
                ((StringUtils.isNotBlank(address.state) && address.state.equalsIgnoreCase(state)) || StringUtils.isNotBlank(address.state)) &&
                ((StringUtils.isNotBlank(address.zipcode) && address.zipcode.equalsIgnoreCase(zipcode)) || StringUtils.isBlank(address.zipcode));
    }
}
