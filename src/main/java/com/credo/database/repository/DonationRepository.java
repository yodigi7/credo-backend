package com.credo.database.repository;

import com.credo.database.entity.Donation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DonationRepository extends CrudRepository<Donation, BigInteger> {
}
