package com.stockexchange.service.restService;

import com.stockexchange.dao.ShareDao;
import com.stockexchange.model.Share;
import com.stockexchange.service.ObjectFieldValueSwapper;
import org.springframework.stereotype.Service;

@Service
public class ShareDefaultRestImpl extends DefaultRestServiceImpl<Share, ShareDao> {
    public ShareDefaultRestImpl(ShareDao shareDao,
                                ObjectFieldValueSwapper fieldValueSwapper) {
        super(shareDao, fieldValueSwapper);
    }
}