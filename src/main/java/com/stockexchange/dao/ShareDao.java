package com.stockexchange.dao;

import com.stockexchange.model.Share;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareDao extends CommonRepository<Share, Integer>{
}
