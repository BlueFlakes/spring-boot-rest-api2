package com.stockexchange.dao;

import com.stockexchange.model.StockExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeDao extends CrudRepository<StockExchange, Integer>{
}
