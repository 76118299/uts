package com.uts.mapper;


import com.uts.config.database.BaseMapper;
import com.uts.entity.TradeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeDetailMapper extends BaseMapper<TradeDetail> {

	int updateStatusById(@Param("id") String id);

	List<TradeDetail> queryByNotSync();
	
}