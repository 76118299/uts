package com.uts.config.database;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
