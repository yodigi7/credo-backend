package com.credo.database.service;

import com.credo.database.dto.PersonDto;
import com.credo.database.entity.Person;
import com.credo.database.mapper.Mapper;
import com.credo.database.repository.AddressRepository;
import com.credo.database.repository.DonationRepository;
import com.credo.database.repository.EmailRepository;
import com.credo.database.repository.EventRepository;
import com.credo.database.repository.PersonRepository;
import com.credo.database.repository.PhoneRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DatabaseService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    Mapper mapper;

    public Person createPerson(PersonDto person) {
        return personRepository.save(
                mapper.convertToEntity(person));
    }

    public List<Person> getPersons(PersonDto personDto) {
        if (personDto.getId() != null) {
            return Collections.singletonList(personRepository.findById(personDto.getId()).orElseThrow(() ->
                    new RuntimeException(String.format("Cannot find person with id: %s",
                            personDto.getId().toString()))));
        } else {
            return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                    .filter((person) -> filterByPersonDto(personDto, person))
                    .collect(Collectors.toList());
        }
    }

    boolean filterByPersonDto(PersonDto personDto, Person person) {
        Person mappedPersonDto = mapper.convertToEntity(personDto);
        return ((StringUtils.isNotBlank(mappedPersonDto.getPrefix()) && mappedPersonDto.getPrefix().equalsIgnoreCase(person.getPrefix())) || StringUtils.isBlank(personDto.getPrefix())) &&
                ((StringUtils.isNotBlank(mappedPersonDto.getPreferredName()) && mappedPersonDto.getPreferredName().equalsIgnoreCase(person.getPreferredName())) || StringUtils.isBlank(personDto.getPreferredName())) &&
                ((StringUtils.isNotBlank(mappedPersonDto.getFirstName()) && mappedPersonDto.getFirstName().equalsIgnoreCase(person.getFirstName())) || StringUtils.isBlank(personDto.getFirstName())) &&
                ((StringUtils.isNotBlank(mappedPersonDto.getMiddleName()) && mappedPersonDto.getMiddleName().equalsIgnoreCase(person.getMiddleName())) || StringUtils.isBlank(personDto.getMiddleName())) &&
                ((StringUtils.isNotBlank(mappedPersonDto.getLastName()) && mappedPersonDto.getLastName().equalsIgnoreCase(person.getLastName())) || StringUtils.isBlank(personDto.getLastName())) &&
                ((StringUtils.isNotBlank(mappedPersonDto.getSuffix()) && mappedPersonDto.getSuffix().equalsIgnoreCase(person.getSuffix())) || StringUtils.isBlank(personDto.getSuffix())) &&
                ((StringUtils.isNotBlank(mappedPersonDto.getMembershipLevel()) && mappedPersonDto.getMembershipLevel().equalsIgnoreCase(person.getMembershipLevel())) || StringUtils.isBlank(personDto.getMembershipLevel())) &&
                ((mappedPersonDto.getCurrentMember() != null && mappedPersonDto.getCurrentMember().equals(person.getCurrentMember())) || personDto.getCurrentMember() == null) &&
                ((mappedPersonDto.getAddress() != null && mappedPersonDto.getAddress().similar(person.getAddress())) || personDto.getAddress() == null) &&
                ((CollectionUtils.isEmpty(mappedPersonDto.getPhones()) && mappedPersonDto.getPhones().containsAll(person.getPhones())) || CollectionUtils.isEmpty(personDto.getPhones())) &&
                ((CollectionUtils.isEmpty(mappedPersonDto.getEmails()) && mappedPersonDto.getEmails().containsAll(person.getEmails())) || CollectionUtils.isEmpty(personDto.getEmails())) &&
                ((CollectionUtils.isEmpty(mappedPersonDto.getDonations()) && mappedPersonDto.getDonations().containsAll(person.getDonations())) || CollectionUtils.isEmpty(personDto.getEmails())) &&
                ((CollectionUtils.isEmpty(mappedPersonDto.getEvents()) && mappedPersonDto.getEvents().containsAll(person.getEvents())) || CollectionUtils.isEmpty(personDto.getEmails())) &&
                ((StringUtils.isNotBlank(mappedPersonDto.getGeneralNotes()) && mappedPersonDto.getGeneralNotes().equalsIgnoreCase(person.getGeneralNotes())) || StringUtils.isBlank(personDto.getGeneralNotes()));
    }
}
