package java.com.uts.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.com.uts.entity.TradeDetail;
import java.com.uts.mapper.TradeDetailMapper;
import java.com.uts.utils.Pair;
import java.com.uts.utils.SelectConnect;
import java.com.uts.utils.SelectDatabaseUtils;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
@Service
public class MyTradeDetailService {

    private static final String TBNAME_PIRFIX="UTSTRADEDETAIL";
    @Autowired
    private TradeDetailMapper mapper;
    @Transactional
    public void send(TradeDetail td){

    }
    @SelectConnect
    public int dynamicInsert(TradeDetail td){
        String uuid = td.getId();
        Pair<Integer, Integer> databaseAndTableNum = SelectDatabaseUtils.getDatabaseAndTableNum(uuid);
        Integer tbnum = databaseAndTableNum.getObject2();
        String tbname= TBNAME_PIRFIX+tbnum;

        JSONObject jsonObj = (JSONObject) JSONObject.toJSON(td);
        Map<String, Object> stringStringMap = JSONObject.parseObject(jsonObj.toJSONString(), new TypeReference<Map<String, Object>>() {
        });

        stringStringMap.put("tableName",tbname);
       return mapper.blanceInsert(stringStringMap);
    }
    @SelectConnect
    public TradeDetail getT(TradeDetail td){
        String uuid = td.getId();
        Pair<Integer, Integer> databaseAndTableNum = SelectDatabaseUtils.getDatabaseAndTableNum(uuid);
        Integer tbnum = databaseAndTableNum.getObject2();
        String tbname= TBNAME_PIRFIX+tbnum;
        mapper.findTradeDetail(tbname ,uuid);
        return null;
    }


    public int updateById(String id){
        return  mapper.updateStatusById(id);
    }
}


