package com.uts.service;

import com.alibaba.fastjson.JSONObject;
import com.uts.entity.TradeDetail;
import com.uts.mapper.TradeDetailMapper;
import com.uts.netty.MyNettyClient;
import com.uts.protocol.Req;
import com.uts.utils.Const;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
@Service
public class MyTradeDetailService {
    @Autowired
    private TradeDetailMapper mapper;
    @Transactional
    public void send(TradeDetail td){
        //数据落地
        mapper.insert(td);
            //连接服务端netty
        ChannelFuture future = MyNettyClient.getNettyClient().getFuture();
        //封装协议
        Req req = new Req();
        req.setId(td.getId());
        //数据序列化成消息
        req.setReqeustMessage(JSONObject.toJSONString(td));
        req.setTag(Const.TRADE_DETAIL);
        req.setType(Const.SAVE);
        future.channel().writeAndFlush(req);

    }
    public int updateById(String id){
        return  mapper.updateStatusById(id);
    }
}


