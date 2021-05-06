package com.credo.database.repository;

import com.credo.database.entity.Parish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ParishRepository extends CrudRepository<Parish, BigInteger> {
}
