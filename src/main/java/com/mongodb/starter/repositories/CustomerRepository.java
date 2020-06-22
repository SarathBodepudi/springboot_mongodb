package com.mongodb.starter.repositories;

import com.mongodb.starter.models.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer addCustomer(Customer customer);

    List<Customer> addCustomers(List<Customer> customers);

    List<Customer> findAllCustomers();

    Customer getCustomer(String id);

    List<Customer> getCustomers(List<String> listIds);

    Long count();
}
