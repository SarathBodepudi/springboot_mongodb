package com.mongodb.starter.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @JsonSerialize(using = ToStringSerializer.class)
    //@NotEmpty(message = "Object Id Cannot Be Empty")
    private ObjectId id;
    //@NotEmpty(message = "Customer Id Cannot Be Empty")
    private BigInteger customerId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId);
    }
}
