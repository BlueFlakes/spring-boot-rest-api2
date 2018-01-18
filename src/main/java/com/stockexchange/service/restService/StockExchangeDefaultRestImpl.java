package com.stockexchange.service.restService;

import com.stockexchange.dao.StockExchangeDao;
import com.stockexchange.exception.AlreadyOccupiedIdException;
import com.stockexchange.exception.UnavailableElementException;
import com.stockexchange.model.Commodity;
import com.stockexchange.model.StockExchange;
import com.stockexchange.service.ObjectFieldValueSwapper;
import com.stockexchange.service.loggerService.LoggerAble;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockExchangeDefaultRestImpl extends DefaultRestServiceImpl<StockExchange, StockExchangeDao> {
    public StockExchangeDefaultRestImpl(StockExchangeDao stockExchangeDao,
                                        ObjectFieldValueSwapper fieldValueSwapper,
                                        LoggerAble logger) {
        super(stockExchangeDao, fieldValueSwapper, logger);
    }

    @Override
    public void deleteById(Integer id) throws UnavailableElementException {
        StockExchange stockExchange = get(id);
        List<Commodity> commodities = stockExchange.getCommodities();

        if (commodities != null) {
            for (Commodity commodity : commodities) {
                commodity.setArchived(true);
            }
        }

        super.deleteById(id);
    }
}
