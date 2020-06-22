package com.mongodb.starter.controllers;

import com.mongodb.starter.models.Customer;
import com.mongodb.starter.models.Person;
import com.mongodb.starter.repositories.CustomerRepository;
import com.mongodb.starter.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        LOGGER.debug("In addCustomer() POST Mapping");
        return customerRepository.addCustomer(customer);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Customer> addCustomers(@RequestBody List<Customer> customers) {
        LOGGER.debug("In addCustomers() POST Mapping");
        return customerRepository.addCustomers(customers);
    }

    @GetMapping("customer")
    public List<Customer> getAllCustomers() {
        LOGGER.debug("In getAllCustomers() GET Mapping");
        return customerRepository.findAllCustomers();
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        LOGGER.debug("In getCustomer() - GET by Id Mapping: {}", id);
        Customer customer = customerRepository.getCustomer(id);
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(customer);
    }

    // comma separated ids: http://localhost:8080/api/persons/5ef0c596708b5528a9b2209e%2C5ef0c596708b5528a9b2209f
    @GetMapping("customers/{ids}")
    public List<Customer> getCustomers(@PathVariable String ids) {
        List<String> listIds = asList(ids.split(","));
        return customerRepository.getCustomers(listIds);
    }

    @GetMapping("customer/count")
    public Long getCount() {
        return customerRepository.count();
    }
    /*

    @DeleteMapping("person/{id}")
    public Long deletePerson(@PathVariable String id) {
        return personRepository.delete(id);
    }

    @DeleteMapping("persons/{ids}")
    public Long deletePersons(@PathVariable String ids) {
        List<String> listIds = asList(ids.split(","));
        return personRepository.delete(listIds);
    }

    @DeleteMapping("persons")
    public Long deletePersons() {
        return personRepository.deleteAll();
    }

    @PutMapping("person")
    public Person putPerson(@RequestBody Person person) {
        return personRepository.update(person);
    }

    @PutMapping("persons")
    public Long putPerson(@RequestBody List<Person> persons) {
        return personRepository.update(persons);
    }

    @GetMapping("persons/averageAge")
    public Double averageAge() {
        return personRepository.getAverageAge();
    }
    */

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
