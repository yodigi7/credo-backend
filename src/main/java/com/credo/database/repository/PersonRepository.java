package com.credo.database.repository;

import com.credo.database.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PersonRepository extends CrudRepository<Person, BigInteger> {
}
