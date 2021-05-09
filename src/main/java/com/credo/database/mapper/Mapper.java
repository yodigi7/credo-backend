package com.credo.database.mapper;

import com.credo.database.dto.AddressDto;
import com.credo.database.dto.EmailDto;
import com.credo.database.dto.EventDto;
import com.credo.database.dto.ParishDto;
import com.credo.database.dto.PaymentDto;
import com.credo.database.dto.PersonDto;
import com.credo.database.dto.PhoneDto;
import com.credo.database.entity.Address;
import com.credo.database.entity.Email;
import com.credo.database.entity.Event;
import com.credo.database.entity.Parish;
import com.credo.database.entity.Payment;
import com.credo.database.entity.Person;
import com.credo.database.entity.Phone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class Mapper {
    @Autowired
    ModelMapper modelMapper;

    public Person convertToEntity(PersonDto personDto) {
        Person person = modelMapper.map(personDto, Person.class);
        if (person.getAddress() != null) {
            person.getAddress().setPerson(person);
        }
        if (person.getParish() != null) {
            if (person.getParish().getParishioners() == null) {
                person.getParish().setParishioners(List.of(person));
            } else {
                person.getParish().getParishioners().add(person);
            }
        }
        Optional.ofNullable(person.getPhones()).orElse(Collections.emptyList())
                .forEach(phone -> phone.setPerson(person));
        Optional.ofNullable(person.getEvents()).orElse(Collections.emptyList())
                .forEach(event -> event.setPerson(person));
        Optional.ofNullable(person.getEmails()).orElse(Collections.emptyList())
                .forEach(email -> email.setPerson(person));
        Optional.ofNullable(person.getPayments()).orElse(Collections.emptyList())
                .forEach(payment -> payment.setPerson(person));
        return person;
    }

    public Address convertToEntity(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    public Phone convertToEntity(PhoneDto phoneDto) {
        return modelMapper.map(phoneDto, Phone.class);
    }

    public Payment convertToEntity(PaymentDto dto) {
        return modelMapper.map(dto, Payment.class);
    }

    public Event convertToEntity(EventDto dto) {
        return modelMapper.map(dto, Event.class);
    }

    public Email convertToEntity(EmailDto dto) {
        return modelMapper.map(dto, Email.class);
    }

    public Parish convertToEntity(ParishDto dto) {
        return modelMapper.map(dto, Parish.class);
    }
}
