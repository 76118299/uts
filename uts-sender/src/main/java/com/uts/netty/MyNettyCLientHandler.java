package com.uts.netty;

import com.uts.listener.ApplicationFactory;
import com.uts.protocol.Req;
import com.uts.protocol.Resp;
import com.uts.service.MyTradeDetailService;
import com.uts.utils.Const;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 读取服务端响应的数据
 * Created by Administrator on 2018/3/6 0006.
 */
public class MyNettyCLientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            //读取服务端响应的消息
            Resp resp = (Resp) msg;
            String id = resp.getId();
            String tag = resp.getTag();
            String type = resp.getType();
            String responseCode = resp.getResponseCode();
            String responseMessage = resp.getResponseMessage();

            if(Const.TRADE_DETAIL.equals(tag)){
                MyTradeDetailService myTradeDetailService = (MyTradeDetailService) ApplicationFactory.getBean("myTradeDetailService");
                //更新本地数据库
                if(Const.RESPONSE_CODE_OK.equals(responseCode)){
                    if(Const.UPDATE.equals(type)){
                        myTradeDetailService.updateById(id);
                    }

                }
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }



    }
}
