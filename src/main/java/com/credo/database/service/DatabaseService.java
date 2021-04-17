package com.credo.database.service;

import com.credo.database.dto.PersonDto;
import com.credo.database.entity.Address;
import com.credo.database.entity.Person;
import com.credo.database.mapper.Mapper;
import com.credo.database.repository.AddressRepository;
import com.credo.database.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    @Autowired PersonRepository personRepository;
    @Autowired AddressRepository addressRepository;
    @Autowired Mapper mapper;

    public Person createPerson(PersonDto person) {
        Person person1 = mapper.convertToEntity(person);
        if (person.getAddress() != null) {
            Address address = addressRepository.save(mapper.convertToEntity(person.getAddress()));
            person1.setAddress(address);
        }
        return personRepository.save(person1);
    }
}
