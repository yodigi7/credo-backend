package com.credo.database.repository;

import com.credo.database.entity.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, BigInteger> {
}
