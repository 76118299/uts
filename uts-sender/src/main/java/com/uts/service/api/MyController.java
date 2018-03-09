package com.uts.service.api;

import com.uts.entity.TradeDetail;
import com.uts.service.MyTradeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
@RestController
public class MyController {
    @Autowired
    private MyTradeDetailService service;
    @RequestMapping("/sender")
    public String sender(@RequestParam("coutn") Integer count){
        TradeDetail td = new TradeDetail();
        service.send(td);

        return null;
    }
}
