package com.stockexchange.controller;

import com.stockexchange.dao.ShareDao;
import com.stockexchange.model.Share;
import com.stockexchange.service.restService.ShareDefaultRestImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/shares")
public class ShareCtrl extends WebController<Share, ShareDao, ShareDefaultRestImpl>{
    public ShareCtrl(ShareDefaultRestImpl shareBasicRest) {
        super(shareBasicRest);
    }
}

