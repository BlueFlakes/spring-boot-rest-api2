package com.stockexchange.service.restService;

import com.stockexchange.dao.CustomerDao;
import com.stockexchange.model.Customer;
import com.stockexchange.service.ObjectFieldValueSwapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerDefaultRestImpl extends DefaultRestServiceImpl<Customer, CustomerDao> {
    public CustomerDefaultRestImpl(CustomerDao customerDao,
                                   ObjectFieldValueSwapper valueSwapper) {
        super(customerDao, valueSwapper);
    }
}
