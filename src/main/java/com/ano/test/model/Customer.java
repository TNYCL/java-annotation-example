package com.ano.test.model;

import com.ano.test.api.annotation.ParameterMethod;
import com.ano.test.api.annotation.ParameterSize;

@ParameterSize(min = 3, max = 25)
public class Customer {

    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ParameterMethod
    public String getFirstName() {
        return this.firstName;
    }

    @ParameterMethod
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

}
