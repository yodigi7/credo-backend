package com.credo.database.mapper;

import com.credo.database.dto.AddressDto;
import com.credo.database.dto.PersonDto;
import com.credo.database.entity.Address;
import com.credo.database.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired ModelMapper modelMapper;
    
    public Person convertToEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    public Address convertToEntity(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }
}
