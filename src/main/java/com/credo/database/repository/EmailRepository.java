package com.credo.database.repository;

import com.credo.database.entity.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EmailRepository extends CrudRepository<Email, BigInteger> {
}
