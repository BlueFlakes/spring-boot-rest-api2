package com.stockexchange.service.restService;

import com.stockexchange.dao.StockExchangeDao;
import com.stockexchange.model.StockExchange;
import com.stockexchange.service.ObjectFieldValueSwapper;
import org.springframework.stereotype.Service;

@Service
public class StockExchangeDefaultRestImpl extends DefaultRestServiceImpl<StockExchange, StockExchangeDao> {
    public StockExchangeDefaultRestImpl(StockExchangeDao stockExchangeDao,
                                        ObjectFieldValueSwapper fieldValueSwapper) {
        super(stockExchangeDao, fieldValueSwapper);
    }
}
