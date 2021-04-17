package com.credo.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    Person person;

    public boolean similar(Address address) {
        return ((StringUtils.isNotBlank(address.streetAddress) && address.streetAddress.equalsIgnoreCase(streetAddress)) || StringUtils.isBlank(address.streetAddress)) &&
                ((StringUtils.isNotBlank(address.city) && address.city.equalsIgnoreCase(city)) || StringUtils.isBlank(address.city)) &&
                ((StringUtils.isNotBlank(address.state) && address.state.equalsIgnoreCase(state)) || StringUtils.isNotBlank(address.state)) &&
                ((StringUtils.isNotBlank(address.zipcode) && address.zipcode.equalsIgnoreCase(zipcode)) || StringUtils.isBlank(address.zipcode));
    }
}
