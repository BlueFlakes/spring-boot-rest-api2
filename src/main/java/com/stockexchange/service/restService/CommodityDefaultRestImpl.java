package com.stockexchange.service.restService;

import com.stockexchange.dao.CommodityDao;
import com.stockexchange.model.Commodity;
import com.stockexchange.service.ObjectFieldValueSwapper;
import org.springframework.stereotype.Service;

@Service
public class CommodityDefaultRestImpl extends DefaultRestServiceImpl<Commodity, CommodityDao> {
    public CommodityDefaultRestImpl(CommodityDao objectDao, ObjectFieldValueSwapper fieldValueSwapper) {
        super(objectDao, fieldValueSwapper);
    }
}
