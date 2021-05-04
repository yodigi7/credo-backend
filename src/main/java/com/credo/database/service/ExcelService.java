package com.credo.database.service;

import com.credo.database.entity.Address;
import com.credo.database.entity.Donation;
import com.credo.database.entity.Email;
import com.credo.database.entity.Event;
import com.credo.database.entity.Person;
import com.credo.database.entity.Phone;
import com.credo.database.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ExcelService {
    @Autowired
    PersonRepository personRepository;

    public XSSFWorkbook getDbToWorkbook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet personSheet = workbook.createSheet("Persons");
        XSSFSheet addressSheet = workbook.createSheet("Addresses");
        XSSFSheet phoneSheet = workbook.createSheet("Phones");
        XSSFSheet emailSheet = workbook.createSheet("Emails");
        XSSFSheet donationSheet = workbook.createSheet("Donations");
        XSSFSheet eventSheet = workbook.createSheet("Events");

        addPersonHeader(personSheet);
        addAddressHeader(addressSheet);
        addPhoneHeader(phoneSheet);
        addEmailHeader(emailSheet);
        addDonationHeader(donationSheet);
        addEventHeader(eventSheet);

        AtomicInteger personRowCount = new AtomicInteger(1);
        AtomicInteger addressRowCount = new AtomicInteger(1);
        AtomicInteger phoneRowCount = new AtomicInteger(1);
        AtomicInteger emailRowCount = new AtomicInteger(1);
        AtomicInteger donationRowCount = new AtomicInteger(1);
        AtomicInteger eventRowCount = new AtomicInteger(1);
        personRepository.findAll().forEach(person -> {
            addPerson(personSheet, person, personRowCount);
            person.getPhones().forEach(phone -> addPhone(phoneSheet, phone, phoneRowCount));
            addAddress(addressSheet, person.getAddress(), addressRowCount);
            person.getEmails().forEach(email -> addEmails(emailSheet, email, emailRowCount));
            person.getDonations().forEach(donation -> addDonations(donationSheet, donation, donationRowCount));
            person.getEvents().forEach(event -> addEvents(eventSheet, event, eventRowCount));
        });

        //Don't resize general notes column person sheet
        autoSizeColumns(personSheet, 7);
        autoSizeColumns(phoneSheet, 1);
        autoSizeColumns(addressSheet, 3);
        autoSizeColumns(emailSheet, 1);
        autoSizeColumns(donationSheet, 2);
        autoSizeColumns(eventSheet, 2);
        return workbook;
    }

    void autoSizeColumns(XSSFSheet sheet, Integer lastColumn) {
        for (int i = 0; i <= lastColumn; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void addEventHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        setCell(cell, "Event Name");
        cell = row.createCell(1);
        setCell(cell, "Event Date");
        cell = row.createCell(2);
        setCell(cell, "Amount");
    }

    private void addEmailHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        setCell(cell, "Email");
        cell = row.createCell(1);
        setCell(cell, "Email Type");
    }

    private void addPhoneHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        setCell(cell, "Phone Number");
        cell = row.createCell(1);
        setCell(cell, "Phone Type");
    }

    private void addPersonHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        setCell(cell, "Prefix");
        cell = row.createCell(1);
        setCell(cell, "Preferred Name");
        cell = row.createCell(2);
        setCell(cell, "First Name");
        cell = row.createCell(3);
        setCell(cell, "Middle Name");
        cell = row.createCell(4);
        setCell(cell, "Last Name");
        cell = row.createCell(5);
        setCell(cell, "Suffix");
        cell = row.createCell(6);
        setCell(cell, "Membership Level");
        cell = row.createCell(7);
        setCell(cell, "Current Member");
        cell = row.createCell(8);
        setCell(cell, "General Notes");
    }

    private void addAddressHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        setCell(cell, "Street Address");
        cell = row.createCell(1);
        setCell(cell, "City");
        cell = row.createCell(2);
        setCell(cell, "State");
        cell = row.createCell(3);
        setCell(cell, "Zipcode");
    }

    private void addDonationHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        setCell(cell, "Donation Amount");
        cell = row.createCell(1);
        setCell(cell, "Donation Date");
        cell = row.createCell(2);
        setCell(cell, "Donation Notes");
    }

    void addDonations(XSSFSheet sheet, Donation donation, AtomicInteger rowCount) {
        Row row = sheet.createRow(rowCount.get());
        Cell cell = row.createCell(0);
        setCell(cell, donation.getDonationAmount());
        cell = row.createCell(1);
        setCell(cell, donation.getDonationDate());
        cell = row.createCell(2);
        setCell(cell, donation.getDonationNotes());
        rowCount.getAndSet(rowCount.get() + 1);
    }

    void addEvents(XSSFSheet sheet, Event event, AtomicInteger rowCount) {
        Row row = sheet.createRow(rowCount.get());
        Cell cell = row.createCell(0);
        setCell(cell, event.getEventName());
        cell = row.createCell(1);
        setCell(cell, event.getEventDate());
        cell = row.createCell(2);
        setCell(cell, event.getAmount());
        rowCount.getAndSet(rowCount.get() + 1);
    }

    void addEmails(XSSFSheet sheet, Email email, AtomicInteger rowCount) {
        Row row = sheet.createRow(rowCount.get());
        Cell cell = row.createCell(0);
        setCell(cell, email.getEmail());
        cell = row.createCell(1);
        setCell(cell, email.getEmailType());
        rowCount.getAndSet(rowCount.get() + 1);
    }

    void addAddress(XSSFSheet sheet, Address address, AtomicInteger rowCount) {
        Row row = sheet.createRow(rowCount.get());
        Cell cell = row.createCell(0);
        setCell(cell, address.getStreetAddress());
        cell = row.createCell(1);
        setCell(cell, address.getCity());
        cell = row.createCell(2);
        setCell(cell, address.getState());
        cell = row.createCell(3);
        setCell(cell, address.getZipcode());
        rowCount.getAndSet(rowCount.get() + 1);
    }

    void addPhone(XSSFSheet sheet, Phone phone, AtomicInteger rowCount) {
        Row row = sheet.createRow(rowCount.get());
        Cell cell = row.createCell(0);
        setCell(cell, phone.getPhoneNumber().toString());
        cell = row.createCell(1);
        setCell(cell, phone.getPhoneType());
        rowCount.getAndSet(rowCount.get() + 1);
    }

    void addPerson(XSSFSheet sheet, Person person, AtomicInteger rowCount) {
        Row row = sheet.createRow(rowCount.get());
        Cell cell = row.createCell(0);
        setCell(cell, person.getPrefix());
        cell = row.createCell(1);
        setCell(cell, person.getPreferredName());
        cell = row.createCell(2);
        setCell(cell, person.getFirstName());
        cell = row.createCell(3);
        setCell(cell, person.getMiddleName());
        cell = row.createCell(4);
        setCell(cell, person.getLastName());
        cell = row.createCell(5);
        setCell(cell, person.getSuffix());
        cell = row.createCell(6);
        setCell(cell, person.getMembershipLevel());
        cell = row.createCell(7);
        setCell(cell, person.getCurrentMember());
        cell = row.createCell(8);
        setCell(cell, person.getGeneralNotes());
        rowCount.getAndSet(rowCount.get() + 1);
    }

    void setCell(Cell cell, String value) {
        cell.setCellValue(Objects.requireNonNullElse(value, ""));
    }

    void setCell(Cell cell, Double value) {
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue("");
        }
    }

    void setCell(Cell cell, Date value) {
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue("");
        }
    }

    void setCell(Cell cell, Boolean value) {
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue("");
        }
    }
}
