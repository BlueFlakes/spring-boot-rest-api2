package com.stockexchange.controller;

import com.stockexchange.dao.StockExchangeDao;
import com.stockexchange.model.StockExchange;
import com.stockexchange.service.restService.StockExchangeDefaultRestImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/stocks")
public class StockCtrl extends WebController<StockExchange, StockExchangeDao, StockExchangeDefaultRestImpl>{
    public StockCtrl(StockExchangeDefaultRestImpl stockExchangeBasicRest) {
        super(stockExchangeBasicRest);
    }
}
