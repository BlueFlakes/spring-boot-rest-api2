package com.stockexchange.controller;

import com.stockexchange.dao.CustomerDao;
import com.stockexchange.model.Customer;
import com.stockexchange.service.restService.CustomerDefaultRestImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customers")
public class CustomerCtrl extends WebController<Customer, CustomerDao, CustomerDefaultRestImpl> {
    public CustomerCtrl(CustomerDefaultRestImpl customerBasicRest) {
        super(customerBasicRest);
    }
}
