package com.credo.database.repository;

import com.credo.database.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AddressRepository extends CrudRepository<Address, BigInteger> {
}
