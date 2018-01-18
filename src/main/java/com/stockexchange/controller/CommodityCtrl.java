package com.stockexchange.controller;

import com.stockexchange.dao.CommodityDao;
import com.stockexchange.model.Commodity;
import com.stockexchange.service.restService.CommodityDefaultRestImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/commodities")
public class CommodityCtrl extends WebController<Commodity, CommodityDao, CommodityDefaultRestImpl> {
    public CommodityCtrl(CommodityDefaultRestImpl restService) {
        super(restService);
    }
}
