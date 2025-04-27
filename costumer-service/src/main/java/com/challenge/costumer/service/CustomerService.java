package com.challenge.costumer.service;

import com.challenge.costumer.dto.CustomerRequest;
import com.challenge.costumer.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerById(Integer id);

}
