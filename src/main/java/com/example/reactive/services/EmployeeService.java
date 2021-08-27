package com.example.reactive.services;

import com.example.reactive.entities.Employee;
import com.example.reactive.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> findOne(BigInteger id) {
        return employeeRepository.findById(id)
                .doOnError(throwable ->
                        new IllegalArgumentException("Employee with specified id "
                                + id + " doesnt exist"));
    }

    public Mono<Employee> create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Mono<Employee> update(Employee employee, BigInteger id) {
        return employeeRepository.findById(id)
                .map(e -> {
                    if (employee.getAge() != null) {
                        e.setAge(employee.getAge());
                    }
                    if (employee.getName() != null) {
                        e.setName(employee.getName());
                    }
                    return e;
                })
                .flatMap(e -> employeeRepository.save(e));
    }

    public Mono<Void> delete(BigInteger id) {
        return employeeRepository.deleteById(id);
    }
}
