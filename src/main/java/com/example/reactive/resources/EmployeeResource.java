package com.example.reactive.resources;

import com.example.reactive.entities.Employee;
import com.example.reactive.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@RestController
@RequestMapping("/reactive")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/find/{id}")
    public Mono<Employee> findOne(@PathVariable BigInteger id) {
        return employeeService.findOne(id);
    }

    @PostMapping("/create")
    public Mono<Employee> create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/update/{id}")
    public Mono<Employee> update(@RequestBody Employee employee, @PathVariable BigInteger id) {
        return employeeService.update(employee, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable BigInteger id) {
        return employeeService.delete(id);
    }
}
