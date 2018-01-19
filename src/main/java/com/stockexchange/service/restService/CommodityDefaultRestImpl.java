package com.stockexchange.service.restService;

import com.stockexchange.dao.CommodityDao;
import com.stockexchange.dao.StockExchangeDao;
import com.stockexchange.exception.AlreadyOccupiedIdException;
import com.stockexchange.exception.AppCustomException;
import com.stockexchange.exception.ArchivedDataException;
import com.stockexchange.model.Commodity;
import com.stockexchange.model.StockExchange;
import com.stockexchange.service.ObjectFieldValueSwapper;
import com.stockexchange.service.loggerService.LoggerAble;
import org.springframework.stereotype.Service;

@Service
public class CommodityDefaultRestImpl extends DefaultRestServiceImpl<Commodity, CommodityDao> {
    private StockExchangeDao stockExchangeDao;

    public CommodityDefaultRestImpl(CommodityDao objectDao,
                                    ObjectFieldValueSwapper fieldValueSwapper,
                                    LoggerAble logger,
                                    StockExchangeDao stockExchangeDao) {
        super(objectDao, fieldValueSwapper, logger);
        this.stockExchangeDao = stockExchangeDao;
    }

    @Override
    public Commodity post(Commodity obj) throws AppCustomException {
        StockExchange stockExchange = obj.getStockExchange();

        if (stockExchange != null) {
            Integer stockId = stockExchange.getId();

            if (stockId != null) {
                StockExchange thisCommodityStockExchange = this.stockExchangeDao.findByArchivedIsFalseAndIdEquals(stockId);

                if (thisCommodityStockExchange != null && !thisCommodityStockExchange.isArchived()) {
                    obj.setStockExchange(thisCommodityStockExchange);
                    return super.post(obj);
                }
            }

        }

        throw new ArchivedDataException();
    }

    public StockExchangeDao getStockExchangeDao( ) {
        return stockExchangeDao;
    }

    public void setStockExchangeDao(StockExchangeDao stockExchangeDao) {
        this.stockExchangeDao = stockExchangeDao;
    }
}
