package java.com.uts.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.Logger;

import org.slf4j.LoggerFactory;


import java.com.uts.entity.TradeDetail;
import java.com.uts.listener.ApplicationFactory;
import java.com.uts.protocol.Req;
import java.com.uts.protocol.Resp;
import java.com.uts.service.MyTradeDetailService;
import java.com.uts.utils.Const;

/**
 * Created by Administrator on 2018/3/7 0007.
 */
public class ServerNettyHandler extends ChannelInboundHandlerAdapter{
   private static org.slf4j.Logger logger = LoggerFactory.getLogger(ServerNettyHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
      Req req = (Req) msg;
        logger.info("req");
        Resp resp = new Resp();
        TradeDetail tb = JSON.parseObject(req.getReqeustMessage(), TradeDetail.class);
        try {
            if (Const.TRADE_DETAIL.equals(req.getTag())) {
                MyTradeDetailService service = (MyTradeDetailService) ApplicationFactory.getBean("MyTradeDetailService");

                if (Const.SAVE.equals(req.getType())) {
                    int res = service.dynamicInsert(tb);
                    if (res == 1) {
                        resp.setId(tb.getId());
                        resp.setType(req.getTag());
                        resp.setTag(Const.UPDATE);
                        resp.setResponseCode(Const.RESPONSE_CODE_OK);
                        ctx.writeAndFlush(resp);
                    } else if (res == 0) {
                        resp.setId(tb.getId());
                        resp.setType(req.getTag());
                        resp.setTag(Const.UPDATE);
                        resp.setResponseCode(Const.RESPONSE_CODE_INSERT_ERR);
                        ctx.writeAndFlush(resp);
                    }
                }


            }
        }catch (Exception e){
            e.printStackTrace();
            resp.setId(tb.getId());
            resp.setType(req.getTag());
            resp.setTag(Const.UPDATE);
            resp.setResponseCode(Const.RESPONSE_CODE_INSERT_ERR);
            ctx.writeAndFlush(resp);
        }


    }
}
