package com.stockexchange.dao;

import com.stockexchange.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CommonRepository<Customer, Integer> {}
