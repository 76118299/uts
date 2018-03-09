package java.com.uts.mapper;



import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.com.uts.entity.TradeDetail;
import java.util.List;
import java.util.Map;

public interface TradeDetailMapper extends BaseMapper<TradeDetail> {

	int updateStatusById(@Param("id") String id);

	List<TradeDetail> queryByNotSync();

	int blanceInsert(Map<String, Object> stringStringMap);

	TradeDetail findTradeDetail(@Param("tableName") String tbname, @Param("id")String uuid);
}
