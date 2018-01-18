package com.stockexchange.dao;

import com.stockexchange.model.Commodity;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityDao extends CommonRepository<Commodity, Integer> {
}
