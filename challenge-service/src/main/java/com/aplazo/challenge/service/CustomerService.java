package com.aplazo.challenge.service;

import com.aplazo.challenge.dto.CustomerRequest;
import com.aplazo.challenge.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse getCustomerById(Integer id);

}
