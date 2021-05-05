package com.credo.database.repository;

import com.credo.database.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EventRepository extends CrudRepository<Event, BigInteger> {
}
