package com.example.reactive.repositories;

import com.example.reactive.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, BigInteger> {
}
