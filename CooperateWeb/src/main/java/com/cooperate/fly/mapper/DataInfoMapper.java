package com.cooperate.fly.mapper;

import com.cooperate.fly.bo.DataInfo;

public interface DataInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataInfo record);

    int insertSelective(DataInfo record);

    DataInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataInfo record);

    int updateByPrimaryKey(DataInfo record);
}