package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.DataValue;
import com.cooperate.fly.datasource.SqlMapper;

@SqlMapper
public interface DataValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataValue record);

    int insertSelective(DataValue record);

    DataValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataValue record);

    int updateByPrimaryKeyWithBLOBs(DataValue record);

    int updateByPrimaryKey(DataValue record);
}