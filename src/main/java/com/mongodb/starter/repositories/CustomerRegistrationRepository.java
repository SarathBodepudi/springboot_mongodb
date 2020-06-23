package com.mongodb.starter.repositories;

import com.google.common.collect.Lists;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.starter.models.Customer;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class CustomerRegistrationRepository implements CustomerRepository {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationRepository.class);
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primary())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build();
    @Autowired
    private MongoClient client;
    private MongoCollection<Customer> customerCollection;

    @PostConstruct
    void init() {
        customerCollection = client.getDatabase("customer").getCollection("customers", Customer.class);
    }

    @Override
    public Customer addCustomer(Customer customer)  {
        LOGGER.debug("In addCustomer() with customer :\n{}", customer);

        customer.setId(new ObjectId());

        customerCollection.insertOne(customer);

        return customer;
    }

    @Override
    public List<Customer> addCustomers(List<Customer> customers) {
        LOGGER.debug("In addCustomers() with customer :\n{}", customers);
        List<Customer> customerList = Lists.newArrayList();
        LOGGER.debug("Exit addCustomers() with customer :\n{}", customerList);
        return customerList;
    }

    @Override
    public List<Customer> findAllCustomers() {
        LOGGER.debug("In findAllCustomers()");
        List<Customer> customerList = Lists.newArrayList();
        LOGGER.debug("Exit addCustomers() with customer :\n{}", customerList);
        return customerList;
    }

    @Override
    public Customer getCustomer(String id) {
        LOGGER.debug("In getCustomer() with id :{}", id);
        Customer customer = new Customer();
        LOGGER.debug("Exit getCustomer() with customer :\n{}", customer);
        return customer;
    }

    @Override
    public List<Customer> getCustomers(List<String> listIds) {
        LOGGER.debug("In getCustomers() with ids :{}", listIds);
        List<Customer> customerList = Lists.newArrayList();
        LOGGER.debug("Exit getCustomers() with customers :\n{}", customerList);
        return customerList;
    }

    @Override
    public Long count() {
        LOGGER.debug("Exit count() :{}", 101L);
        return 101L;
    }
}
